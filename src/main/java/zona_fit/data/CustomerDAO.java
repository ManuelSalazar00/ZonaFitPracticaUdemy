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
        }
        finally {
            try {
                newConnection.close();
            }catch (Exception e){
                log.info("Error al cerrar la conexion" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addCustomer(Customer customer) {

        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return false;
    }

    /* prueba local*/
          /* public static void main(String[] args) {
        ICustomerDAO customerDAO = new CustomerDAO();

        System.out.println("**** prueba local ****");
        var customers = customerDAO.listCustomer();
        ICustomerDAO customerDAO = new CustomerDAO();

        customers.forEach(System.out::println);

        System.out.println("**** prueba local ****");
        Customer customer =  new Customer(4);
        System.out.println("Cliente antes de la busqeuda: " + customer);
        Boolean findOk = customerDAO.findByIdCustomer(customer);
        if(findOk)
            System.out.println("Cliente ok: " + customer);
        else
            System.out.println("cliente no encontrado: " + customer.getId());

    }*/
}
