package edu.psu.se411;
@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T input);
}
