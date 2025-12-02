package main.ua.Electronics_Store.validation;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTests {

    @Test
    void createValidCustomer_shouldSucceed() {
        Customer c = new Customer("Anna", "Petrenko", "anna@example.com");
        assertEquals("Anna", c.firstName());
    }

    @Test
    void createInvalidCustomer_shouldThrowWithAllErrors() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            new Customer("", "", "bad-email");
        });
        Throwable cause = ex.getCause();
        assertNotNull(cause);
        assertTrue(cause instanceof InvalidDataException);
        String msg = cause.getMessage();
        assertTrue(msg.contains("firstName: cannot be empty") || msg.contains("email: invalid format"));
    }

    @Test
    void productSetters_validationShouldThrow() {
        Category c = new Category("Electronics", "Devices");
        Product p = new Product("Phone", c, 100, 5);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> p.setPrice(-5));
        assertTrue(ex.getCause() instanceof InvalidDataException);

        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> p.setName("  "));
        assertTrue(ex2.getCause() instanceof InvalidDataException);
    }

    @Test
    void personSetters_shouldThrowInvalidDataExceptionWrapped() {
        Person p = new Person();
        RuntimeException ex = assertThrows(RuntimeException.class, () -> p.setFirstName(""));
        assertTrue(ex.getCause() instanceof InvalidDataException);
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> p.setEmail("bad"));
        assertTrue(ex2.getCause() instanceof InvalidDataException);
    }
}
