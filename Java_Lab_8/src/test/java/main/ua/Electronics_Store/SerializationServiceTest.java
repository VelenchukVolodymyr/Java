package main.ua.Electronics_Store;

import main.ua.Electronics_Store.model.Category;
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
}
