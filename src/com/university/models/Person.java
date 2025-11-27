package com.university.models;

import ua.util.Utils;

public abstract class Person {
    protected String firstName;  
    protected String lastName;
    
    protected Person(String firstName, String lastName) {
        Utils.validateName(firstName, "First name");
        Utils.validateName(lastName, "Last name");
        
        this.firstName = Utils.formatName(firstName);
        this.lastName = Utils.formatName(lastName);
    }
    
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    protected String getFormattedInfo() {
        return firstName + " " + lastName;
    }
    
    @Override
    public abstract String toString();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return firstName.equals(other.firstName) && 
               lastName.equals(other.lastName);
    }
    
    @Override
    public int hashCode() {
        return (firstName + lastName).hashCode();
    }
}
