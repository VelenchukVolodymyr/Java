package main.ua.Electronics_Store.repository;

import java.util.*;
import java.util.logging.Logger;
import main.ua.Electronics_Store.util.IdentityExtractor;

public class GenericRepository<T> {
    private final List<T> items = new ArrayList<>();
    private final IdentityExtractor<T> extractor;
    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        items.add(item);
        logger.info("Added: " + item);
    }

    public void remove(T item) {
        items.remove(item);
        logger.info("Removed: " + item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public Optional<T> findByIdentity(String identity) {
        return items.stream()
                .filter(i -> extractor.getIdentity(i).equals(identity))
                .findFirst();
    }
}
