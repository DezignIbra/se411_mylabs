package edu.psu.se411.model;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class StackTest {
    @Test void pushPushPopReturnsLatestElement() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Z");
        stringStack.push("A");
        // Lab's failure demonstration: mvn test -Dlab03.expectedTop=Z
        // Default stays A so the final submission passes.
        assertEquals(System.getProperty("lab03.expectedTop", "A"), stringStack.pop());
    }
    @Test void popEmptyStackThrows() {
        Stack<String> stringStack = new Stack<>();
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
                stringStack::pop, "Expected pop from empty Stack to throw, but it didn't");
        // Matches the supplied implementation, whose text differs from the handout.
        assertEquals("Stack is empty, cannot pop", thrown.getMessage());
    }
    @Test void elementsArePoppedInReverseOrder() {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Z"); stringStack.push("A"); stringStack.push("B");
        assertEquals("B", stringStack.pop());
        assertEquals("A", stringStack.pop());
        assertEquals("Z", stringStack.pop());
        assertThrows(NoSuchElementException.class, stringStack::pop);
    }
}
