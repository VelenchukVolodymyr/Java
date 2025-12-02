package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Customer;
import main.ua.Electronics_Store.model.Category;
import main.ua.Electronics_Store.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepositoryTest {

    @Test
    void findByFirstLastEmail_shouldWork() {
        CustomerRepository repo = new CustomerRepository();

        Customer c1 = new Customer("Anna", "Petrenko", "anna@example.com");
        Customer c2 = new Customer("Ivan", "Kovalenko", "ivan@example.com");

        repo.add(c1);
        repo.add(c2);

        List<Customer> byFirst = repo.findByFirstName("Anna");
        assertEquals(1, byFirst.size());

        List<Customer> byLast = repo.findByLastName("Kovalenko");
        assertEquals(1, byLast.size());

        List<Customer> byEmail = repo.findByEmail("ivan@example.com");
        assertEquals(1, byEmail.size());
    }
}
