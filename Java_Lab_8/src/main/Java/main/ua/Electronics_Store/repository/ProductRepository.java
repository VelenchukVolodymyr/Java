package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Product;
import main.ua.Electronics_Store.model.Category;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ProductRepository extends GenericRepository<Product> {

    private static final Logger logger = Logger.getLogger(ProductRepository.class.getName());

    public ProductRepository() {
        super(Product::getName); // identity — ім'я
    }

    public List<Product> sortByName() {
        return sortByComparator(Product.byName());
    }

    public List<Product> sortByPrice() {
        return sortByComparator(Product.byPrice());
    }

    public List<Product> sortByStock() {
        // Test-suite expects stock sorted descending (most stock first)
        return sortByComparator(Product.byStock().reversed());
    }

    public List<Product> sortByCategory() {
        return sortByComparator(Product.byCategory());
    }

    public List<Product> sortByStatus() {
        return sortByComparator(Product.byStatus());
    }

    // --- Stream-based searches ---

    public List<Product> findByName(String name) {
        logger.info("findByName: " + name);
        if (name == null) return List.of();
        String n = name.trim().toLowerCase();
        return getAll().stream()
                .filter(p -> p.getName().toLowerCase().equals(n))
                .collect(Collectors.toList());
    }

    public List<Product> findByNameContains(String fragment) {
        logger.info("findByNameContains: " + fragment);
        if (fragment == null) return List.of();
        String f = fragment.trim().toLowerCase();
        return getAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    public List<Product> findByPriceRange(double min, double max) {
        logger.info("findByPriceRange: " + min + " - " + max);
        return getAll().stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Product> findByCategoryName(String categoryName) {
        logger.info("findByCategoryName: " + categoryName);
        if (categoryName == null) return List.of();
        String cn = categoryName.trim().toLowerCase();
        return getAll().stream()
                .filter(p -> {
                    Category c = p.getCategory();
                    return c != null && c.name().toLowerCase().equals(cn);
                })
                .collect(Collectors.toList());
    }

    public double totalValue() {
        return getAll().stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
}
