package main.ua.Electronics_Store.example;

import main.ua.Electronics_Store.model.*;
import main.ua.Electronics_Store.repository.GenericRepository;
import main.ua.Electronics_Store.util.IdentityExtractor;

public class Main {
    public static void main(String[] args) {
        System.out.println(" ЛР4: Перевірка GenericRepository ");

        // Репозиторій для покупців
        GenericRepository<Customer> customerRepo = new GenericRepository<>(Customer::email);
        Customer anna = new Customer("Anna", "Petrenko", "anna.petrenko@gmail.com");
        Customer ivan = new Customer("Ivan", "Kovalenko", "ivan.kovalenko@gmail.com");

        customerRepo.add(anna);
        customerRepo.add(ivan);

        System.out.println("Усі покупці:");
        customerRepo.getAll().forEach(System.out::println);

        System.out.println("\nПошук за email = anna.petrenko@gmail.com");
        customerRepo.findByIdentity("anna.petrenko@gmail.com")
                .ifPresentOrElse(
                        c -> System.out.println("Знайдено: " + c),
                        () -> System.out.println("Не знайдено.")
                );

        // Репозиторій для категорій
        GenericRepository<Category> categoryRepo = new GenericRepository<>(Category::name);
        Category electronics = new Category("Electronics", "Devices and gadgets");
        categoryRepo.add(electronics);

        System.out.println("\n Категорії:");
        categoryRepo.getAll().forEach(System.out::println);
    }
}
