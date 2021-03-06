/**
 * This is a service layer which can be exposed to client/consumer application to manipulate account and perform
 * transactions. The BankService is responsible for
 * 1). Hides away the underlying implementation/complexity of each transaction operation for one/potentially multi-type
 * account
 * 2). Deals with internally exposed methods from Bank
 * 3). Potentially deals with customer authentication and authorization (not implemented in this task)
 * */
public class BankService implements IBankService {

    Bank bank;

    public BankService(Bank bank) {
        this.bank = bank;
    }

    /**
     * This initializes a CashAccount instance and associates it with a customerId and registers this with the specified
     * bank, returns the cashAccount as a reference for the use of client/customer application side
     * @param  customerId String
     * @param  bank Bank
     * @return CashAccount
     * */
    @Override
    public CashAccount openAccountWithBank(String customerId, Bank bank) {
        CashAccount account = new CashAccount();
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
    @Override
    public double withdraw(String customerId, double amount) throws TransactionNotSupportedException {
        CashAccount account = this.bank.getAccount(customerId);
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
    @Override
    public double deposit(String customerId, double amount) {
        CashAccount account = this.bank.getAccount(customerId);
        account.updateBalance(amount);
        return account.getBalance();
    }

    /**
     * @param customerId String
     * @return double
     * */
    @Override
    public double checkAccountBalance(String customerId) {
        CashAccount account = this.bank.getAccount(customerId);
        return account.getBalance();
    }
}
