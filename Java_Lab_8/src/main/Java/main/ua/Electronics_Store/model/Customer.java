package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.PersonUtils;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;

import main.ua.Electronics_Store.model.Validatable;
import main.ua.Electronics_Store.util.ValidationHelper;

public record Customer(String firstName, String lastName, String email) implements Validatable {

    public Customer {
        StringBuilder sb = new StringBuilder();
        boolean firstEmpty = !ValidationHelper.isNonEmpty(firstName);
        boolean lastEmpty = !ValidationHelper.isNonEmpty(lastName);
        boolean emailInvalid = !PersonUtils.isValidEmail(email);

        if (firstEmpty) sb.append("firstName: cannot be empty; ");
        if (lastEmpty) sb.append("lastName: cannot be empty; ");
        if (emailInvalid) sb.append("email: invalid format; ");

        if (sb.length() > 0) {
            // preserve previous behavior for single-name errors
            if ((firstEmpty || lastEmpty) && !emailInvalid) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            if (emailInvalid && !firstEmpty && !lastEmpty) {
                throw new RuntimeException(new InvalidDataException("Invalid email: " + email));
            }
            throw new RuntimeException(new InvalidDataException(sb.toString()));
        }
    }

    @Override
    public void validate() throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        if (!ValidationHelper.isNonEmpty(firstName)) sb.append("firstName: cannot be empty; ");
        if (!ValidationHelper.isNonEmpty(lastName)) sb.append("lastName: cannot be empty; ");
        if (!PersonUtils.isValidEmail(email)) sb.append("email: invalid format; ");
        if (sb.length() > 0) throw new InvalidDataException(sb.toString());
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