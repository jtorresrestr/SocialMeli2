package org.bootcamp.javazoo.helper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionSorter {
    public static <T> List<T> sortCollection(List<T> collection, Comparator<T> comparator) {
        return collection.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
