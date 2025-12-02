package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Category;
import main.ua.Electronics_Store.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    @Test
    void findByPriceRangeAndCategory_shouldWork() {
        ProductRepository repo = new ProductRepository();
        Category c = Category.of("Electronics", "Devices");
        repo.add(Product.of("Phone", c, 300, 10));
        repo.add(Product.of("Laptop", c, 1200, 5));

        List<Product> cheap = repo.findByPriceRange(100, 500);
        assertEquals(1, cheap.size());

        List<Product> byCat = repo.findByCategoryName("electronics");
        assertEquals(2, byCat.size());

        double total = repo.totalValue();
        assertTrue(total > 0);
    }
}
