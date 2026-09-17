package edu.psu.se411.model;
import java.math.BigDecimal;
import java.util.Objects;
import edu.psu.se411.exceptions.InsufficientFundsException;

/** Local simulation: a successful withdrawal transfers money to a bank balance. */
public class Wallet {
    private BigDecimal balance;
    private BigDecimal bankBalance = BigDecimal.ZERO;
    public Wallet(BigDecimal balance) {
        Objects.requireNonNull(balance, "Balance is required");
        if (balance.signum() < 0) throw new IllegalArgumentException("Balance cannot be negative");
        this.balance = balance;
    }
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        Objects.requireNonNull(amount, "Amount is required");
        if (amount.signum() <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount.compareTo(balance) > 0)
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        // Validate everything before changing either balance.
        balance = balance.subtract(amount);
        bankBalance = bankBalance.add(amount);
    }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getBankBalance() { return bankBalance; }
}
