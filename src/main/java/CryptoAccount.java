public class CryptoAccount implements IAccount {

    private double bitcoins;

    public double getBitcoins() {
        return bitcoins;
    }

    public void updateBitcoins(double bitcoins) {
        this.bitcoins = bitcoins;
    }

    public void convertToCash() {

    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.CRYPTO;
    }
}
