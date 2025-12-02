package main.ua.Electronics_Store.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private Customer customer;
    private List<Product> products;
    private LocalDate orderDate;

    public Order(Customer customer, List<Product> products, LocalDate orderDate) {
        if (customer == null || products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Order must have customer and at least one product");
        }
        this.customer = customer;
        this.products = products;
        this.orderDate = orderDate;
    }

    public static Order of(Customer customer, List<Product> products, LocalDate orderDate) {
        return new Order(customer, products, orderDate);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{customer=%s, products=%s, date=%s}"
                .formatted(customer, products, orderDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(customer, order.customer) &&
                Objects.equals(products, order.products) &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, products, orderDate);
    }
}
