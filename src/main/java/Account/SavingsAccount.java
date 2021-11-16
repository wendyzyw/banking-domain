package Account;

import java.util.UUID;

/*
* Savings account doesn't allow withdrawal, but allows for transfer and interest
* */
public class SavingsAccount implements IAccount {

    private UUID accountNumber;
    private double balance;
    private double interest;
    private double interestRate;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
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
        return ACCOUNT_TYPE.SAVINGS;
    }

    @Override
    public UUID getAccountNumber() {
        return this.accountNumber;
    }
}
