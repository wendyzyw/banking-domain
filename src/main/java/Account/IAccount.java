package Account;

import java.util.UUID;

public interface IAccount {
    enum ACCOUNT_TYPE {
        CASH, SAVINGS
    }

    ACCOUNT_TYPE getAccountType();

    UUID getAccountNumber();

    void updateBalance(double amount);

    double getBalance();

}
