package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.Utils;

public record Supplier(String firstName, String contactInfo) {

    public Supplier {
        Utils.validateName(firstName);
        Utils.validateName(contactInfo);
    }

    public static Supplier of(String firstName, String contactInfo) throws InvalidDataException {
        return new Supplier(firstName, contactInfo);
    }
}
