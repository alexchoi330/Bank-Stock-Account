

## Bank and Stock Account Application

### -A **bulleted** list-
- What will the application do?

    This application will contain your current bank account informations where you can withdraw and deposit money, and have different account types such as savings, debt, equity, and loans.
    *Equity manager/management* is the aim for this application. this application also allows you to view current stocks on the stock market and buy or sell from the user's portfolio.
  
- Who will use it?

    Customers who need a bank account that can be used to trade stocks and wanting good interest returns on savings account. Also any customer who wishes to just have a bank account to deposit their hard earned money.

- Why is this project of interest to you?

    At first, I planned to develop a debt account, but I realized that so many more features and functions can be added along with the debt account. And I do some investing myself as well so I figured that it'd be a fantastic idea to build 
    an application that could benefit people like me. It also seemed way more fun to implement tons of more functions and features into my application. 


### -User Stories- (list of functions to implement)
-As a user, I want to be able to make a new bank account

-As a user, I want to be able to add money into the bank account

-As a user, I want to be able to withdraw money from the bank account

-As a user, I want to be able to view my bank account

-As a user, I want to be able to add stocks to the list of stocks

-As a user, I want to be able to view my stocks from my list of stocks

-As a user, I want to be able to save my bank account to file with save button

-As a user, I want to be able to load in  my bank account with a load button

-As a user, I want to view my account id, name, and balance all together

------------------------------------------------------------------------------------
### Application Tutorial

-Press load to start the project, if there was no existing account, it will create a new account.

-You can generate the first required event by adding stocks to list of stocks and display it

-You can generate the second required event by depositing/withdrawing from account and display it

-You can locate my visual component by adding in a stock into the list of stocks

-You can save the state of my application by pressing menu, then save.

-You can reload the state of my application by pressing menu, then load.

-You can expect a fail pop up if you enter a negative value when the system asks for deposit/withdraw

-You can expect to get an invalid entry pop up if you say no to "viewing your account"




### Exception cases: 

ValueException class extends Exceptions

deposit() and withdraw() both throws ValueException

doDeposit() and doWithdraw() both catches ValueException

testDepositTwo and testDepositThree are the test cases for expected and unexpected exceptions


### Some problems I ran into and how I solved them

-Problem: methods that were displaying the account was in the accountapp class, those methods were related to displaying
the account only, therefore poor cohesion.

-Solution: Made a new class called DisplayAccount and made it extend to AccountApp, and I moved all the methods that
were specifically related to displaying the account to this class.


-Problem: printStockList() and listStocks() are fairly similar, and qualifies for low coupling

-Solution: Made DisplayAccount an abstract class with the abstract showListOfStocks() method



-Problems:

-coupling for AccountApp class doDeposit() and doWithDraw(), and Account deposit() and withdraw()

------------------------------------------------------------------------------
### list of future ideas

-As a user, I want to be able to buy stocks from the list of stocks

-As a user, I want to be able to add my stocks into my list of stocks

-As a user, I want to be able to add stock to my stock-list

-As a user, I want to be able to remove stock from my stock-list

### need fixing 
add stocks, list of stocks shows the newly added stocks but you cant search for it individually.

An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 
the bold and italic fonts correctly but they will appear correctly on GitHub.
