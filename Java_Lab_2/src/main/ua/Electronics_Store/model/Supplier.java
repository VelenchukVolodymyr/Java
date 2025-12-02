package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

public record Supplier(String firstName,String contactInfo){

    public Supplier {
        Utils.validateName(firstName);
        Utils.validateName(contactInfo);
    }

    // Factory-метод
    public static Supplier of(String firstName, String contactInfo) {
        return new Supplier(firstName, contactInfo);
    }

}
