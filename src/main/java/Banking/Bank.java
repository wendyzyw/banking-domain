package Banking;

import Account.CashAccount;
import Account.IAccount;
import Account.SavingsAccount;
import Exceptions.CustomerAccountNotRegisteredException;
import Exceptions.ExistingRegisteredAccountFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The Bank maintains the minimal data structure for all cash accounts with the use of HashMap to associate each
 * customerId with a cash account
 * The Bank methods are exposed only to internal services and are not publicly exposed to customer application
 * */
public class Bank {
    private final Map<String, Map<UUID, IAccount>> accountMap;

    public Bank() {
        this.accountMap = new HashMap<>();
    }

    /**
     * retrieve the associated cash account via a customerId; throws Exception if customerId is invalid
     * @param customerId String
     * @return IAccount
     * */
    protected IAccount getAccount(String customerId, UUID accountNumber) throws CustomerAccountNotRegisteredException {
        if (!this.accountMap.containsKey(customerId)) {
            throw new CustomerAccountNotRegisteredException("Customer with id " + customerId + " does not has an " +
                    "account registered with the bank.");
        }
        return this.accountMap.get(customerId).get(accountNumber);
    }

    /**
     * store the customerId and IAccount as key-value pair when a customer opens up an account with the bank via
     * BankService
     * @param customerId String
     * @param account IAccount
     * */
    protected void registerAccount(String customerId, IAccount account)
            throws ExistingRegisteredAccountFoundException {
        if (!this.accountMap.containsKey(customerId)) {
            Map<UUID, IAccount> accounts = new HashMap<>();
            accounts.put(account.getAccountNumber(), account);
            this.accountMap.put(customerId, accounts);
        } else {
            this.accountMap.get(customerId).put(account.getAccountNumber(), account);
        }
    }

    /**
     * calculate the aggregated sum of amount of all registered cash account
     * @return double
     * */
    public double getBankTotal() {
        return accountMap.values()
                .stream()
                .map(accounts -> accounts.values()
                                .stream()
                                .mapToDouble(IAccount::getBalance)
                                .sum())
                .reduce(0.0, Double::sum);
    }

}
