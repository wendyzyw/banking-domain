import Account.IAccount;
import Exceptions.CustomerAccountNotRegisteredException;
import Exceptions.ExistingRegisteredAccountFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * The Bank maintains the minimal data structure for all cash accounts with the use of HashMap to associate each
 * customerId with a cash account
 * The Bank methods are exposed only to internal services and are not publicly exposed to customer application
 * */
public class Bank {
    private final Map<String, IAccount> cashAccountMap;

    public Bank() {
        this.cashAccountMap = new HashMap<>();
    }

    /**
     * retrieve the associated cash account via a customerId; throws Exception if customerId is invalid
     * @param customerId String
     * @return IAccount
     * */
    protected IAccount getAccount(String customerId) throws CustomerAccountNotRegisteredException {
        if (!this.cashAccountMap.containsKey(customerId)) {
            throw new CustomerAccountNotRegisteredException("Customer with id " + customerId + " does not has an " +
                    "account registered with the bank.");
        }
        return this.cashAccountMap.get(customerId);
    }

    /**
     * store the customerId and IAccount as key-value pair when a customer opens up an account with the bank via
     * BankService
     * @param customerId String
     * @param IAccount IAccount
     * */
    protected void registerAccount(String customerId, IAccount IAccount)
            throws ExistingRegisteredAccountFoundException {
        if (this.cashAccountMap.containsKey(customerId)) {
            throw new ExistingRegisteredAccountFoundException("Customer with id " + customerId + " already has an " +
                    "registered account in the system.");
        }
        this.cashAccountMap.put(customerId, IAccount);
    }

    /**
     * calculate the aggregated sum of amount of all registered cash account
     * @return double
     * */
    protected double getBankTotal() {
        return cashAccountMap.values().stream().mapToDouble(IAccount::getBalance).sum();
    }

}
