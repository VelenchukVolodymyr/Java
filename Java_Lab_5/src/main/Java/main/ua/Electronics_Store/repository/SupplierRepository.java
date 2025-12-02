package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Supplier;
import java.util.List;

public class SupplierRepository extends GenericRepository<Supplier> {

    public SupplierRepository() {
        super(Supplier::firstName); // або contactInfo як ідентифікатор
    }

    public List<Supplier> sortByFirstName() {
        return sortByComparator(Supplier.byFirstName());
    }

    public List<Supplier> sortByContactInfo() {
        return sortByComparator(Supplier.byContactInfo());
    }
}
