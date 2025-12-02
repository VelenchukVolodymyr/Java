package repository;

import main.ua.Electronics_Store.model.Customer;
import main.ua.Electronics_Store.repository.GenericRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericRepositoryTest {

    @Test
    void testAddAndFindByIdentity() {
        GenericRepository<Customer> repo = new GenericRepository<>(Customer::email);
        Customer anna = new Customer("Anna", "Petrenko", "anna.petrenko@gmail.com");
        repo.add(anna);

        assertTrue(repo.findByIdentity("anna.petrenko@gmail.com").isPresent());
    }

    @Test
    void testRemove() {
        GenericRepository<Customer> repo = new GenericRepository<>(Customer::email);
        Customer ivan = new Customer("Ivan", "Kovalenko", "ivan.kovalenko@gmail.com");
        repo.add(ivan);
        repo.remove(ivan);

        assertTrue(repo.getAll().isEmpty());
    }
}
