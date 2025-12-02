package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Category;
import java.util.List;

public class CategoryRepository extends GenericRepository<Category> {

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
}
