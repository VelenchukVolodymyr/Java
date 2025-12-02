package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.Order;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class OrderRepository extends GenericRepository<Order> {
    private static final Logger logger = Logger.getLogger(OrderRepository.class.getName());

    public OrderRepository() {
        super(order -> order.getCustomer().email()); // ідентифікатор — email покупця
    }

    public List<Order> sortByDate() {
        return sortByComparator(Order.byDate());
    }

    public List<Order> sortByCustomerName() {
        return sortByComparator(Order.byCustomerName());
    }

    // Stream-based search by date range (inclusive)
    public List<Order> findByDateRange(LocalDate from, LocalDate to) {
        logger.info("findByDateRange: " + from + " - " + to);
        if (from == null || to == null) return List.of();
        return getAll().stream()
                .filter(o -> {
                    LocalDate d = o.getOrderDate();
                    return (d.isEqual(from) || d.isAfter(from)) && (d.isEqual(to) || d.isBefore(to));
                })
                .collect(Collectors.toList());
    }

    public List<Order> findByCustomerEmail(String email) {
        logger.info("findByCustomerEmail: " + email);
        if (email == null) return List.of();
        String e = email.trim().toLowerCase();
        return getAll().stream()
                .filter(o -> o.getCustomer() != null && o.getCustomer().email().toLowerCase().equals(e))
                .collect(Collectors.toList());
    }

    // Example of flatMap: get all products from orders
    public List<String> allProductNamesFromOrders() {
        return getAll().stream()
                .flatMap(o -> o.getProducts().stream())
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());
    }
}
