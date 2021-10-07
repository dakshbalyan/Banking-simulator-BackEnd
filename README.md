Date Last updated : 28th Sept 2021

# ATM simulator WebApp

## Abstract

In this project I have used the Java spring framework along with the Java JPA to develop the backend for an ATM simulator that can be run on any browser. The following document has some additional information regarding my implementation.

## Database Decisions

![atm_simulator_dbDesign](https://user-images.githubusercontent.com/33063532/136365240-2041d774-c64e-4973-9ecd-b3aeaf6dc5d6.png)

### One to One Relationships

1. LoginDetails ↔ Account
2. Account → Bank, User
3. Transaction → Account

In text, Login details will be unique for each account; an Account will only be associated with one bank and user; A particular transaction can belong to only one account.

### One to Many Relationships

1. User → Account
2. Bank → Account, User
3. Account → Transaction

A user can have multiple accounts, a bank can have multiple users, i.e, accounts; an account will have multiple transactions.

Credit and Debit should be default values should be 0.

## Design Choices

1. We are not allowing a particular user to have multiple mobile numbers to create different accounts.
2. The Bank table and User table will have unique entries only.
3. Account table with accountID as unique will be used to connect bank and user table
4. Login table will only have atm pin and accountID
5. The transaction table will have all the transactions for all the accounts. To get the transaction history for a particular account we will use accountID.
6. We will only have a limited number of banks with us. Thus the records of the bank table will more less stay the same.

## Features to be implemented

1. Basic CRUD functionalities → Create a new account by a user in a particular bank, read transactions or account details, perform updation in account, and delete account.
2. A login feature will also be implemented
3. Pin change functionality
4. Statement of all transactions
5. Deposit, withdraw cash and update total amount
6. Fast cash withdrawal option
7. Display balance on screen with account details

Additional features that can be added in future →

- send email/sms push notifications
- make/save password in encrypted format
- Mini statement according to date
- Transfer to another account of other/same bank
- Check age of user and allow only above 18 to create account

## Problems that can be faced

1. Scalability
2. The amount column of the latest row of transaction table for a particular account ID should be consistent with the amount column in account table → **possible solution** can be to map the account columns of both transaction and account column together so that any changes made can be reflected in both

## Queries

- Create - Creating account → inserUserObject(User user)
  - Insert into User values(user.getAll() )
  - Insert into Account values( some variables )
- Read - Getting account details &amp; balance after login → getAccountDetails(String accountID)
  - Select A.accountID, A.amount, B.name, B.branch from account A inner join on bank B on A.bank\_id = B.id;
- Update - Withdraw → withdrawAmount(int debit)
  - if(debit \&lt;= amnt) then execute the queries
    - Insert into transactions values (accountID, debit, 0,amnt - debit,date)
    - If mapped successfully then won&#39;t need to insert into account&#39;s amount column **or**
    - updateAmnt(string accountID) → update account A set A.amount = amnt - debit where A.accountID = accountID
- Update - Deposit → depositAmount (int credit)
  - Insert into transactions values (accountID, 0,credit, amnt + credit, date)
  - If mapped successfully then won&#39;t need to insert into account&#39;s amount column **or**
  - updateAmnt(string accountID) → update account A set A.amount = amnt + credit where A.accountID = accountID
- Update - PinChange → changePin()
  - Enter old pin → if matches then enter new pin twice for confirmation and check if both are same or not → if yes then execute below query
  - Update login\_details LD set LD.pin = newPin where LD.accountID = accountID;
- Update Fast Cash → create fastCash enums then call withdraw function passing the enum types
- ReadNAuthenticate LoginPage → authenticate(accountID, pin)
  - Select LD.pin, LD.accountID from login\_details LD where LD.accountID = accountID
  - If rs.getInt(&quot;pin&quot;) == pin then login successful otherwise fail
- Read - Getting statement → getAllTransactionByAccountId(String accountID)
  - Select \* from transaction t where t.account\_id = accountID;
- Delete Account → deleteAccount(accountID)
  - Delete from transaction T where T.accountID = accountID
  - Delete from Account A where A.accountID = accountID
  - Delete from login\_details LD where LD.accountID = accountID
