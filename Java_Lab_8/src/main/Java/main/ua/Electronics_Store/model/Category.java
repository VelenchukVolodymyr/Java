package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;


import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.model.Validatable;
import main.ua.Electronics_Store.util.ValidationHelper;


public record Category(String name, String description) implements Comparable<Category>, Validatable {

    public Category {
        // validate in constructor
        StringBuilder sb = new StringBuilder();
        boolean nameEmpty = !ValidationHelper.isNonEmpty(name);
        boolean descEmpty = !ValidationHelper.isNonEmpty(description);
        if (nameEmpty) sb.append("name: cannot be empty; ");
        if (descEmpty) sb.append("description: cannot be empty; ");
        if (sb.length() > 0) {
            // keep previous behavior for single name error
            if (nameEmpty && !descEmpty) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            throw new RuntimeException(new InvalidDataException(sb.toString()));
        }
    }

    public static Category of(String name, String description) {
        return new Category(name, description);
    }

    @Override
    public int compareTo(Category o) {
        return this.name().compareTo(o.name());
    }

    // --- COMPARATORS ---

    public static Comparator<Category> byName() {
        return Comparator.comparing(Category::name);
    }

    public static Comparator<Category> byDescription() {
        return Comparator.comparing(Category::description);
    }

    public static Comparator<Category> byDescriptionLength() {
        return Comparator.comparingInt(c -> c.description().length());
    }

    public static Comparator<Category> byDescriptionThenName() {
        return Comparator.comparing(Category::description)
                .thenComparing(Category::name);
    }

    @Override
    public void validate() throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        if (!ValidationHelper.isNonEmpty(name)) sb.append("name: cannot be empty; ");
        if (!ValidationHelper.isNonEmpty(description)) sb.append("description: cannot be empty; ");
        if (sb.length() > 0) throw new InvalidDataException(sb.toString());
    }
}
