package Banking;

import Account.CashAccount;
import Account.IAccount;
import Account.SavingsAccount;
import Exceptions.TransactionNotSupportedException;

import java.util.UUID;

/**
 * This is a service layer which can be exposed to client/consumer application to manipulate account and perform
 * transactions. The BankService is responsible for
 * 1). Hides away the underlying implementation/complexity of each transaction operation for one/potentially multi-type
 * account
 * 2). Deals with internally exposed methods from Bank
 * 3). Potentially deals with customer authentication and authorization (not implemented in this task)
 * */
public class BankService {

    final Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }

    /**
     * This initializes a IAccount instance and associates it with a customerId and registers this with the specified
     * bank, returns the IAccount as a reference for the use of client/customer application side
     * @param  customerId String
     * @param  bank Bank
     * @return IAccount
     * */
    public IAccount openAccountWithBank(String customerId, Bank bank, IAccount.ACCOUNT_TYPE accountType) {
        IAccount account;
        switch (accountType) {
            case SAVINGS:
                account = new SavingsAccount(0);
            case CASH:
            default:
                account = new CashAccount();
        }
        bank.registerAccount(customerId, account);
        return account;
    }

    /**
     * Withdrawal operation only allows for requested amount that is no greater than the balance of an account
     * returns the updated balance for the display on client/customer application
     * @param customerId String
     * @param amount double
     * @return double
     * */
    public double withdraw(String customerId, UUID accountNumber, double amount) throws TransactionNotSupportedException {
        IAccount account = this.bank.getAccount(customerId, accountNumber);
        if (amount > account.getBalance()) {
            throw new TransactionNotSupportedException("Not enough balance to withdraw for the requested amount.");
        }
        account.updateBalance(amount * -1);
        return account.getBalance();
    }

    /**
     * Deposit amount into account, returns the updated balance for the display on client/customer application
     * @param customerId String
     * @param amount double
     * @return double
     * */
    public double deposit(String customerId, UUID accountNumber, double amount) {
        IAccount account = this.bank.getAccount(customerId, accountNumber);
        account.updateBalance(amount);
        return account.getBalance();
    }

    /**
     * @param customerId String
     * @return double
     * */
    public double checkAccountBalance(String customerId, UUID accountNumber) {
        IAccount account = this.bank.getAccount(customerId, accountNumber);
        return account.getBalance();
    }

    public void transfer(String customerId, IAccount.ACCOUNT_TYPE from, IAccount.ACCOUNT_TYPE to, double amount) {
    }
}
