
import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testValidCustomer() {
        Customer customer = new Customer("Anna", "Petrenko", "anna.petrenko@gmail.com");

        assertEquals("Anna", customer.firstName(), "Ім’я повинно бути 'Anna'");
        assertEquals("Petrenko", customer.lastName(), "Прізвище повинно бути 'Petrenko'");
        assertEquals("anna.petrenko@gmail.com", customer.email(), "Email повинен бути 'anna.petrenko@gmail.com'");
    }

    @Test
    void testEmptyFirstNameThrowsException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Customer("", "Petrenko", "anna.petrenko@gmail.com")
        );

        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void testEmptyLastNameThrowsException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Customer("Anna", "", "anna.petrenko@gmail.com")
        );

        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void testInvalidEmailThrowsException() {
        Exception exception = assertThrows(
                RuntimeException.class,  // бо InvalidDataException є перевіреним, але ми його кидаємо всередині record без throws
                () -> new Customer("Anna", "Petrenko", "invalid-email")
        );

        assertTrue(exception.getMessage().contains("Invalid email"),
                "Повідомлення має містити 'Invalid email'");
    }

    @Test
    void testNullEmailThrowsException() {
        Exception exception = assertThrows(
                RuntimeException.class,
                () -> new Customer("Anna", "Petrenko", null)
        );

        assertTrue(exception.getMessage().contains("Invalid email"),
                "Повідомлення має містити 'Invalid email'");
    }
}
