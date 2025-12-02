package main.ua.Electronics_Store.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import main.ua.Electronics_Store.exceptions.DataSerializationException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SerializationService {

    private static final Logger logger = Logger.getLogger(SerializationService.class.getName());

    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;

    public SerializationService() {
        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.findAndRegisterModules();

        yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new JavaTimeModule());
        yamlMapper.findAndRegisterModules();
    }

    public <T> void saveToJson(List<T> items, File file) {
        try {
            file.getParentFile().mkdirs();
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
            logger.info("Saved JSON to: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.severe("Failed saving JSON to: " + file.getAbsolutePath() + " — " + e.getMessage());
            throw new DataSerializationException("Failed to save JSON", e);
        }
    }

    public <T> List<T> loadFromJson(File file, Class<T> elementType) {
        try {
            JavaType type = jsonMapper.getTypeFactory().constructCollectionType(List.class, elementType);
            List<T> list = jsonMapper.readValue(file, type);
            logger.info("Loaded JSON from: " + file.getAbsolutePath());
            return list;
        } catch (IOException e) {
            logger.severe("Failed loading JSON from: " + file.getAbsolutePath() + " — " + e.getMessage());
            throw new DataSerializationException("Failed to load JSON", e);
        }
    }

    public <T> void saveToYaml(List<T> items, File file) {
        try {
            file.getParentFile().mkdirs();
            yamlMapper.writerWithDefaultPrettyPrinter().writeValue(file, items);
            logger.info("Saved YAML to: " + file.getAbsolutePath());
        } catch (IOException e) {
            logger.severe("Failed saving YAML to: " + file.getAbsolutePath() + " — " + e.getMessage());
            throw new DataSerializationException("Failed to save YAML", e);
        }
    }

    public <T> List<T> loadFromYaml(File file, Class<T> elementType) {
        try {
            JavaType type = yamlMapper.getTypeFactory().constructCollectionType(List.class, elementType);
            List<T> list = yamlMapper.readValue(file, type);
            logger.info("Loaded YAML from: " + file.getAbsolutePath());
            return list;
        } catch (IOException e) {
            logger.severe("Failed loading YAML from: " + file.getAbsolutePath() + " — " + e.getMessage());
            throw new DataSerializationException("Failed to load YAML", e);
        }
    }
}
