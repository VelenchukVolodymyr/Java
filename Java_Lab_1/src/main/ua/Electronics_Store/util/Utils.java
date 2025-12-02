package main.ua.Electronics_Store.util;

public class Utils {
    public static void validateName(String name) {
        if (!ValidationHelper.isNonEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validatePrice(double price) {
        if (!ValidationHelper.isPositive(price)) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
}
