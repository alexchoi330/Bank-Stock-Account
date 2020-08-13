# My Personal Project

## _Alex Choi_

### -A **bulleted** list-
- What will the application do?

    This application will gather information about your current bank account
    status such as savings, debt, equity, and loans, and it will
    display it. It will focus more towards equity or the application may only
    contain equity. *Equity manager/management* is the aim for this
    application. It is basically an investment manager that keeps a track
    of your investments in stocks and displays your portfolio.

- Who will use it?

    Anyone who is willing to or has already invested in stocks will benefit
    from using this application as it'll display an organized
    visual representation of your equities, such as what stocks you
    currently own, how many, how much, and much more.

- Why is this project of interest to you?

    At first, I was planning to do something like a debt manager, as I've
    seen some ads about it while watching youtube. But, then I realized
    that I am a young investor who owns a bit of some stocks in the market
    and it would be helpful to make such an application for myself
    and other investors out there. Both debt and quity manager was what
    came to my mind first, when I started thinking about
    what to create. *I am not 100% certain about doing equity though,
    I may want to do both equity and debt or just equity or debt.
    I'll find out before 2nd phase of the project hopefully.*


### -User Stories-
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
Instructions for Grader

-You can generate the first required event by adding stocks to list of stocks and display it

-You can generate the second required event by depositing/withdrawing from account and display it

-You can locate my visual component by adding in a stock into the list of stocks

-You can save the state of my application by pressing menu, then save.

-You can reload the state of my application by pressing menu, then load.

-You can expect a fail pop up if you enter a negative value when the system asks for deposit/withdraw

-You can expect to get an invalid entry pop up if you say no to "viewing your account"




Phase 4: Task 2 : I used the following...
Test and design a class that is robust.  You must have at least one method that throws a checked
exception.  You must have one test for the case where the exception is expected and another where the exception is
not expected.

ValueException class extends Exceptions

deposit() and withdraw() both throws ValueException

doDeposit() and doWithdraw() both catches ValueException

testDepositTwo and testDepositThree are the test cases for expected and unexpected exceptions

## Phase 4: Task 3

-Problem: methods that were displaying the account was in the accountapp class, those methods were related to displaying
the account only, therefore poor cohesion.

-Solution: Made a new class called DisplayAccount and made it extend to AccountApp, and I moved all the methods that
were specifically related to displaying the account to this class.


-Problem: printStockList() and listStocks() are fairly similar, and qualifies for low coupling

-Solution: Made DisplayAccount an abstract class with the abstract showListOfStocks() method



-Problems:

-coupling for AccountApp class doDeposit() and doWithDraw(), and Account deposit() and withdraw()

------------------------------------------------------------------------------
Future add-ons I want to do even after the course ends

-As a user, I want to be able to buy stocks from the list of stocks

-As a user, I want to be able to add my stocks into my list of stocks

-As a user, I want to be able to add stock to my stock-list

-As a user, I want to be able to remove stock from my stock-list

fix:
add stocks, list of stocks shows the newly added stocks but you cant search for it individually.

An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 
the bold and italic fonts correctly but they will appear correctly on GitHub.