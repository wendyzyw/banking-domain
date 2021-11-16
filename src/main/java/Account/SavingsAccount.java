package Account;

/*
* Savings account doesn't allow withdrawal, but allows for transfer and interest
* */
public class SavingsAccount implements IAccount {

    private double balance;
    private double interest;
    private double interestRate;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
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
}
