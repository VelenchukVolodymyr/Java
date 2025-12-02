package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

import java.util.Objects;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        Utils.validateName(name);
        this.name = name;
        this.description = description;
    }

    // Factory method
    public static Category of(String name, String description) {
        return new Category(name, description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Utils.validateName(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{name='%s', description='%s'}"
                .formatted(name, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name) &&
                Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
