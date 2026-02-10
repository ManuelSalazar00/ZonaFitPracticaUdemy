package zona_fit.data;

import zona_fit.domain.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> listCustomer();

    boolean findByIdCustomer(Customer customer);

    boolean addCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(Customer customer);

}
