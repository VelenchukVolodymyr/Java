package main.ua.Electronics_Store;

import main.ua.Electronics_Store.model.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Створення категорій ===");
        // Через конструктор
        Category electronics = new Category("Electronics", "Gadgets and devices");
        System.out.println(electronics);

        // Через factory
        Category books = Category.of("Books", "All kinds of books");
        System.out.println(books);

        System.out.println("\n=== Створення продуктів ===");
        Product laptop = new Product("Laptop", electronics, 1200.0, 5);
        Product phone = Product.of("Smartphone", electronics, 800.0, 10);
        System.out.println(laptop);
        System.out.println(phone);

        System.out.println("\n=== Створення постачальників ===");
        Supplier supplier1 = new Supplier("TechCorp", "techcorp@example.com");
        Supplier supplier2 = Supplier.of("BookStore", "books@example.com");
        System.out.println(supplier1);
        System.out.println(supplier2);

        System.out.println("\n=== Створення клієнтів ===");
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        Customer customer2 = Customer.of("Jane", "Smith", "jane.smith@example.com");
        System.out.println(customer1);
        System.out.println(customer2);

        System.out.println("\n=== Створення замовлення ===");
        List<Product> products = new ArrayList<>();
        products.add(laptop);
        products.add(phone);

        Order order = new Order(customer1, products, LocalDate.now());
        System.out.println(order);

        System.out.println("\n=== Демонстрація валідації ===");
        try {
            Product invalidProduct = new Product("", electronics, -100, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення продукту: " + e.getMessage());
        }

        try {
            Customer invalidCustomer = new Customer("Alice", "", "aliceexample.com");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення клієнта: " + e.getMessage());
        }
    }
}
