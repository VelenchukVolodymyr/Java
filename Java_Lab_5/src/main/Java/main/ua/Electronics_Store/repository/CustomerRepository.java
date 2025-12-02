package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Customer;
import java.util.List;

public class CustomerRepository extends GenericRepository<Customer> {

    public CustomerRepository() {
        super(Customer::email); // email як ідентифікатор
    }

    public List<Customer> sortByFirstName() {
        return sortByComparator(Customer.byFirstName());
    }

    public List<Customer> sortByLastName() {
        return sortByComparator(Customer.byLastName());
    }

    public List<Customer> sortByEmail() {
        return sortByComparator(Customer.byEmail());
    }
}
