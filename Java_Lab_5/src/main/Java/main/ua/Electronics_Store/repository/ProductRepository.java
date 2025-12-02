package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Product;
import java.util.List;


public class ProductRepository extends GenericRepository<Product> {

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
        return sortByComparator(Product.byStock());
    }

    public List<Product> sortByCategory() {
        return sortByComparator(Product.byCategory());
    }

    public List<Product> sortByStatus() {
        return sortByComparator(Product.byStatus());
    }
}
