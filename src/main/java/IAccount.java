
public interface IAccount {
    enum ACCOUNT_TYPE {
        CASH, CRYPTO
    }

    ACCOUNT_TYPE getAccountType();
}
