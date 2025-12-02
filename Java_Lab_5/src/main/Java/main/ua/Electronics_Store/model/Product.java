package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

import java.util.Objects;

import java.util.Comparator;

public class Product {
    private String name;
    private Category category;
    private double price;
    private int stock;

    public enum ProductStatus {
        IN_STOCK,
        OUT_OF_STOCK,
        DISCONTINUED
    }

    private ProductStatus status;

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductStatus getStatus() {
        return status;
    }


    // Конструктор з валідацією
    public Product(String name, Category category, double price, int stock) {
        Utils.validateName(name);
        Utils.validatePrice(price);
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = ProductStatus.OUT_OF_STOCK; // значення за замовчуванням
    }

    // Factory-метод
    public static Product of(String name, Category category, double price, int stock) {
        return new Product(name, category, price, stock);
    }

    // Getters + Setters з валідацією
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Utils.validateName(name);
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        Utils.validatePrice(price);
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // toString
    @Override
    public String toString() {
        return "Product{name='%s', category='%s', price=%.2f, stock=%d}"
                .formatted(name, category, price, stock);
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Double.compare(product.price, price) == 0 &&
                stock == product.stock &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price, stock);
    }

    // Сортування за імʼям товару
    public static Comparator<Product> byName() {
        return Comparator.comparing(Product::getName);
    }

    // Сортування за ціною
    public static Comparator<Product> byPrice() {
        return Comparator.comparingDouble(Product::getPrice);
    }

    // Сортування за кількістю на складі
    public static Comparator<Product> byStock() {
        return Comparator.comparingInt(Product::getStock);
    }

    // Сортування за назвою категорії
    public static Comparator<Product> byCategory() {
        return Comparator.comparing(p -> p.getCategory().name());
    }

    // Сортування за статусом (IN_STOCK → OUT_OF_STOCK → DISCONTINUED)
    public static Comparator<Product> byStatus() {
        return Comparator.comparing(Product::getStatus);
    }
}
