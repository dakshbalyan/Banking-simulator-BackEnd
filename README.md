Date Last updated: 11th Oct 2021

# Banking simulator WebApp

## Abstract

In this project I have used the Java spring framework along with the Java JPA to develop the backend for a banking simulator that can be run on any browser. The following document has some additional information regarding my implementation.

## Database Design

![atm_simulator_dbDesign](https://user-images.githubusercontent.com/33063532/136365240-2041d774-c64e-4973-9ecd-b3aeaf6dc5d6.png)

### One to One Relationships

1. Login Details ↔ Account

Login details will be unique for each account; an Account will only be associated with one bank and user; A particular transaction can belong to only one account.

### One to Many Relationships

1. User → Account
2. Bank → Account
3. Account → Transaction

A user can have multiple accounts, a bank can have multiple users, i.e., accounts; an account will have multiple transactions.

### Many to One Relationships

1. Transaction → Account
2. Account → User, Bank

### Many to Many Relationships

1. User → Bank

Credit and Debit should be default values should be 0.

## Design Choices

1. We are not allowing a particular user to have multiple mobile numbers to create different accounts.
2. The Bank table and User table will have unique entries only.
3. Account table with accountID as unique will be used to connect bank and user table
4. Login table will only have atm pin and accountID
5. The transaction table will have all the transactions for all the accounts. To get the transaction history for a particular account we will use accountID.
6. We will only have a limited number of banks with us. Thus, the records of the bank table will more less stay the same.
7. The backend functions will be exposed as REST endpoints so that any front-end technology can be used in tandem with the developed backend.
8. Validations at controller level are being performed for only PUT requests for now.
1. UserName - Not blank; length should be more than 3
2. User Mobile no - Not blank; length should be only 10
3. Gender - Not null.
4. User Aadhar ID - not null; length should be only 12
5. User email - Not null; Valid email is checked
6. Address - Not null; length should be more than 5
7. Bank Name - not null; length should be more than 3
8. Bank branch name - not null; length should be more than 3
9. Validations at service level are performed by throwing exceptions to the REST client as response -
1. Gender - Only 3 values, i.e., male, female and other allowed
2. Fast Cash withdrawal - only certain values, i.e., FIVEHUNDRED(500), ONETHOUSAND(1000), TWOTHOUSAND(2000), FIVETHOUSAND(5000), TENTHOUSAND(10000) are allowed
10. Other validations are limited to database constraints and data types which will throw appropriate errors that will be sent as response to the REST client
11. For every request and response different POJO classes are present.
12. All primary keys are auto generated
13. User can only create account in existing banks

## Features to be implemented

1. Basic CRUD functionalities → Create a new account by a user in a particular bank, read transactions or account details, perform updation in account, and delete account.
2. A login feature will also be implemented
3. Pin change functionality
4. Statement of all transactions
5. Deposit, withdraw cash and update total amount
6. Fast cash withdrawal option
7. Display balance on screen with account details

Future scope of work →

- send email/SMS push notifications
- make/save password in encrypted format
- Mini statement according to date
- Transfer to another account of other/same bank
- Check age of user and allow only above 18 to create account

## Frameworks Used

For the database we went with MySQL, as it is the widely used SQL rdbms in the industry. To develop our backend - Spring Boot and to connect to the Database - Spring Data JPA has been used.

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
    - If rs.getInt("pin") == pin then login successful otherwise fail
- Read - Getting statement → getAllTransactionByAccountId(String accountID)
    - Select \* from transaction t where t.account\_id = accountID;
- Delete Account → deleteAccount(accountID)
    - Delete from transaction T where T.accountID = accountID
    - Delete from Account A where A.accountID = accountID
    - Delete from login\_details LD where LD.accountID = accountID

## Architecture

The backend has been divided into 3 layers -

1. Controller layer - There are mainly 4 controllers segregated based on the REST calls that are allowed to be made to our backend, i.e. , GET, POST, PUT and DELETE controllers.
2. Service layer - For each feature/function mentioned above there is a service class that has been created and has been separated into packages based on their CRUD functionalities they perform.
3. Data layer - Repositories were made for each entity which in turn connects to our MySQL database through the Spring Data JPA.

The Controller layers - maps the URLs/REST-endpoints and calls the appropriate service layer functions. The service then makes calls to the needed repositories (data layer) which fetches and completes the transaction over our database. The appropriate response is then returned to the user through the controller layer with the result given by the service layer.

## REST Calls

#### PUT requests

1. Create User - Creates account for the user and bank information provided
1. URL to call → http://localhost:8080/home/createAccount
2. Request format →

```
{
 "userName": "Any Name",
 "userMobileNo": "Only 10 digit mobile number allowed",
 "gender": "male/female or other allowed",
 "userEmail": "Only Valid email allowed",
 "userDOB": "Date of birth",
 "userAge": "Integer type age",
 "userAddress": "",
 "userAadharId": "should be of length 12",
 "registerBank":{
     "bankName":"",
     "branchName":""
    }
 } 
 ```

1. Register Bank - This function has been implemented to add banks for the users
1. URL to call → http://localhost:8080/home/registerBank
2. Request format →

```
{
"bankName":"",
"branchName":""
} 
```

#### POST Requests

1. Pin Change - Users can change the pin of their account by passing their old pin, account id and the new pin. Pin will be changed only when the old pin matches with the existing pin in the database.
1. URL to call → http://localhost:8080/home/account/changePin
2. Request format →

```
{
"accountID":"",
"newPin":"",
"oldPin":""
}  
 ```

1. Login - Endpoint for users to login
1. URL to call → http://localhost:8080/home/login
2. Request format →

```
{
"userEnteredAccountID":"",
"userEnteredPIN":""
}  
 ```

1. Deposit Amount - Users can deposit any amount to their account ID.
1. URL to call → http://localhost:8080/home/account/deposit
2. Withdrawal amount - Users can withdraw the amount that is present in their account.
1. URL to call → http://localhost:8080/home/account/withdraw
3. Fast Cash Withdrawal - Users can withdraw only permissible fast cash amounts that are present in their account.
1. URL to call → http://localhost:8080/home/account/fastCash

For endpoints 3,4,5 there will be an entry created in the transaction table and balance will be updated in the accounts table. Request format will be same →

```
{
"accountId":"",
"transactionAmount":""
}  
 ```

#### GET Requests

1. Account Details - Fetches account details for account id.
1. URL to call → http://localhost:8080/home/account/
2. Account Balance - Fetches balance details for account id.
1. URL to call → http://localhost:8080/home/account/balance
3. Account statement - Fetches all transactions for the particular account id.
1. URL to call → http://localhost:8080/home/account/statement

#### Delete Requests

1. Delete Account - Deletes account for a particular account id. This will delete entries in login\_details, account and transaction tables.
1. URL to call → http://localhost:8080/home/account/deleteAccount

The request format will be same for all GET and DELETE requests →

```
{
"accountId":""
}  
 ```

##
