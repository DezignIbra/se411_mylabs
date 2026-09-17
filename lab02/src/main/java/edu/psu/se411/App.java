package edu.psu.se411;
import java.util.List;

public class App {
    public static void printList(List<?> items) {
        for (Object item : items) System.out.println(item);
    }
    public static double sumNumbers(List<? extends Number> numbers) {
        return NumberBox.sumNumbers(numbers);
    }
    public static void main(String[] args) {
        System.out.println("Exercise 1: PrintableList");
        new PrintableList<>(new String[]{"Java", "Generics", "SE411"}).printItems();
        System.out.println("Exercise 2: NumberBox");
        NumberBox<Integer> integers = new NumberBox<>(10);
        integers.setItem(20);
        NumberBox<Double> decimals = new NumberBox<>(2.5);
        System.out.println("Integer item: " + integers.getItem());
        System.out.println("Integer addition: " + integers.add(5));
        System.out.println("Double item: " + decimals.getItem());
        System.out.println("Double addition: " + decimals.add(1.5));
        System.out.println("Number sum: " + NumberBox.sumNumbers(List.of(1, 2.5, 3)));
        System.out.println("Exercise 3: Pipeline");
        PipeLine<String, String> trimmed = PipeLine.<String>start().add(String::trim);
        PipeLine<String, Integer> lengths = trimmed.add(String::length);
        PipeLine<String, String> description = lengths.add(n -> n * 2).add(n -> "Double length: " + n);
        System.out.println(description.execute("  Java  "));
        System.out.println("Exercise 4: Wildcards");
        printList(List.of("A", "B"));
        printList(List.of(1, 2));
        System.out.println("Wildcard sum: " + sumNumbers(List.of(1, 2.5, 3)));
    }
}
