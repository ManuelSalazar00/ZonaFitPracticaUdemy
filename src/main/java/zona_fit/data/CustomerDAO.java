package zona_fit.data;

import zona_fit.domain.Customer;

import static zona_fit.connection.ConnectionJdbc.getConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CustomerDAO implements ICustomerDAO {

    Logger log = Logger.getLogger(getClass().getName());

    @Override
    public List<Customer> listCustomer() {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection newConnection = getConnection();
        String sqlScript = "SELECT id, nombre, apellido, membresia FROM cliente ORDER BY id";
        try {
            preparedStatement = newConnection.prepareStatement(sqlScript);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("nombre"));
                customer.setLastName(resultSet.getString("apellido"));
                customer.setMembership(resultSet.getInt("membresia"));
                customers.add(customer);
            }
        } catch (Exception e) {
            log.info("Error al listar los clientes" + e.getMessage());
        } finally {
            try {
                newConnection.close();
            } catch (SQLException e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return customers;
    }

    @Override
    public boolean findByIdCustomer(Customer customer) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection newConnection = getConnection();
        String sqlScript = "SELECT id, nombre, apellido, membresia FROM cliente  WHERE id = ?";
        try {
            preparedStatement = newConnection.prepareStatement(sqlScript);
            preparedStatement.setInt(1, customer.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setName(resultSet.getString("nombre"));
                customer.setLastName(resultSet.getString("apellido"));
                customer.setMembership(resultSet.getInt("membresia"));
                return true;
            }

        } catch (Exception e) {
            log.info("Error al recupear cliente por id: " + e.getMessage());
        } finally {
            try {
                newConnection.close();
            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        PreparedStatement preparedStatement;
        Connection newConection = getConnection();
        String sqlScript = "INSERT INTO cliente (nombre, apellido, membresia ) "
                + " VALUES  (?, ?, ?);";
        try {
            preparedStatement = newConection.prepareStatement(sqlScript);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getMembership());
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            log.info("Error al cargar cliente" + e.getMessage());
        } finally {
            try {
                newConection.close();
            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        PreparedStatement preparedStatement;
        Connection newConnection = getConnection();
        String sqlScript = "UPDATE cliente SET nombre=?, apellido=?, membresia=? " +
                " WHERE id = ?";
        try {
            preparedStatement = newConnection.prepareStatement(sqlScript);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getMembership());
            preparedStatement.setInt(4, customer.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            log.info("Error al actulizzar datos de cliente" + e.getMessage());
        } finally {
            try {
                newConnection.close();
            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        PreparedStatement preparedStatement;
        Connection newConnection = getConnection();
        String sqlScript = "DELETE FROM cliente WHERE id = ?";
        try {
            preparedStatement = newConnection.prepareStatement(sqlScript);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            log.info("Error al eliminar datos de cliente" + e.getMessage());
        } finally {
            try {

            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

}
