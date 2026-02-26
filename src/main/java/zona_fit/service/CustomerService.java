package zona_fit.service;

import zona_fit.data.ICustomerDAO;
import zona_fit.domain.Customer;


import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.listCustomer();
    }

    public Optional<Customer> getFindByIdCustomer(Long id){
        return customerDAO.findByIdCustomer(id);
    }




}
