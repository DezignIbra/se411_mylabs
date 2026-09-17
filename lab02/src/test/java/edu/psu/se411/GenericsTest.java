package edu.psu.se411;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class GenericsTest {
    @Test void boxesSupportBothNumberTypes() {
        NumberBox<Integer> ints = new NumberBox<>(2);
        ints.setItem(7);
        assertEquals(7, ints.getItem());
        assertEquals(9.5, ints.add(2.5));
        NumberBox<Double> doubles = new NumberBox<>(1.25);
        doubles.setItem(2.25);
        assertEquals(3.0, doubles.add(0.75));
    }
    @Test void wildcardSumSupportsMixedNumbersAndEmptyLists() {
        assertEquals(6.5, App.sumNumbers(List.of(1, 2.5, 3L)));
        assertEquals(0.0, App.sumNumbers(List.of()));
    }
    @Test void pipelineChangesTypesInOrderAndIsReusable() {
        PipeLine<String, String> base = PipeLine.<String>start().add(String::trim);
        PipeLine<String, Integer> length = base.add(String::length);
        PipeLine<String, String> result = length.add(n -> n * 2).add(Object::toString);
        assertEquals("8", result.execute("  Java  "));
        assertEquals("4", result.execute(" hi "));
        assertEquals("Java", base.execute(" Java "));
        assertEquals(4, length.execute(" Java "));
        assertEquals(5, PipeLine.<Integer>start().execute(5));
    }
}
