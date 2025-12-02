package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.util.Utils;

import java.util.Comparator;


public record Category(String name, String description) implements Comparable<Category> {

    public Category {
        Utils.validateName(name);
        Utils.validateName(description);
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
}
