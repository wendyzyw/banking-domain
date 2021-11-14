import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankServiceMultipleCustomersTest {

    private static Bank mockBank;
    private static BankService bankService;
    private final static String CUSTOMER_ID_1 = "01";
    private final static String CUSTOMER_ID_2 = "02";

    @BeforeEach
    void setUp() {
        mockBank = new Bank();
        bankService = new BankService(mockBank);
        bankService.openAccountWithBank(CUSTOMER_ID_1, mockBank);
        bankService.openAccountWithBank(CUSTOMER_ID_2, mockBank);
    }

    @Test
    @DisplayName("Customer 01 account balance equals $10 after 01 deposits $10 and 02 deposits $20")
    void customer01AccountBalanceEquals10AfterTwoTransactionByTwoCustomers() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        bankService.deposit(CUSTOMER_ID_2, 20.00);
        assertEquals(10.00, bankService.checkAccountBalance(CUSTOMER_ID_1));
    }

    @Test
    @DisplayName("Customer 02 account balance equals $20 after 01 deposits $10 and 02 deposits $20")
    void customer02AccountBalanceEquals10AfterTwoTransactionByTwoCustomers() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        bankService.deposit(CUSTOMER_ID_2, 20.00);
        assertEquals(20.00, bankService.checkAccountBalance(CUSTOMER_ID_2));
    }

    @Test
    @DisplayName("Bank total equals $30 after 01 deposits $10 and 02 deposits $20")
    void bankTotalEquals10AfterTwoTransactionByTwoCustomers() {
        bankService.deposit(CUSTOMER_ID_1, 10.00);
        bankService.deposit(CUSTOMER_ID_2, 20.00);
        assertEquals(30.00, mockBank.getBankTotal());
    }

}
