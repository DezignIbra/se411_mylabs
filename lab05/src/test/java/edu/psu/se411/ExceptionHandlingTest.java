package edu.psu.se411;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import edu.psu.se411.exceptions.*;
import edu.psu.se411.model.Wallet;

class ExceptionHandlingTest {
    @Test void ageBoundary() {
        assertThrows(InvalidAgeException.class, () -> App.validateAge(17));
        assertDoesNotThrow(() -> App.validateAge(18));
        assertDoesNotThrow(() -> App.validateAge(55));
    }
    @Test void withdrawalTransfersExactAmount() throws InsufficientFundsException {
        Wallet w = new Wallet(new BigDecimal("0.30"));
        w.withdraw(new BigDecimal("0.10"));
        assertEquals(new BigDecimal("0.20"), w.getBalance());
        assertEquals(new BigDecimal("0.10"), w.getBankBalance());
        w.withdraw(new BigDecimal("0.20"));
        assertEquals(new BigDecimal("0.00"), w.getBalance());
    }
    @Test void failedWithdrawalDoesNotChangeEitherBalance() {
        Wallet w = new Wallet(new BigDecimal("100.00"));
        assertThrows(InsufficientFundsException.class, () -> w.withdraw(new BigDecimal("100.01")));
        assertEquals(new BigDecimal("100.00"), w.getBalance());
        assertEquals(BigDecimal.ZERO, w.getBankBalance());
    }
    @Test void invalidAmountsAreRejected() {
        Wallet w = new Wallet(BigDecimal.TEN);
        assertThrows(IllegalArgumentException.class, () -> w.withdraw(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> w.withdraw(BigDecimal.ONE.negate()));
        assertThrows(IllegalArgumentException.class, () -> new Wallet(BigDecimal.ONE.negate()));
        assertEquals(BigDecimal.TEN, w.getBalance());
    }
}
