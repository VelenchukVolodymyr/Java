package main.ua.Electronics_Store.repository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import main.ua.Electronics_Store.util.IdentityExtractor;

public class GenericRepository<T> {

    private final List<T> items = new ArrayList<>();
    private final IdentityExtractor<T> extractor;
    private static final Logger logger = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        logger.info("Attempting to add item: " + item);
        if (item == null) {
            logger.warning("Cannot add null item");
            return;
        }

        if (item instanceof main.ua.Electronics_Store.model.Validatable v) {
            try {
                v.validate();
            } catch (Exception e) {
                logger.severe("Validation failed for item: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        items.add(item);
        logger.info("Added: " + item);
    }

    public boolean remove(T item) {
        if (item == null) {
            logger.warning("Attempted to remove null item");
            return false;
        }

        boolean removed = items.remove(item);

        if (removed) {
            logger.info("Removed: " + extractor.getIdentity(item));
        } else {
            logger.warning("Failed to remove: " + extractor.getIdentity(item));
        }

        return removed;
    }

    public boolean removeByIdentity(String identity) {
        if (identity == null) {
            logger.warning("Attempted to remove item with null identity");
            return false;
        }

        Optional<T> target = findByIdentity(identity);

        if (target.isPresent()) {
            items.remove(target.get());
            logger.info("Removed by identity: " + identity);
            return true;
        }

        logger.warning("No item found with identity: " + identity);
        return false;
    }

    public Optional<T> findByIdentity(String identity) {
        if (identity == null) {
            logger.warning("Null identity passed to findByIdentity()");
            return Optional.empty();
        }

        return items.stream()
                .filter(i -> identity.equals(extractor.getIdentity(i)))
                .findFirst();
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    public boolean containsIdentity(String identity) {
        return findByIdentity(identity).isPresent();
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
        logger.info("Repository cleared");
    }

    // -------- Sorting Methods --------

    public List<T> sortByComparator(Comparator<T> comparator) {
        if (items.isEmpty()) {
            logger.warning("Attempted to sort empty repository");
            return new ArrayList<>();
        }

        if (comparator == null) {
            logger.warning("Comparator is null — returning unsorted list");
            return new ArrayList<>(items);
        }

        logger.info("Sorted by comparator");
        return items.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<T> sortByIdentity(String order) {
        if (items.isEmpty()) {
            logger.warning("Attempted to sort empty repository");
            return new ArrayList<>();
        }

        Comparator<T> cmp = Comparator.comparing(extractor::getIdentity);

        if (order == null) return items.stream().sorted(cmp).toList();

        switch (order.trim().toLowerCase()) {
            case "desc", "descending" -> cmp = cmp.reversed();
        }

        logger.info("Sorted by identity, order=" + order);
        return items.stream().sorted(cmp).toList();
    }
}
