package main.ua.Electronics_Store.example;

import main.ua.Electronics_Store.util.FileDataReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна робота №3: Обробка виключень ===");
        String filename = "data.txt";

        FileDataReader.readDataFromFile(filename);

        System.out.println("=== Завершено. Перевірте log-файл для деталей. ===");
    }
}
