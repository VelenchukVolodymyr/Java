package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.exceptions.InvalidDataException;

public interface Validatable {
    void validate() throws InvalidDataException;
}
