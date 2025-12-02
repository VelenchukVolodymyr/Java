package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest {

    @Test
    void findByNameAndContains_shouldWork() {
        CategoryRepository repo = new CategoryRepository();
        repo.add(Category.of("Electronics", "Devices and gadgets"));
        repo.add(Category.of("Home", "Home appliances"));

        List<Category> r1 = repo.findByName("Electronics");
        assertEquals(1, r1.size());

        List<Category> r2 = repo.findByNameContains("home");
        assertEquals(1, r2.size());

        List<Category> r3 = repo.findByDescriptionContains("gadgets");
        assertEquals(1, r3.size());

        int totalLen = repo.totalDescriptionLength();
        assertTrue(totalLen > 0);
    }
}
