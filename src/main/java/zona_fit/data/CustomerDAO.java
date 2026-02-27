package zona_fit.data;

import zona_fit.domain.Customer;
import zona_fit.exceptions.DatabaseException;

import static zona_fit.connection.ConnectionJdbc.getConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO implements ICustomerDAO {

    @Override
    public List<Customer> listCustomer() {
        List<Customer> customers = new ArrayList<>();
        String sqlScript = "SELECT id, nombre, apellido, membresia FROM cliente ORDER BY id";
        try (Connection newConnection = getConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement(sqlScript);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        }
        return customers;
    }

    @Override
    public Optional<Customer> findByIdCustomer(Long id) {
        String sqlScript = "SELECT id, nombre, apellido, membresia FROM cliente  WHERE id = ?";
        try (Connection newConnection = getConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement(sqlScript)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("nombre"));
                    customer.setLastName(resultSet.getString("apellido"));
                    customer.setMembership(resultSet.getInt("membresia"));
                    return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al recupear cliente por id: ", e);
        }
        return Optional.empty();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        String sqlScript = "INSERT INTO cliente (nombre, apellido, membresia ) "
                + " VALUES  (?, ?, ?);";
        try (Connection newConnection = getConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement(sqlScript
                     , Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getMembership());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generateKeys = preparedStatement.getGeneratedKeys()) {
                    if (generateKeys.next()) {
                        customer.setId(generateKeys.getLong(1));
                    }
                }
                return customer;
            } else {
                throw new DatabaseException("No se pudo insertar cliente");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crea nuevo cliente: ", e);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String sqlScript = "UPDATE cliente SET nombre=?, apellido=?, membresia=? " +
                " WHERE id = ?";
        try (Connection newConnection = getConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement(sqlScript)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setInt(3, customer.getMembership());
            preparedStatement.setLong(4, customer.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0)
                throw new DatabaseException("No se encontro el cleinte con id: " + customer.getId());

            return customer;

        } catch (SQLException e) {
            throw new DatabaseException("Error al modificar los datos de cliente: ", e);
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {
        String sqlScript = "DELETE FROM cliente WHERE id = ?";
        try (Connection newConnection = getConnection();
             PreparedStatement preparedStatement = newConnection.prepareStatement(sqlScript)) {
            preparedStatement.setLong(1, customer.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0)
                throw new DatabaseException("No se encontro el cleinte con id: " + customer.getId());

        } catch (SQLException e) {
            throw new DatabaseException("Error al eliminar datos de cliente: ", e);
        }
    }

}
