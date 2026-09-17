package edu.psu.se411;
import java.math.BigDecimal;
import edu.psu.se411.exceptions.InvalidAgeException;
import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.model.Wallet;

public class App {
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Age must be at least 18");
        System.out.println("Age valid message.");
    }
    public static void main(String[] args) {
        for (int age : new int[]{17, 18}) {
            try { validateAge(age); }
            catch (InvalidAgeException e) { System.out.println("Invalid age: " + e.getMessage()); }
        }
        Wallet wallet = new Wallet(new BigDecimal("1000.00"));
        try {
            wallet.withdraw(new BigDecimal("250.00"));
            System.out.println("Wallet balance: " + wallet.getBalance());
            System.out.println("Bank balance: " + wallet.getBankBalance());
            wallet.withdraw(new BigDecimal("1500.00"));
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal rejected: " + e.getMessage());
        }
    }
}
