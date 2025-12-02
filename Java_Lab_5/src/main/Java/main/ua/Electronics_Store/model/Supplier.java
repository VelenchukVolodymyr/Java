package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;

public record Supplier(String firstName, String contactInfo) {

    public Supplier {
        Utils.validateName(firstName);
        Utils.validateName(contactInfo);
    }

    public static Supplier of(String firstName, String contactInfo) throws InvalidDataException {
        return new Supplier(firstName, contactInfo);
    }

    public static Comparator<Supplier> byFirstName() {
        return Comparator.comparing(Supplier::firstName);
    }

    public static Comparator<Supplier> byContactInfo() {
        return Comparator.comparing(Supplier::contactInfo);
    }

}
