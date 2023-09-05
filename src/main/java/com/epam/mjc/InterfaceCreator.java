package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InterfaceCreator {

    public Predicate<List<String>> isValuesStartWithUpperCase() {
        return list -> list.stream()
                .allMatch(value -> !value.isEmpty() && Character.isUpperCase(value.charAt(0)));
    }

    public Consumer<List<Integer>> addEvenValuesAtTheEnd() {
        return list -> {
            List<Integer> evenValues = list.stream().filter(value -> value % 2 == 0).collect(Collectors.toList());
            list.addAll(evenValues);
        };
    }

    public Supplier<List<String>> filterCollection(List<String> values) {
        Predicate<String> isValidString = str -> {
            boolean startsWithUppercase = Character.isUpperCase(str.charAt(0));

            boolean endsWithPeriod = str.endsWith(".");

            boolean hasMoreThanThreeWords = str.split("\\s+").length > 3;

            return startsWithUppercase && endsWithPeriod && hasMoreThanThreeWords;
        };

        List<String> filteredList = values.stream()
                .filter(isValidString)
                .collect(Collectors.toList());

        return () -> filteredList;
    }

    public Function<List<String>, Map<String, Integer>> stringSize() {
        return list -> list.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length
                ));
    }

    public BiFunction<List<Integer>, List<Integer>, List<Integer>> concatList() {
        return (list1, list2) -> {
            List<Integer> result = new ArrayList<>(list1);
            result.addAll(list2);
            return result;
        };
    }
}
