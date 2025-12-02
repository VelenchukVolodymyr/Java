package main.ua.Electronics_Store;

import main.ua.Electronics_Store.model.Category;
import main.ua.Electronics_Store.model.Product;
import main.ua.Electronics_Store.model.Customer;
import main.ua.Electronics_Store.model.Order;
import main.ua.Electronics_Store.model.Supplier;
import main.ua.Electronics_Store.util.SerializationService;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationServiceTest {

    @Test
    void saveAndLoadJsonYaml_shouldReturnSameData() {
        SerializationService ser = new SerializationService();
        Category c1 = Category.of("Electronics", "Devices");
        Category c2 = Category.of("Home", "Home appliances");

        File jf = new File("build/tmp/test-data/categories.json");
        File yf = new File("build/tmp/test-data/categories.yaml");

        ser.saveToJson(List.of(c1, c2), jf);
        ser.saveToYaml(List.of(c1, c2), yf);

        List<Category> fromJson = ser.loadFromJson(jf, Category.class);
        List<Category> fromYaml = ser.loadFromYaml(yf, Category.class);

        assertEquals(2, fromJson.size());
        assertEquals(2, fromYaml.size());
        assertTrue(fromJson.contains(c1));
        assertTrue(fromYaml.contains(c2));
    }

    @Test
    void saveAndLoadDataContainer_shouldPreserveAllData() {
        SerializationService ser = new SerializationService();

        Category electronics = Category.of("Electronics", "Devices and gadgets");
        Category home = Category.of("Home", "Home appliances");

        Product p1 = Product.of("Phone", electronics, 300, 10);
        Product p2 = Product.of("Laptop", electronics, 1200, 5);

        Customer anna = new Customer("Anna", "Petrenko", "anna@example.com");

        Order o1 = Order.of(anna, List.of(p1), java.time.LocalDate.of(2025, 1, 1));

        Supplier s1 = new Supplier("ElectroCo", "support@electroco.example");

        main.ua.Electronics_Store.model.DataContainer container = new main.ua.Electronics_Store.model.DataContainer(
                List.of(electronics, home),
                List.of(p1, p2),
                List.of(anna),
                List.of(o1),
                List.of(s1)
        );

        File jf = new File("build/tmp/test-data/container.json");
        File yf = new File("build/tmp/test-data/container.yaml");

        ser.saveObjectToJson(container, jf);
        ser.saveObjectToYaml(container, yf);

        main.ua.Electronics_Store.model.DataContainer fromJson = ser.loadObjectFromJson(jf, main.ua.Electronics_Store.model.DataContainer.class);
        main.ua.Electronics_Store.model.DataContainer fromYaml = ser.loadObjectFromYaml(yf, main.ua.Electronics_Store.model.DataContainer.class);

        assertEquals(container, fromJson);
        assertEquals(container, fromYaml);
    }
}
