package main.ua.Electronics_Store.repository;

import main.ua.Electronics_Store.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {

    @Test
    void findByDateRangeAndCustomerEmail_shouldWork() {
        OrderRepository repo = new OrderRepository();

        Category c = Category.of("Electronics", "Devices");
        Product p1 = Product.of("Phone", c, 300, 10);
        Product p2 = Product.of("Laptop", c, 1200, 5);

        Customer anna = new Customer("Anna", "Petrenko", "anna@example.com");
        Customer ivan = new Customer("Ivan", "Kovalenko", "ivan@example.com");

        Order o1 = Order.of(anna, List.of(p1), LocalDate.of(2025,1,1));
        Order o2 = Order.of(ivan, List.of(p2), LocalDate.of(2025,6,1));

        repo.add(o1);
        repo.add(o2);

        List<Order> janToMar = repo.findByDateRange(LocalDate.of(2025,1,1), LocalDate.of(2025,3,31));
        assertEquals(1, janToMar.size());

        List<Order> byEmail = repo.findByCustomerEmail("ivan@example.com");
        assertEquals(1, byEmail.size());

        List<String> productNames = repo.allProductNamesFromOrders();
        assertTrue(productNames.contains("Phone") || productNames.contains("Laptop"));
    }
}
