package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.PersonUtils;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;

public record Customer(String firstName, String lastName, String email) {

    public Customer {
        try {
            Utils.validateName(firstName);
            Utils.validateName(lastName);

            if (!PersonUtils.isValidEmail(email)) {
                throw new InvalidDataException("Invalid email: " + email);
            }
        } catch (InvalidDataException e) {
            throw new RuntimeException("Invalid customer data: " + e.getMessage(), e);
        }
    }

    public static Comparator<Customer> byFirstName() {
        return Comparator.comparing(Customer::firstName);
    }

    public static Comparator<Customer> byLastName() {
        return Comparator.comparing(Customer::lastName);
    }

    public static Comparator<Customer> byEmail() {
        return Comparator.comparing(Customer::email);
    }

}