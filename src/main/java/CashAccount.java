/**
 * The modeling of CashAccount is implemented in a minimal form as a single unit of encapsulation wrapping a balance
 * and exposing methods for retrieving and modifying the balance
 * */
public class CashAccount {
    private double balance;

    public CashAccount() {
        this.balance = 0; // initial balance default to 0
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
}
