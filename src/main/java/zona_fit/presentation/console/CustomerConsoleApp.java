package zona_fit.presentation.console;


import zona_fit.data.CustomerDAO;
import zona_fit.data.ICustomerDAO;
import zona_fit.domain.Customer;
import zona_fit.utilities.UserFrames;

import java.util.List;
import java.util.Scanner;

public class CustomerConsoleApp {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ICustomerDAO customerDAO = new CustomerDAO();
        CustomerConsoleApp consoleApp = new CustomerConsoleApp();
        Customer customer;

        final int NUMBER_OPTIONS_MAX = 6;
        int option = 0;
        while (option != NUMBER_OPTIONS_MAX) {
            consoleApp.showMenu();
            option = consoleApp.scanner.nextInt();

            switch (option) {
                case 1:
                    consoleApp.allCustomers(customerDAO);
                    break;
                case 2:
                    consoleApp.findByIdCustomer(customerDAO);
                    break;
                case 3:
                    consoleApp.saveCustomer(customerDAO);
                    break;
                case 4:
                    consoleApp.updateCustomer(customerDAO);
                    break;
                case 5:
                    consoleApp.deleteCustomer(customerDAO);
                    break;
                case 6:
                    consoleApp.showMessageExitApplication();
                    break;
                default:
                    System.out.println(UserFrames.displayOutputSubTitles("Opcion invalida, Gracias por usar el servicio"));
                    break;

            }
        }
    }

    public void showMenu() {
        System.out.println("------------ Menu ----------");
        System.out.println(UserFrames.displayOutputSubTitles("" +
                "# 1. Listar todos los clientes. \n" +
                "# 2. Buscar cliente por id. \n" +
                "# 3. Agregar cliente. \n" +
                "# 4. Modificar cliente.\n" +
                "# 5. Eliminar cliente.\n" +
                "# 6. Salir \n"));
        System.out.print("digite una opcion : ");
    }

    public List<Customer> allCustomers(ICustomerDAO customerDAO) {
        List<Customer> customers = customerDAO.listCustomer();
        customers.forEach(System.out::println);
        return customers;
    }

    public boolean findByIdCustomer(ICustomerDAO customerDAO) {
        System.out.print("ingrese el id del cliente: ");
        int numberIdentityCustomer = scanner.nextInt();
        scanner.nextLine();
        Customer customer = new Customer(numberIdentityCustomer);
        Boolean findCustomerById = customerDAO.findByIdCustomer(customer);
        if (findCustomerById == true) {
            System.out.println("Cliente encontrado: " + customer);
        } else {
            System.out.println("Cliente no encontrado: " + customer.getId());
        }
        return false;
    }

    public boolean saveCustomer(ICustomerDAO customerDAO) {
        System.out.println("ingrese el nombre: ");
        String inputName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("ingrese el apellido: ");
        String inputLastName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("ingrese el valor de la mebresia: ");
        int inputMembership = scanner.nextInt();
        scanner.nextLine();
        Customer customerNew = new Customer(inputName, inputLastName, inputMembership);
        System.out.println("Cliente antes de la busqeuda: " + customerNew);
        Boolean save = customerDAO.saveCustomer(customerNew);
        if (save) {
            System.out.println("cliente agreado; " + customerNew + "\n");
            System.out.println(UserFrames.displayOutputSubTitles(""));
            allCustomers(customerDAO);
        } else {
            System.out.println("EL cliente no pudo ser agregado" + customerNew);
        }
        return false;
    }

    public boolean updateCustomer(ICustomerDAO customerDAO) {
        System.out.print("ingrese el id del cliente a modficar: ");
        int numberIdentityCustomer = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ingrese el nombre: ");
        String inputName = scanner.nextLine();
        System.out.print("ingrese el apellido: ");
        String inputLastName = scanner.nextLine();
        System.out.print("ingrese el valor de la mebresia: ");
        int inputMembership = scanner.nextInt();
        scanner.nextLine();
        Customer customerUpdate = new Customer(numberIdentityCustomer, inputName, inputLastName, inputMembership);
        Boolean updateCustomer = customerDAO.updateCustomer(customerUpdate);
        if (updateCustomer) {
            System.out.println("El cliente fue modificado; " + customerUpdate);
            System.out.println(UserFrames.displayOutputSubTitles(""));
            allCustomers(customerDAO);
        } else
            System.out.println("El cliente no puedo ser modificado" + customerUpdate);
        return false;
    }

    public boolean deleteCustomer(ICustomerDAO customerDAO) {
        System.out.print("ingrese el id del cliente: ");
        int numberIdentityCustomer = scanner.nextInt();
        scanner.nextLine();
        Customer customer = new Customer(numberIdentityCustomer);
        Boolean deleteCustomer = customerDAO.deleteCustomer(customer);
        if (deleteCustomer) {
            System.out.println("cliente eliminado; " + customer);
            System.out.println(UserFrames.displayOutputSubTitles(""));
            allCustomers(customerDAO);
        } else
            System.out.println("cliente no se pudo eliminar" + customer);
        return false;
    }

    public void showMessageExitApplication() {
        System.out.println(UserFrames.displayOutputSubTitles("Gracias por usar el servicio"));
    }


}
