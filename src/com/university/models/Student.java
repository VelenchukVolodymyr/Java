package com.university.models;

import ua.util.Utils;

public class Student extends Person {
    private String studentId;
    
    public Student(String firstName, String lastName, String studentId) {
        super(firstName, lastName);
        Utils.validateStudentId(studentId);
        this.studentId = studentId;
    }
    
    public static Student of(String firstName, String lastName, String studentId) {
        return new Student(firstName, lastName, studentId);
    }
    
    public String getStudentId() { 
        return studentId; 
    }
    
    @Override
    public String toString() {
        return "Student[" + studentId + ", " + getFormattedInfo() + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return studentId.equals(other.studentId);
    }
    
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}
