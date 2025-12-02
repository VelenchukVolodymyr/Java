package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Order;
import java.util.List;

public class OrderRepository extends GenericRepository<Order> {

    public OrderRepository() {
        super(order -> order.getCustomer().email()); // ідентифікатор — email покупця
    }

    public List<Order> sortByDate() {
        return sortByComparator(Order.byDate());
    }

    public List<Order> sortByCustomerName() {
        return sortByComparator(Order.byCustomerName());
    }
}
