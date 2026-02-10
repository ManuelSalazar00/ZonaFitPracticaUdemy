package zona_fit.data;

import zona_fit.domain.Customer;

import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    @Override
    public List<Customer> listCustomer() {
        return null;
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
}
