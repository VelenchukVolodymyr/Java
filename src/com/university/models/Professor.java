package com.university.models;

import ua.util.Utils;

public class Professor extends Person {
    private String department;
    
    public Professor(String firstName, String lastName, String department) {
        super(firstName, lastName);  
        Utils.validateDepartment(department);
        this.department = department.trim();
    }
    
    public static Professor of(String firstName, String lastName, String department) {
        return new Professor(firstName, lastName, department);
    }
    
    public String getDepartment() { return department; }
    
    @Override
    public String toString() {
        return "Professor[" + getFormattedInfo() + ", " + department + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Professor)) return false;
        Professor other = (Professor) obj;
        return super.equals(obj) && department.equals(other.department);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() + department.hashCode();
    }
}
