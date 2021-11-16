package Account;

import Account.IAccount;

import java.util.UUID;

/**
 * The modeling of CashAccount is implemented in a minimal form as a single unit of encapsulation wrapping a balance
 * and exposing methods for retrieving and modifying the balance
 * */
public class CashAccount implements IAccount {

    private UUID accountNumber;
    private double balance;

    public CashAccount() {
        this.balance = 0; // initial balance default to 0
        this.accountNumber = UUID.randomUUID();
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.CASH;
    }

    @Override
    public UUID getAccountNumber() {
        return this.accountNumber;
    }
}
