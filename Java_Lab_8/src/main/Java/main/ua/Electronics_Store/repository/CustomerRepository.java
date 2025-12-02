package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Customer;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CustomerRepository extends GenericRepository<Customer> {
    private static final Logger logger = Logger.getLogger(CustomerRepository.class.getName());

    public CustomerRepository() {
        super(Customer::email); // email як ідентифікатор
    }

    public List<Customer> sortByFirstName() {
        return sortByComparator(Customer.byFirstName());
    }

    public List<Customer> sortByLastName() {
        return sortByComparator(Customer.byLastName());
    }

    public List<Customer> sortByEmail() {
        return sortByComparator(Customer.byEmail());
    }

    // --- Stream-based searches ---

    public List<Customer> findByFirstName(String firstName) {
        logger.info("findByFirstName: " + firstName);
        if (firstName == null) return List.of();
        String f = firstName.trim().toLowerCase();
        return getAll().stream()
                .filter(c -> c.firstName().toLowerCase().equals(f))
                .collect(Collectors.toList());
    }

    public List<Customer> findByLastName(String lastName) {
        logger.info("findByLastName: " + lastName);
        if (lastName == null) return List.of();
        String f = lastName.trim().toLowerCase();
        return getAll().stream()
                .filter(c -> c.lastName().toLowerCase().equals(f))
                .collect(Collectors.toList());
    }

    public List<Customer> findByEmail(String email) {
        logger.info("findByEmail: " + email);
        if (email == null) return List.of();
        String e = email.trim().toLowerCase();
        return getAll().stream()
                .filter(c -> c.email().toLowerCase().equals(e))
                .collect(Collectors.toList());
    }
}
