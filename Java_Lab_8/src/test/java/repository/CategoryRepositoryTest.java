package repository;


import main.ua.Electronics_Store.model.Category;
import main.ua.Electronics_Store.repository.CategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CategoryRepositoryTest {

    @Test
    void testSortByName() {
        CategoryRepository repo = new CategoryRepository();

        repo.add(Category.of("Phones", "Smart devices"));
        repo.add(Category.of("TV", "Video devices"));
        repo.add(Category.of("Audio", "Sound devices"));

        List<Category> sorted = repo.sortByName();

        assertEquals("Audio", sorted.get(0).name());
        assertEquals("Phones", sorted.get(1).name());
        assertEquals("TV", sorted.get(2).name());
    }

    @Test
    void testSortByDescriptionLength() {
        CategoryRepository repo = new CategoryRepository();

        repo.add(Category.of("Phones", "Smart"));
        repo.add(Category.of("TV", "Video devices"));
        repo.add(Category.of("PC", "Computer"));

        List<Category> sorted = repo.sortByDescriptionLength();

        assertEquals("Smart", sorted.get(0).description()); // 5 символів
        assertEquals("Computer", sorted.get(1).description()); // 8 символів
        assertEquals("Video devices", sorted.get(2).description()); // 13 символів
    }

    @Test
    void testSortByDescriptionThenName() {
        CategoryRepository repo = new CategoryRepository();

        repo.add(Category.of("B", "Same"));
        repo.add(Category.of("A", "Same"));

        List<Category> sorted = repo.sortByDescriptionThenName();

        assertEquals("A", sorted.get(0).name());
        assertEquals("B", sorted.get(1).name());
    }
}
