package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;

import main.ua.Electronics_Store.model.Validatable;
import main.ua.Electronics_Store.util.ValidationHelper;
import main.ua.Electronics_Store.exceptions.InvalidDataException;

public record Supplier(String firstName, String contactInfo) implements Validatable {

    public Supplier {
        StringBuilder sb = new StringBuilder();
        if (!ValidationHelper.isNonEmpty(firstName)) sb.append("firstName: cannot be empty; ");
        if (!ValidationHelper.isNonEmpty(contactInfo)) sb.append("contactInfo: cannot be empty; ");
        if (sb.length() > 0) throw new RuntimeException(new InvalidDataException(sb.toString()));
    }

    public static Supplier of(String firstName, String contactInfo) throws InvalidDataException {
        // factory that throws InvalidDataException directly
        StringBuilder sb = new StringBuilder();
        if (!ValidationHelper.isNonEmpty(firstName)) sb.append("firstName: cannot be empty; ");
        if (!ValidationHelper.isNonEmpty(contactInfo)) sb.append("contactInfo: cannot be empty; ");
        if (sb.length() > 0) throw new InvalidDataException(sb.toString());
        return new Supplier(firstName, contactInfo);
    }

    public static Comparator<Supplier> byFirstName() {
        return Comparator.comparing(Supplier::firstName);
    }

    public static Comparator<Supplier> byContactInfo() {
        return Comparator.comparing(Supplier::contactInfo);
    }

    @Override
    public void validate() throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        if (!ValidationHelper.isNonEmpty(firstName)) sb.append("firstName: cannot be empty; ");
        if (!ValidationHelper.isNonEmpty(contactInfo)) sb.append("contactInfo: cannot be empty; ");
        if (sb.length() > 0) throw new InvalidDataException(sb.toString());
    }

}
