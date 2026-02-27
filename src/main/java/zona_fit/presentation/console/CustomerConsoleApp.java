package zona_fit.presentation.console;


import zona_fit.data.CustomerDAO;
import zona_fit.data.ICustomerDAO;
import zona_fit.domain.Customer;
import zona_fit.utilities.UserFrames;
import zona_fit.service.CustomerService;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class CustomerConsoleApp {
    Scanner scanner = new Scanner(System.in);
    private final CustomerService customerService;

    public CustomerConsoleApp() {
        ICustomerDAO customerDAO = new CustomerDAO();
        this.customerService = new CustomerService(customerDAO);
    }

    public static void main(String[] args) {
        CustomerConsoleApp consoleApp = new CustomerConsoleApp();

        final int NUMBER_OPTIONS_MAX = 6;
        int option = 0;
        while (option != NUMBER_OPTIONS_MAX) {
            consoleApp.showMenu();
            option = Integer.parseInt(consoleApp.scanner.nextLine());


            switch (option) {
                case 1 -> consoleApp.allCustomers();
                case 2 -> consoleApp.findByIdCustomer();
                case 3 -> consoleApp.saveCustomer();
                case 4 -> consoleApp.updateCustomer();
                case 5 -> consoleApp.deleteCustomer();
                case 6 -> consoleApp.showMessageExitApplication();
                default ->
                        System.out.println(UserFrames.displayOutputSubTitles("Opcion invalida, Gracias por usar el servicio"));
            }
        }
    }

    public void showMenu() {
        System.out.println("------------ Menu ----------");
        System.out.println(UserFrames.displayOutputSubTitles(
                """
                        # 1. Listar todos los clientes.\s
                        # 2. Buscar cliente por id.\s
                        # 3. Agregar cliente.\s
                        # 4. Modificar cliente.\s
                        # 5. Eliminar cliente.\s
                        # 6. Salir\s
                        """));
        System.out.print("digite una opcion : ");
    }

    public void allCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (!customers.isEmpty()) {
            customers.forEach(System.out::println);
        } else {
            System.out.println("No hay clientes registrados.");
        }
    }

    public void findByIdCustomer() {
        System.out.print("ingrese el id del cliente: ");
        long numberIdentityCustomer = Long.parseLong(scanner.nextLine());
        Optional<Customer> customer = customerService.getFindByIdCustomer(numberIdentityCustomer);
        if (customer.isPresent()) {
            System.out.println("Cliente encontrado: " + customer.get());
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public void saveCustomer() {
        System.out.print("ingrese el nombre: ");
        String inputName = scanner.nextLine();
        System.out.print("ingrese el apellido: ");
        String inputLastName = scanner.nextLine();
        System.out.print("ingrese el valor de la mebresia: ");
        int inputMembership = Integer.parseInt(scanner.nextLine());
        Customer saved = customerService.saveCustomer(inputName, inputLastName, inputMembership);
        System.out.println("Cliente creado de manera exitosa: " + saved);
    }

    public void updateCustomer() {
        System.out.print("ingrese el id del cliente a modficar: ");
        int numberIdentityCustomer = Integer.parseInt(scanner.nextLine());
        System.out.print("ingrese el nombre: ");
        String inputName = scanner.nextLine();
        System.out.print("ingrese el apellido: ");
        String inputLastName = scanner.nextLine();
        System.out.print("ingrese el valor de la mebresia: ");
        int inputMembership = Integer.parseInt(scanner.nextLine());
        Customer customerUpdate = customerService.updateCustomer (numberIdentityCustomer, inputName, inputLastName
                , inputMembership);
        System.out.println("Cliente actuliazdo con exito: " + customerUpdate);
    }

    public void deleteCustomer() {
        System.out.print("ingrese el id del cliente: ");
        long numberIdentityCustomer = scanner.nextLong();
        scanner.nextLine();
        customerService.deleteCustomer(numberIdentityCustomer);
        System.out.println("Cliente con el id: " + numberIdentityCustomer  + " fue elemindado con exito: ");
    }

    public void showMessageExitApplication() {
        System.out.println(UserFrames.displayOutputSubTitles("Gracias por usar el servicio"));
    }


}
