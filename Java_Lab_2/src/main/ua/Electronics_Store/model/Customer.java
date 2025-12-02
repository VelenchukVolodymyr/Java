package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.Utils;

public record Customer(String firstName,String lastName,String email ) {

    public Customer {
        Utils.validateName(firstName);
        Utils.validateName(lastName);
        Utils.validateName(email);
    }


    public static Customer of(String firstName, String lastName, String email) {
        return new Customer(firstName, lastName, email);
    }

}

