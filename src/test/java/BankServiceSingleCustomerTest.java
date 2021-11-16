import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Account.IAccount;
import Exceptions.TransactionNotSupportedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankServiceSingleCustomerTest {

    private static BankService bankService;
    private static Bank mockBank;
    private final static String CUSTOMER_ID_1 = "01";

    @BeforeEach
    void setUp() {
        mockBank = new Bank();
        bankService = new BankService(mockBank);
        bankService.openAccountWithBank(CUSTOMER_ID_1, mockBank, IAccount.ACCOUNT_TYPE.CASH);
    }

    @Test
    @DisplayName("Initial cash account balance for customer 01 to be zero")
    void testInitialCashAccountSetUp() {
        assertEquals(0, bankService.checkAccountBalance(CUSTOMER_ID_1));
    }

    @Test
    @DisplayName("Initial bank total balance for customer 01 should be zero")
    void testInitialBankTotal() {
        assertEquals(0, mockBank.getBankTotal());
    }

    @Test
    @DisplayName("Cash account balance becomes $10 after customer 01 deposits $10")
    void testCashAccountBalanceToEqual10AfterDeposit() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        assertEquals(10.00, bankService.checkAccountBalance(CUSTOMER_ID_1));
    }

    @Test
    @DisplayName("Bank total becomes $10 after customer 01 deposits $10")
    void testBankTotalToEqual10AfterDeposit() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        assertEquals(10.00, mockBank.getBankTotal());
    }

    @Test
    @DisplayName("Cash account balance becomes $5 after customer 01 deposits $10 then withdraws $5")
    void testCashAccountBalanceToEqual5AfterWithdraw() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        bankService.withdraw(CUSTOMER_ID_1, 5.00);
        assertEquals(5.00, bankService.checkAccountBalance(CUSTOMER_ID_1));
    }

    @Test
    @DisplayName("Bank total becomes $5 after customer 01 deposits $10 then withdraws $5")
    void testBankTotalToEqual5AfterWithdraw5() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        bankService.withdraw(CUSTOMER_ID_1, 5.00);
        assertEquals(5.00, mockBank.getBankTotal());
    }

    @Test
    @DisplayName("Withdrawal of $20 is halted after customer 01 deposits 10")
    void testCashAccountBalanceToEqual10AfterWithdraw20() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        assertThrows(TransactionNotSupportedException.class, () -> {
            bankService.withdraw(CUSTOMER_ID_1, 20.00);
        });
    }

    @Test
    @DisplayName("Bank total remains $10 after customer 01 deposits $10 then withdraws $20")
    void testBankTotalToRemain10AfterDeposits10ThenWithdraw20() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        assertThrows(TransactionNotSupportedException.class, () -> {
            bankService.withdraw(CUSTOMER_ID_1, 20.00);
        });
        assertEquals(10.00, mockBank.getBankTotal());
    }
}
