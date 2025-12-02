package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Supplier;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SupplierRepository extends GenericRepository<Supplier> {
    private static final Logger logger = Logger.getLogger(SupplierRepository.class.getName());

    public SupplierRepository() {
        super(Supplier::firstName); // або contactInfo як ідентифікатор
    }

    public List<Supplier> sortByFirstName() {
        return sortByComparator(Supplier.byFirstName());
    }

    public List<Supplier> sortByContactInfo() {
        return sortByComparator(Supplier.byContactInfo());
    }

    // Stream searches
    public List<Supplier> findByFirstNameContains(String fragment) {
        logger.info("findByFirstNameContains: " + fragment);
        if (fragment == null) return List.of();
        String f = fragment.trim().toLowerCase();
        return getAll().stream()
                .filter(s -> s.firstName().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }

    public List<Supplier> findByContactInfoContains(String fragment) {
        logger.info("findByContactInfoContains: " + fragment);
        if (fragment == null) return List.of();
        String f = fragment.trim().toLowerCase();
        return getAll().stream()
                .filter(s -> s.contactInfo().toLowerCase().contains(f))
                .collect(Collectors.toList());
    }
}
