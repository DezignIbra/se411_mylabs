package edu.psu.se411;
import java.util.Objects;

/** T is the original input type; R is the current output type. */
public final class PipeLine<T, R> {
    private final Transformer<T, R> steps;
    private PipeLine(Transformer<T, R> steps) { this.steps = steps; }
    public static <T> PipeLine<T, T> start() {
        return new PipeLine<>(input -> input);
    }
    // Composition stores the ordered steps while preserving their type relationships.
    public <V> PipeLine<T, V> add(Transformer<? super R, ? extends V> next) {
        Objects.requireNonNull(next);
        return new PipeLine<>(input -> next.transform(steps.transform(input)));
    }
    public R execute(T input) { return steps.transform(input); }
}
