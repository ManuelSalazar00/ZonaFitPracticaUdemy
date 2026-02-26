package zona_fit.data;

import zona_fit.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerDAO {
    List<Customer> listCustomer();

    Optional<Customer> findByIdCustomer(Long id);

    boolean saveCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(Customer customer);

}
