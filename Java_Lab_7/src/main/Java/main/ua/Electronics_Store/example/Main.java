package main.ua.Electronics_Store.example;

import main.ua.Electronics_Store.model.*;
import main.ua.Electronics_Store.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.File;
import java.util.stream.Collectors;
import main.ua.Electronics_Store.util.SerializationService;
import main.ua.Electronics_Store.model.DataContainer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demo: Stream API & specialized repositories\n");

        // --- Setup repositories ---
        CategoryRepository categoryRepo = new CategoryRepository();
        ProductRepository productRepo = new ProductRepository();
        CustomerRepository customerRepo = new CustomerRepository();
        OrderRepository orderRepo = new OrderRepository();
        SupplierRepository supplierRepo = new SupplierRepository();

        // --- Create some entities ---
        Category electronics = Category.of("Electronics", "Devices and gadgets");
        Category home = Category.of("Home", "Home appliances");
        Category toys = Category.of("Toys", "Toys and games for kids");

        categoryRepo.add(electronics);
        categoryRepo.add(home);
        categoryRepo.add(toys);

        Product p1 = Product.of("Smartphone", electronics, 699.99, 50);
        Product p2 = Product.of("Laptop", electronics, 1299.0, 20);
        Product p3 = Product.of("Vacuum", home, 199.0, 15);
        Product p4 = Product.of("Toy Car", toys, 19.99, 100);

        productRepo.add(p1);
        productRepo.add(p2);
        productRepo.add(p3);
        productRepo.add(p4);

        Customer anna = new Customer("Anna", "Petrenko", "anna.petrenko@gmail.com");
        Customer ivan = new Customer("Ivan", "Kovalenko", "ivan.kovalenko@gmail.com");

        customerRepo.add(anna);
        customerRepo.add(ivan);

        // Orders
        Order o1 = Order.of(anna, List.of(p1, p4), LocalDate.of(2025, 11, 1));
        Order o2 = Order.of(ivan, List.of(p2, p3), LocalDate.of(2025, 11, 5));

        orderRepo.add(o1);
        orderRepo.add(o2);

        // Suppliers
        Supplier s1 = new Supplier("ElectroCo", "support@electroco.example");
        Supplier s2 = new Supplier("HomeGoods", "contact@homegoods.example");
        supplierRepo.add(s1);
        supplierRepo.add(s2);
        // --- Serialization demo ---
        // Load config
        Properties cfg = new Properties();
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) cfg.load(is);
        } catch (Exception e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }

        String jsonPath = cfg.getProperty("json.file", "output/data.json");
        String yamlPath = cfg.getProperty("yaml.file", "output/data.yaml");
        int genCount = Integer.parseInt(cfg.getProperty("generate.count", "10"));

        SerializationService ser = new SerializationService();

        DataContainer container = new DataContainer(
                categoryRepo.getAll(),
                productRepo.getAll(),
                customerRepo.getAll(),
                orderRepo.getAll(),
                supplierRepo.getAll()
        );

        File jsonFile = new File(jsonPath);
        File yamlFile = new File(yamlPath);

        try {
            // Save the entire container (all entities) into JSON/YAML
            ser.saveObjectToJson(container, jsonFile);
            ser.saveObjectToYaml(container, yamlFile);
        } catch (Exception e) {
            System.out.println("Serialization failed: " + e.getMessage());
        }

        // Load back into new repos and compare
        try {
            DataContainer loadedJson = ser.loadObjectFromJson(jsonFile, DataContainer.class);
            DataContainer loadedYaml = ser.loadObjectFromYaml(yamlFile, DataContainer.class);

            System.out.println("\nLoaded JSON container: " + loadedJson);
            System.out.println("Loaded YAML container: " + loadedYaml);

            // Compare whole containers
            System.out.println("JSON round-trip equals original: " + container.equals(loadedJson));
            System.out.println("YAML round-trip equals original: " + container.equals(loadedYaml));

            // Create new repos and populate from loaded JSON as example
            CategoryRepository newCategoryRepo = new CategoryRepository();
            if (loadedJson != null && loadedJson.getCategories() != null) {
                loadedJson.getCategories().forEach(newCategoryRepo::add);
            }
            System.out.println("New repo categories size: " + newCategoryRepo.size());
        } catch (Exception e) {
            System.out.println("Deserialization failed: " + e.getMessage());
        }
        // --- Demonstrate Stream searches ---
        System.out.println("Products in price range 100..800:");
        productRepo.findByPriceRange(100, 800).forEach(System.out::println);

        System.out.println("\nProducts containing 'toy' in name:");
        productRepo.findByNameContains("toy").forEach(System.out::println);

        System.out.println("\nCategories containing 'home' in description:");
        categoryRepo.findByDescriptionContains("home").forEach(System.out::println);

        System.out.println("\nAll product names from orders (flatMap + distinct):");
        orderRepo.allProductNamesFromOrders().forEach(System.out::println);

        // Terminal operations: collect, reduce, forEach
        System.out.println("\nAll product names collected:");
        List<String> names = productRepo.getAll().stream().map(Product::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

        double totalValue = productRepo.getAll().stream().map(Product::getPrice).reduce(0.0, Double::sum);
        System.out.println("\nTotal products value (reduce): " + totalValue);

        // Performance: stream vs parallelStream (simple demonstration)
        List<Product> many = productRepo.getAll();
        int runs = 200_000; // increase amount of operations by repeating mapping

        long t1 = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            many.stream().map(Product::getName).collect(Collectors.toList());
        }
        long dur1 = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            many.parallelStream().map(Product::getName).collect(Collectors.toList());
        }
        long dur2 = System.nanoTime() - t2;

        System.out.println("\nstream total ns: " + dur1 + ", parallelStream total ns: " + dur2);

        System.out.println("\nDone demo.");
    }
}
