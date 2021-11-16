package Account;

public interface IAccount {
    enum ACCOUNT_TYPE {
        CASH, SAVINGS
    }

    ACCOUNT_TYPE getAccountType();

    public void updateBalance(double amount);

    public double getBalance();

}
