package com.shahian;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String[] names = {"hamidreza", "Shahian", "Java", "Stream Api Java 8"};
        methodwithForeach(names);
        methodwithFor(names);
        streamCreate(names);
        streamForach(names);
        streamMin(names);
        streamAllMatch(names);
        streanCollect(names);
        streamReduse();
        streamFilter(names);
        streamMap(names);
        streamFlatMap(names);
        flattenListOfListsStream();
        streamPeek(names);
        streamSkipAndLimit(names);
        streamDistinct(names);
        streamSorted(names);
        streamParallel(names);

    }


    private static void streamParallel(String[] names) {
        int total = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("total with parallel : " + total);
        int total1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .sequential()
                .reduce(0, (x, y) -> x + y);
        System.out.println("total with sequential : " + total1);

        long startParallel = System.currentTimeMillis();
        Arrays.stream(names).parallel().filter(x -> x.startsWith("h")).forEach(x -> System.out.println(x));
        long endParallel = System.currentTimeMillis();

        System.out.println(" compute Parallel times :" + (endParallel - startParallel));

        long startSequential = System.currentTimeMillis();
        Arrays.stream(names).sequential().filter(x -> x.startsWith("h")).forEach(x -> System.out.println(x));
        long endSequential = System.currentTimeMillis();

        System.out.println(" compute sequential  times :" + (endSequential - startSequential));

    }

    private static void streamSorted(String[] names) {
        String[] namesD2 = {"hamidrezaD2", "ShahianD2", "JavaD2", "Stream Api Java 8D2"};

        String[][] strings = {names, namesD2};
        String[] strings2 = Stream.of(strings)
                .flatMap(strings1 -> Stream.of(strings1))
                .peek(s -> System.out.println("peek : " + s))
                .toArray(value -> new String[value]);
        Arrays.stream(strings2).sorted().forEach(s -> System.out.println("sorted :" + s));
    }

    private static void streamDistinct(String[] names) {
        String[] namesD2 = {"hamidrezaD2", "ShahianD2", "JavaD2", "Stream Api Java 8D2"};

        String[][] strings = {names, namesD2};
        String[] strings2 = Stream.of(strings)
                .flatMap(strings1 -> Stream.of(strings1))
                .peek(s -> System.out.println("peek : " + s))
                .toArray(value -> new String[value]);
        long count = Arrays.stream(strings2).distinct().count();
        System.out.println("distinct : " + count);
    }

    private static void streamSkipAndLimit(String[] names) {
        String[] namesD2 = {"hamidrezaD2", "ShahianD2", "JavaD2", "Stream Api Java 8D2"};

        String[][] strings = {names, namesD2};
        String[] strings2 = Stream.of(strings)
                .flatMap(strings1 -> Stream.of(strings1))
                .toArray(value -> new String[value]);
        Arrays.stream(strings2).skip(1).forEach(s -> System.out.println("skip : " + s));
        Arrays.stream(strings2).limit(1).forEach(s -> System.out.println("limit : " + s));
        Arrays.stream(strings2).skip(2).limit(3).forEach(s -> System.out.println("skip And limit : " + s));
    }

    private static void streamPeek(String[] names) {
        String[] namesD2 = {"hamidrezaD2", "ShahianD2", "JavaD2", "Stream Api Java 8D2"};

        String[][] strings = {names, namesD2};
        String[] strings2 = Stream.of(strings)
                .flatMap(strings1 -> Stream.of(strings1))
                .peek(s -> System.out.println("peek : " + s))
                .toArray(value -> new String[value]);

    }

    private static void streamFlatMap(String[] names) {
        String[] namesD2 = {"hamidrezaD2", "ShahianD2", "JavaD2", "Stream Api Java 8D2"};

        String[][] strings = {names, namesD2};
        String[] strings2 = Stream.of(strings).flatMap(strings1 -> Stream.of(strings1)).toArray(value -> new String[value]);
        for (String s : strings2) {
            System.out.println("2 dimensional to 1dimensional : " + s);
        }
    }

    public static <T> List<T> flattenListOfListsStream(List<List<T>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static void flattenListOfListsStream() {
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("one:two"),
                Arrays.asList("two:one", "two:two", "two: three "),
                Arrays.asList("three:one", "three:two", "three:three")
        );
        List<String> ls = flattenListOfListsStream(nestedList);
        System.out.println("flattenListOfListsStream : " + ls);
    }

    private static void streamMap(String[] names) {
        List<String> stringList = new ArrayList<>(Arrays.asList(names));
        stringList.stream().map(n -> n + " maped").forEach(s -> System.out.println(s));
    }

    private static void streamFilter(String[] names) {
        List<String> stringList = new ArrayList<>(Arrays.asList(names));
        stringList.stream().filter(n -> n.startsWith("h")).forEach(s -> System.out.println("filter in strean : " + s));
    }

    private static void streamReduse() {
        //In Java 8, the Stream.reduce() combine elements of a stream and produces a single value.
        int[] ints = {1, 2, 3, 4, 5};
        int reduce = Arrays.stream(ints).reduce(0, (left, right) -> left + right);
        System.out.println("reduse :" + reduce);
        //or
        int reduce1 = Arrays.stream(ints).reduce(0, Integer::sum);
        System.out.println("reduse1 :" + reduce1);
        int sum3 = Arrays.stream(ints).reduce(0, (a, b) -> a - b);
        System.out.println("sum3 :" + sum3);

        int sum4 = Arrays.stream(ints).reduce(1, (a, b) -> a * b);
        System.out.println("sum4 :" + sum4);

        int sum5 = Arrays.stream(ints).reduce(1, (a, b) -> a / b);
        System.out.println("sum5 :" + sum5);

    }

    private static void streanCollect(String[] names) {
        List<String> stringList = new ArrayList<>(Arrays.asList(names));
        List<String> collect = stringList.stream().filter(name -> name.length() > 5).collect(Collectors.toList());
        System.out.println("collected list are " + collect);
    }

    private static void streamAllMatch(String[] names) {
        List<String> stringList = new ArrayList<>(Arrays.asList(names));
        boolean allMatch = Arrays.stream(names).allMatch(name -> !name.isEmpty());
        System.out.println("all non list is :" + allMatch);
        boolean b = stringList.stream().allMatch(name -> name.startsWith("h"));
        System.out.println("all list start with h :" + b);
    }

    private static void streamMin(String[] names) {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 5, 6, 38, 8, -9, 123, 2));
        Optional<Integer> result = integers.stream().min(Integer::compare);
        System.out.println("min result is " + result);
//      Optional<Integer> generatedMethod = Stream.generate(() -> 5).min((o1, o2) -> o1.compareTo(o2));
//      System.out.println("integer min result is :" + generatedMethod);
    }

    private static void streamForach(String[] names) {
        Arrays.stream(names).forEach(name -> System.out.println(name));
    }

    private static void streamCreate(String[] names) {
        //create stream based on Array
        Stream<String> stringStream = Arrays.stream(names);


        //create stream based on Collection
        List<String> stringList = Arrays.asList(names);
        Stream<String> stringSystem = stringList.stream();


        //create stream using generate method
        Stream<Integer> generatedMethod = Stream.generate(() -> 5);

        //create stream using iterate method
        Stream<Integer> iterateMethod = Stream.iterate(1, x -> x + 2);


    }

    private static void methodwithFor(String[] names) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].startsWith("h")) {
                names[i] += " loves java ";
            }
            System.out.println(names[i]);

        }
    }

    private static void methodwithForeach(String[] names) {
        List<String> stringList = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("St")) {
                stringList.add(name);

            }
        }
        System.out.println(stringList);
    }
}
