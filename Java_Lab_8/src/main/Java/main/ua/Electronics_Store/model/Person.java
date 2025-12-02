package main.ua.Electronics_Store.model;

import main.ua.Electronics_Store.util.PersonUtils;
import main.ua.Electronics_Store.exceptions.InvalidDataException;

import java.util.Objects;

public class Person {
    protected String firstName;
    protected String lastName;
    protected String email;

    public Person() {
    }

    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    protected String getFullName() {
        return PersonUtils.formatName(firstName, lastName);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!PersonUtils.isValidName(firstName)) {
            throw new RuntimeException(new InvalidDataException("firstName: invalid or empty"));
        }
        this.firstName = PersonUtils.capitalizeText(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!PersonUtils.isValidName(lastName)) {
            throw new RuntimeException(new InvalidDataException("lastName: invalid or empty"));
        }
        this.lastName = PersonUtils.capitalizeText(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new RuntimeException(new InvalidDataException("email: cannot be null"));
        String formattedEmail = PersonUtils.formatEmail(email);
        if (!PersonUtils.isValidEmail(formattedEmail)) throw new RuntimeException(new InvalidDataException("email: invalid format"));
        this.email = formattedEmail;
    }

    public static Person createPerson(String firstName, String lastName) {
        if (PersonUtils.isValidName(firstName) &&
                PersonUtils.isValidName(lastName)) {
            String email = PersonUtils.generateEmailFromNames(firstName, lastName);
            return new Person(firstName, lastName, email);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}