package repository;

import main.ua.Electronics_Store.model.Customer;
import main.ua.Electronics_Store.repository.GenericRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import main.ua.Electronics_Store.model.Category;

import java.util.List;


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

    @Test
    void testAddAndGetAll() {
        GenericRepository<Category> repo = new GenericRepository<>(Category::name);

        repo.add(Category.of("Phones", "Devices"));
        repo.add(Category.of("TV", "Video"));

        assertEquals(2, repo.size());
        assertEquals(2, repo.getAll().size());
    }

    @Test
    void testFindByIdentity() {
        GenericRepository<Category> repo = new GenericRepository<>(Category::name);

        repo.add(Category.of("Phones", "Devices"));

        assertTrue(repo.findByIdentity("Phones").isPresent());
        assertFalse(repo.findByIdentity("TV").isPresent());
    }

    @Test
    void testSortByIdentityAscending() {
        GenericRepository<Category> repo = new GenericRepository<>(Category::name);

        repo.add(Category.of("B", "bbb"));
        repo.add(Category.of("A", "aaa"));

        List<Category> sorted = repo.sortByIdentity("asc");

        assertEquals("A", sorted.get(0).name());
        assertEquals("B", sorted.get(1).name());
    }

    @Test
    void testSortByIdentityDescending() {
        GenericRepository<Category> repo = new GenericRepository<>(Category::name);

        repo.add(Category.of("B", "bbb"));
        repo.add(Category.of("A", "aaa"));

        List<Category> sorted = repo.sortByIdentity("desc");

        assertEquals("B", sorted.get(0).name());
        assertEquals("A", sorted.get(1).name());
    }
}
