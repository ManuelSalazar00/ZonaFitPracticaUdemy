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

    /* prueba local
    public static void main(String[] args) {
        System.out.println("**** prueba local ****");

        ICustomerDAO customerDAO = new CustomerDAO();
        var customers = customerDAO.listCustomer();
        customers.forEach(System.out::println);
    }*/
}
