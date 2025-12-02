package main.ua.Electronics_Store.util;

import main.ua.Electronics_Store.exceptions.InvalidDataException;
import main.ua.Electronics_Store.model.*;
import java.io.*;
import java.util.logging.*;

public class FileDataReader {

    private static final Logger logger = Logger.getLogger(FileDataReader.class.getName());

    public static void readDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    processLine(line);
                } catch (InvalidDataException e) {
                    logger.warning("Invalid data: " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            logger.severe("File not found: " + e.getMessage());
        } catch (IOException e) {
            logger.severe("IO Error while reading file: " + e.getMessage());
        } finally {
            logger.info("File reading finished.");
        }
    }

    private static void processLine(String line) throws InvalidDataException {
        String[] parts = line.split(";");
        if (parts.length == 0)
            throw new InvalidDataException("Empty line found!");

        switch (parts[0]) {
            case "Category":
                if (parts.length != 3)
                    throw new InvalidDataException("Invalid category format: " + line);
                Category category = new Category(parts[1], parts[2]);
                logger.info("Created category: " + category);
                break;

            case "Supplier":
                if (parts.length != 3)
                    throw new InvalidDataException("Invalid supplier format: " + line);
                Supplier supplier = new Supplier(parts[1], parts[2]);
                logger.info("Created supplier: " + supplier);
                break;

            case "Customer":
                if (parts.length != 4)
                    throw new InvalidDataException("Invalid customer format: " + line);
                Customer customer = new Customer(parts[1], parts[2], parts[3]);
                logger.info("Created customer: " + customer);
                break;

            default:
                throw new InvalidDataException("Unknown data type: " + parts[0]);
        }
    }
}
