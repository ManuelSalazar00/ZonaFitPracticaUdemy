package zona_fit.data;

import zona_fit.domain.Customer;
import zona_fit.exceptions.DatabaseException;

import static zona_fit.connection.ConnectionJdbc.getConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerDAO implements ICustomerDAO {

    private static final Logger log = LoggerFactory.getLogger(CustomerDAO.class);

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
        } catch (SQLException e) {
            throw new DatabaseException("Error al listar todos los clientes: ", e);
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

        } catch (SQLException e) {
            throw new DatabaseException("Error al recupear cliente por id: ", e);
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

        } catch (SQLException e) {
            throw new DatabaseException("Error al crea nuevo cliente: ", e);
        } finally {
            try {
                newConection.close();
            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }

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
        } catch (SQLException e) {
            throw new DatabaseException("Error al modificar los datos de cliente: ", e);
        } finally {
            try {
                newConnection.close();
            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
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

        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar datos de cliente: ", e);
        } finally {
            try {

            } catch (Exception e) {
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
    }

}
