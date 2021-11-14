/**
 * This specifies a list of implemented methods that support allowed transactions upon account
 * */
public interface IBankService {
    CashAccount openAccountWithBank(String customerId, Bank bank);
    double withdraw(String customerId, double amount);
    double deposit(String customerId, double amount);
    double checkAccountBalance(String customerId);
}
