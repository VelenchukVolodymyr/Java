package main.ua.Electronics_Store.util;

public class ValidationHelper {
    static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    static boolean isPositive(double number) {
        return number > 0;
    }

    static boolean isStringLengthBetween(String name, int min, int max) {
        if (name == null) return false;
        return name.length() >= min && name.length() <= max;
    }

    static boolean isStringMatchPattern(String value, String regex) {
        if (value == null) return false;
        return value.matches(regex);
    }


}
