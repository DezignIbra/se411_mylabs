package edu.spu.se411.lab06_logging.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;

public class WalletAccount {
    private static final Logger logger = LoggerFactory.getLogger(WalletAccount.class);
    private double balance;
    public WalletAccount(double balance) {
        setBalance(balance);
        logger.debug("Wallet account created");
    }
    public void withdraw(double amount) throws InsufficientFundsException {
        validateAmount(amount);
        if (amount > balance) throw new InsufficientFundsException("Insufficient funds for withdrawal");
        balance -= amount;
        logger.debug("Wallet operation completed: {}", "withdraw");
    }
    public void deposit(double amount) {
        validateAmount(amount);
        double updatedBalance = balance + amount;
        if (!Double.isFinite(updatedBalance)) throw new IllegalArgumentException("Balance would overflow");
        balance = updatedBalance;
        logger.debug("Wallet operation completed: {}", "deposit");
    }
    private static void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount must be finite and positive");
    }
    public void setBalance(double balance) {
        if (!Double.isFinite(balance) || balance < 0)
            throw new IllegalArgumentException("Balance must be finite and non-negative");
        this.balance = balance;
    }
    public double getBalance() { return balance; }
}
