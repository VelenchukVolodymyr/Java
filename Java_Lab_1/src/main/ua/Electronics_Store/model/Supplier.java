package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

import java.util.Objects;

public class Supplier extends Person {

    private String contactInfo;

    // Конструктор
    public Supplier(String firstName, String contactInfo) {
        Utils.validateName(firstName);
        Utils.validateName(contactInfo);
        this.firstName = firstName;
        this.contactInfo = contactInfo;
    }

    // Factory-метод
    public static Supplier of(String firstName, String contactInfo) {
        return new Supplier(firstName, contactInfo);
    }

    // Getters/Setters
    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        Utils.validateName(firstName);
        this.firstName = firstName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        Utils.validateName(contactInfo);
        this.contactInfo = contactInfo;
    }

    // toString
    @Override
    public String toString() {
        return "Supplier{firstName='%s', contactInfo='%s'}".formatted(firstName, contactInfo);
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier supplier)) return false;
        return Objects.equals(firstName, supplier.firstName) &&
                Objects.equals(contactInfo, supplier.contactInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, contactInfo);
    }
}
