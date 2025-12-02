package main.ua.Electronics_Store.model;

import java.util.List;

public class DataContainer {
    private List<Category> categories;
    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;
    private List<Supplier> suppliers;

    public DataContainer() {}

    public DataContainer(List<Category> categories, List<Product> products, List<Customer> customers, List<Order> orders, List<Supplier> suppliers) {
        this.categories = categories;
        this.products = products;
        this.customers = customers;
        this.orders = orders;
        this.suppliers = suppliers;
    }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public List<Customer> getCustomers() { return customers; }
    public void setCustomers(List<Customer> customers) { this.customers = customers; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }

    public List<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(List<Supplier> suppliers) { this.suppliers = suppliers; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataContainer that = (DataContainer) o;
        return java.util.Objects.equals(categories, that.categories) &&
                java.util.Objects.equals(products, that.products) &&
                java.util.Objects.equals(customers, that.customers) &&
                java.util.Objects.equals(orders, that.orders) &&
                java.util.Objects.equals(suppliers, that.suppliers);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(categories, products, customers, orders, suppliers);
    }

    @Override
    public String toString() {
        return "DataContainer{" +
                "categories=" + categories +
                ", products=" + products +
                ", customers=" + customers +
                ", orders=" + orders +
                ", suppliers=" + suppliers +
                '}';
    }
}
