package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Category;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CategoryRepository extends GenericRepository<Category> {
    private static final Logger logger = Logger.getLogger(CategoryRepository.class.getName());

    public CategoryRepository() {
        // Використовуємо name як "ідентифікатор" для GenericRepository
        super(Category::name);
    }

    // Сортування за назвою
    public List<Category> sortByName() {
        return sortByComparator(Category.byName());
    }

    // Сортування за описом
    public List<Category> sortByDescription() {
        return sortByComparator(Category.byDescription());
    }

    // Сортування за довжиною опису
    public List<Category> sortByDescriptionLength() {
        return sortByComparator(Category.byDescriptionLength());
    }

    // Спочергове сортування: спочатку за описом, потім за назвою
    public List<Category> sortByDescriptionThenName() {
        return sortByComparator(Category.byDescriptionThenName());
    }

    // --- Stream-based searches ---

    public List<Category> findByName(String name) {
        logger.info("findByName called with: " + name);
        if (name == null) return List.of();
        return getAll().stream()
                .filter(c -> c.name().equalsIgnoreCase(name.trim()))
                .collect(Collectors.toList());
    }

    public List<Category> findByNameContains(String fragment) {
        logger.info("findByNameContains called with: " + fragment);
        if (fragment == null) return List.of();
        String f = fragment.trim().toLowerCase();
        return getAll().stream()
                .filter(c -> c.name().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    public List<Category> findByDescriptionContains(String fragment) {
        logger.info("findByDescriptionContains called with: " + fragment);
        if (fragment == null) return List.of();
        String f = fragment.trim().toLowerCase();
        return getAll().stream()
                .filter(c -> c.description().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    public int totalDescriptionLength() {
        return getAll().stream()
                .map(Category::description)
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
    }
}
