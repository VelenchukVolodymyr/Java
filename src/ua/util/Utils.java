package ua.util;

// Public class - accessible from anywhere
public class Utils {
    
    // Public methods that use package-private ValidationHelper
    public static void validateName(String name, String fieldName) {
        if (!ValidationHelper.isValidName(name)) {
            throw new IllegalArgumentException(
                fieldName + " must be at least 2 characters and not empty"
            );
        }
    }
    
    public static void validateStudentId(String studentId) {
        if (!ValidationHelper.isValidStudentId(studentId)) {
            throw new IllegalArgumentException(
                "Student ID must be in format XX123456 (2 uppercase letters + 6 digits)"
            );
        }
    }
    
    public static void validateCredits(int credits) {
        if (!ValidationHelper.isValidCredits(credits)) {
            throw new IllegalArgumentException(
                "Credits must be between 1 and 10"
            );
        }
    }
    
    public static void validateDepartment(String department) {
        if (!ValidationHelper.isValidDepartment(department)) {
            throw new IllegalArgumentException(
                "Department cannot be empty"
            );
        }
    }
    
    public static String formatName(String name) {
        if (name == null) return "";
        return name.trim().replaceAll("\\s+", " ");
    }
}
