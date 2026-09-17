package edu.psu.se411;
import java.util.List;

public class NumberBox<T extends Number> {
    private T item;
    public NumberBox(T item) { setItem(item); }
    public void setItem(T item) { this.item = java.util.Objects.requireNonNull(item); }
    public T getItem() { return item; }
    // Number has no generic + operator; return a double without casting to T.
    public double add(Number other) { return item.doubleValue() + other.doubleValue(); }
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number number : numbers) sum += number.doubleValue();
        return sum;
    }
}
