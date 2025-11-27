package com.university.lab1;

import com.university.models.*;

public class Lab1Main {
    public static void main(String[] args) {
        System.out.println("LAB 1: Classes, Inheritance, Access Modifiers");
        
        System.out.println("\n1. Creating objects using constructor:");
        Student s1 = new Student("John", "Doe", "CS123456");
        System.out.println("   " + s1);
        
        System.out.println("\n2. Creating objects using static factory method:");
        Student s2 = Student.of("Jane", "Smith", "PH654321");
        Professor p1 = Professor.of("Albert", "Einstein", "Physics");
        Course c1 = Course.of("Data Structures", 4);
        System.out.println("   " + s2);
        System.out.println("   " + p1);
        System.out.println("   " + c1);
        
        System.out.println("\n3. Testing validation (successful):");
        try {
            Student s3 = new Student("  Alice  ", "  Johnson  ", "MA111111");
            System.out.println("   Created with spaces: " + s3);
            System.out.println("   Name formatted: " + s3.getFullName());
        } catch (IllegalArgumentException e) {
            System.out.println("   Error: " + e.getMessage());
        }
        
        System.out.println("\n4. Testing validation (failures):");
        
        try {
            Student invalid1 = new Student("J", "Doe", "CS123456");
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught: " + e.getMessage());
        }
        
        try {
            Student invalid2 = new Student("John", "Doe", "INVALID");
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught: " + e.getMessage());
        }
        
        try {
            Course invalid3 = new Course("Test", 15);
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught: " + e.getMessage());
        }
        
        System.out.println("\n5. Testing equals() and hashCode():");
        Student s4 = new Student("John", "Doe", "CS123456");
        System.out.println("   s1.equals(s4): " + s1.equals(s4));
        System.out.println("   s1.hashCode() == s4.hashCode(): " + (s1.hashCode() == s4.hashCode()));
        System.out.println("   s1 == s4 (reference): " + (s1 == s4));
        
        Professor p2 = new Professor("Albert", "Einstein", "Physics");
        System.out.println("   p1.equals(p2): " + p1.equals(p2));
        
        Course c2 = Course.of("Data Structures", 4);
        System.out.println("   c1.equals(c2): " + c1.equals(c2));
        
        System.out.println("\n6. Testing inheritance (Person base class):");
        System.out.println("   s1 instanceof Person: " + (s1 instanceof Person));
        System.out.println("   p1 instanceof Person: " + (p1 instanceof Person));
        System.out.println("   Student and Professor extend Person");
        System.out.println("   Course is standalone class");
        
        System.out.println("\n7. Protected members (accessible in subclass):");
        System.out.println("   Student uses protected getFormattedInfo(): " + s1);
        System.out.println("   Professor uses protected getFormattedInfo(): " + p1);
        
        System.out.println("\n8. Access Modifiers Summary:");
        System.out.println("   - private: fields (firstName, lastName, studentId, etc.)");
        System.out.println("   - protected: Person fields and getFormattedInfo() method");
        System.out.println("   - package-private: ValidationHelper class (ua.util)");
        System.out.println("   - public: Utils class, all getters, constructors, factory methods");
        
        System.out.println("\n9. Package ua.util:");
        System.out.println("   - ValidationHelper: package-private (not accessible here)");
        System.out.println("   - Utils: public (uses ValidationHelper internally)");
        System.out.println("   - All validation goes through Utils class");
        
        System.out.println("\n======================================================================");
        System.out.println("LAB 1 COMPLETE!");
        System.out.println("======================================================================");
    }
}
