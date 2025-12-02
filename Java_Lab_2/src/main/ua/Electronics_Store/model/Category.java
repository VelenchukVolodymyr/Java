package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

public record Category(String name, String description) {

    public Category {
        Utils.validateName(name);
    }

    public static Category of(String name, String description) {
        return new Category(name, description);
    }
}
