package ua.util;

// Package-private class - accessible only within ua.util package
class ValidationHelper {
    
    // Package-private methods
    static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }
    
    static boolean isValidStudentId(String studentId) {
        return studentId != null && studentId.matches("^[A-Z]{2}\\d{6}$");
    }
    
    static boolean isValidCredits(int credits) {
        return credits >= 1 && credits <= 10;
    }
    
    static boolean isValidDepartment(String department) {
        return department != null && !department.trim().isEmpty();
    }
}
