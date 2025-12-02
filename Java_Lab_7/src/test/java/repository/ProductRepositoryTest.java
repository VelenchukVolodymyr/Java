package repository;

import main.ua.Electronics_Store.repository.CategoryRepository;
import main.ua.Electronics_Store.repository.ProductRepository;
import main.ua.Electronics_Store.model.Category;
import main.ua.Electronics_Store.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    void testSortByName() {
        ProductRepository repo = new ProductRepository();

        Category electronics = Category.of("Electronics", "Devices");

        repo.add(Product.of("Phone", electronics, 800, 10));
        repo.add(Product.of("TV", electronics, 1500, 5));
        repo.add(Product.of("AirPods", electronics, 200, 30));

        List<Product> sorted = repo.sortByName();

        assertEquals("AirPods", sorted.get(0).getName());
        assertEquals("Phone",  sorted.get(1).getName());
        assertEquals("TV",     sorted.get(2).getName());
    }

    @Test
    void testSortByPrice() {
        ProductRepository repo = new ProductRepository();
        Category electronics = Category.of("Electronics", "Devices");

        repo.add(Product.of("Phone", electronics, 800, 10));
        repo.add(Product.of("TV", electronics, 1500, 5));
        repo.add(Product.of("AirPods", electronics, 200, 30));

        List<Product> sorted = repo.sortByPrice();

        assertEquals(200, sorted.get(0).getPrice());
        assertEquals(800, sorted.get(1).getPrice());
        assertEquals(1500, sorted.get(2).getPrice());
    }

    @Test
    void testSortByStockDescending() {
        ProductRepository repo = new ProductRepository();
        Category electronics = Category.of("Electronics", "Devices");

        repo.add(Product.of("Phone", electronics, 800, 10));
        repo.add(Product.of("TV", electronics, 1500, 5));
        repo.add(Product.of("AirPods", electronics, 200, 30));

        List<Product> sorted = repo.sortByStock();

        assertEquals(30, sorted.get(0).getStock());
        assertEquals(10, sorted.get(1).getStock());
        assertEquals(5,  sorted.get(2).getStock());
    }
}
