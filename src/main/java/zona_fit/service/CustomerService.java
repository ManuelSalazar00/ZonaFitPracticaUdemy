package zona_fit.service;

import zona_fit.data.ICustomerDAO;
import zona_fit.domain.Customer;
import zona_fit.exceptions.ServiceException;


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

    public Optional<Customer> getFindByIdCustomer(Long id) {
        return customerDAO.findByIdCustomer(id);
    }

    public Customer saveCustomer(String name, String lastName, int membership) {
        Customer newCustomer = new Customer(name, lastName, membership);
        return customerDAO.saveCustomer(newCustomer);
    }

    public Customer updateCustomer(long id, String name, String lastName, int membership) {
        Customer existingCustomer = getFindByIdCustomer(id)
                .orElseThrow(() -> new ServiceException("Cliente no encontrado"));

        boolean modified = validateAndApplyChanges(existingCustomer, name, lastName, membership);

        if (!modified) {
            return existingCustomer;
        }

        return customerDAO.updateCustomer(existingCustomer);
    }

    public void deleteCustomer(Long id){
        Customer existingCustomer = getFindByIdCustomer(id)
                .orElseThrow(() -> new ServiceException("Cliente no encontrado"));

        customerDAO.deleteCustomer(existingCustomer);
    }


    private boolean validateAndApplyChanges(Customer customer, String name, String lastName, int membership) {
        boolean modified = false;

        if (name != null && !name.isBlank() && !name.equals(customer.getName())) {
            customer.setName(name);
            modified = true;
        }

        if (lastName != null && !lastName.isBlank() && !lastName.equals(customer.getLastName())) {
            customer.setLastName(lastName);
            modified = true;
        }

        if (membership > 0 && membership != customer.getMembership()) {
            customer.setMembership(membership);
            modified = true;
        }

        return modified;
    }


}
