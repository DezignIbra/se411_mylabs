package edu.spu.se411.lab06_logging.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;

class WalletAccountTest {
    @Test void depositAndWithdrawUpdateBalance() throws InsufficientFundsException {
        WalletAccount account = new WalletAccount(1000);
        account.deposit(200);
        account.withdraw(100);
        assertEquals(1100, account.getBalance(), 0.000001);
        account.withdraw(1100);
        assertEquals(0, account.getBalance(), 0.000001);
    }
    @Test void insufficientFundsPreservesBalance() {
        WalletAccount account = new WalletAccount(100);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(101));
        assertEquals(100, account.getBalance());
    }
    @Test void rejectsInvalidAmountsWithoutChangingBalance() {
        WalletAccount account = new WalletAccount(100);
        for (double amount : new double[]{-1, 0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
        }
        assertEquals(100, account.getBalance());
    }
    @Test void rejectsInvalidBalancesAndOverflow() {
        for (double value : new double[]{-1, Double.NaN, Double.POSITIVE_INFINITY})
            assertThrows(IllegalArgumentException.class, () -> new WalletAccount(value));
        WalletAccount account = new WalletAccount(Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(Double.MAX_VALUE));
        assertEquals(Double.MAX_VALUE, account.getBalance());
    }
}
