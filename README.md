#Cash Coding Exercise 
This project implements the bank domain with cash account and exposes basic transaction services in minimalist way.
### Built with 
* Java 8
* Gradle/Groovy
* Junit 5

### Testing
There is no client side application/customer program implemented so the only way to interact with the behavior of the code is through runnig the tests.

Use this command to run test suites:
```
gradlew :test --tests "*"
```
### Some assumptions made 
The modeling of the bank and account domain is built on top of following assumptions: 
1. No authentication/authorization is in place to validate customerId when any of the transactions (deposit/withdraw/checkBalance) is invoked;
2. Each customerId is only allowed to be registered with one CashAccount;
3. There is only one monotonous type of account in the system which is cash account containing a 2 decimal numeric value regardless of currency type; 
4. Each cash account maintains a non-negative balance;
5. Transaction operation can be illegal (e.g. withdrawing amount that leads to negative balance in the account) and runtime exceptions are thrown. 

### Design decision
The body of the application consists of 3 entities, whereas the client/customer is not in the scope of this implementation:  
1. Banking 

    This models the bank entity where all cash accounts are registered and stored as a reference using a HashMap, using customerId (String) as key and the cash account object reference as value.

2. BankService 

    The BankService composes an instance of the Banking and implements the IBankService interface which exposes a list of transactions for client/consumer application to manage the associated cash accounts.  

3. CashAccount

    This models the account containing a single numeric value as cash balance (double).

There are 3 possible runtime exceptions that could potentially be raised in this application: 
* CustomerAccountNotRegisteredException
* ExistingRegisteredAccountFoundException
* TransactionNotSupportException
### The pros and cons 
There are certain trade-offs regarding this implementation: 

#### The pros: 
* <b>Separations of concerns</b>: the Banking, BankService, CashAccount each takes care of a specific area of business logic and are not tightly coupled with one another; 
* <b>Encapsulation</b>: client program that mocks customer behavior maintains a reference of the bankService to invoke bank transaction (account registration/deposit/withdraw/balance check), which encapsulates the underlying subsystem/business logic that deals with the account / bank which can potentially be complex;
* <b>Extensibility</b>: introducing more account types or allowing multiple association of accounts for one customer will incur changes only in the class of BankService, which makes method calls via the bank instance to retrieve associated accounts, and performs updates on the account. Also new services can be easily added into BankService such as transferring money between accounts.  
#### The cons: 
* <b>Inefficiency in calculating bank total</b>: each time total balance is retrieved from the bank all cash accounts registered need to extract the balance to calculate an aggregate sum;
* <b>Single point of failure</b>: BankService acts as the centralized point connecting the Banking internal operations to expose customer facing transactions, this class can potentially grow complicated and accumulate error-prone logic;
* <b>Potential re-architecturing curve for change in data structure</b>: using HashMap for storing customerId - CashAccount pairs might not become efficient once requirements change such as multiple accounts keeping / multiple account types, also this incurs issue once concurrency & multithreading is introduced. changing this bit requires modification to all bank internal methods potentially.

### Possible improvements/extensions 
There are several areas where improvements can be made if time permits:
1. <b>Adopt an appropriate and efficient design pattern</b>: such as observer/subscriber/notifier to propagate impacts of each deposit/withdraw transactions, so that this not only changes a specific customer account but also reflects in the bank total without needing to calculate the aggregate sum; 
2. <b>Use proper value formatter & validator</b>: to constrain all amount/values dealt with in the system to be numerically rounded to 2 decimal places and are validated;
3. <b>Remove duplicate logic in BankService</b>: where currently all transactions deposit/withdraw/checkbalance needs to retrieve the corresponding account from the bank, this can be extracted into a standalone piece of logic.
4. <b>Potential extension of keeping transaction history</b>: this can be extended as a feature on top of the existing  deposit/withdrawal operation that will be stored for each account.
