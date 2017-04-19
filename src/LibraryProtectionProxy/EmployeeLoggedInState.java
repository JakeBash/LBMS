package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

/**
 * Protection Proxy state for when the client is connected, the user is logged in,
 * and that user is an Employee of the library
 *
 * A logged in employee has access to all the commands
 *
 *
 * @author Nikolas Tilley
 */
public class EmployeeLoggedInState implements LibraryProtectionProxyState
{

    private Library library;

    public EmployeeLoggedInState(Library library)
    {
        this.library = library;
    }


    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        library.bookSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        library.bookStoreSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID, Long visitorID)
    {
        library.borrowBook(clientID, bookID, visitorID);
    }

    /**
     * The method that undoes the borrowing of a book for a client/visitor
     * @param clientID - the clientID of the person who tried to borrow the books
     * @param bookID - the book ISBN
     * @param visitorID - the visitorID associated with the borrow
     */
    public void undoBorrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        library.undoBorrowBook(clientID, bookID, visitorID);
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        library.purchaseBooks(clientID, quantity, ids);
    }

    /**
     * The method that undoes a purchasing of books to the LBMS internal library
     * @param clientID - the clientID who purchased the books
     * @param quantity - the quantity of the books to be purchased
     * @param ids - the ids of the books to be purchased
     */
    public void undoPurchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        library.undoPurchaseBooks(clientID,quantity,ids);
    }

    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        library.registerVisitor(clientID, firstName, lastName, address, phoneNumber);
    }

    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        library.beginVisit(clientID, visitorID);
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        library.endVisit(clientID, visitorID);
    }

    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        library.getVisitorCheckedOutBooks(clientID, visitorID);
    }

    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        library.payFine(clientID, visitorID, amount);
    }

    /**
     * This method undos a paying of fines
     * @param clientID - the ClientID that is associated with the fine
     * @param visitorID - the visitorID that is associated with the clientID
     * @param amount - the amount of the fine
     */
    public void undoPayFine(Long clientID, Long visitorID, int amount)
    {
        library.undoPayFine(clientID,visitorID,amount);
    }

    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        library.generateReport(clientID, days);
    }

    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        library.getFormattedDateTime(clientID);
    }

    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        library.advanceTime(clientID, days, hours);
    }

    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        library.returnBooks(clientID, visitorID, isbns);
    }

    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        library.shutdown(clientID);
    }

    // connect,
    // Client Connect
    // Probably should return an error because you are already connected
    public void clientConnect(Long clientID)
    {
        String response = clientID + ",connect,already-connected;";
        library.updateClientStatus(clientID, response);
    }

    // disconnect,
    // Client Disconnect
    // logs user out and ends connection
    public void clientDisconnect(Long clientID)
    {
        library.clientDisconnect(clientID);
    }

    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        library.createAccount(clientID, username, password, role, visitorID);
    }

    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        String response = clientID + ",login,already-logged-in;";
        library.updateClientStatus(clientID, response);
    }

    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        library.logout(clientID);
    }


    // Todo supply argument signature
    // service,
    // Set book information service
    public void setService(Long clientID, String service)
    {
        library.setService(clientID, service);
    }




}
