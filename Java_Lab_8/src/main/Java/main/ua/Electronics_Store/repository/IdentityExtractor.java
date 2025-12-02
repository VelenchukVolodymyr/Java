package main.ua.Electronics_Store.util;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String getIdentity(T item);
}
