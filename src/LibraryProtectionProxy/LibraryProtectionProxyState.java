package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public interface LibraryProtectionProxyState
{

    // Employees can
    // TODO All of R1 Methods
    // Create new account method
    // Can choose between book sources to purchase from


    // Visitors can Begin Visit
    // Book info querry from book storage
    // Borrow books
    // End Visit


    // info,
    // Library Book Search
    void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);

    // search,
    // Book Store Search
    void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);

    // borrow,
    // Borrow book
    void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID);


    /**
     * The method that undoes the borrowing of a book for a client/visitor
     * @param clientID - the clientID of the person who tried to borrow the books
     * @param bookID - the book ISBN
     * @param visitorID - the visitorID associated with the borrow
     */
    void undoBorrowBook(Long clientID, ArrayList<String> bookID,Long visitorID);

    // buy,
    // Book Purchase
    void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids);

    /**
     * The method that undoes a purchasing of books to the LBMS internal library
     * @param clientID - the clientID who purchased the books
     * @param quantity - the quantity of the books to be purchased
     * @param ids - the ids of the books to be purchased
     */
    void undoPurchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids);

    // register,
    // Register Visitor
    void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber);

    // arrive,
    // Begin Visit
    void beginVisit(Long clientID, Long visitorID);

    // depart,
    // End Visit
    void endVisit(Long clientID, Long visitorID);

    // borrowed,
    // Find Borrowed Books
    void getVisitorCheckedOutBooks(Long clientID, Long visitorID);

    // pay,
    // Pay fine
    void payFine(Long clientID, Long visitorID, int amount);

    /**
     * This method undos a paying of fines
     * @param clientID - the ClientID that is associated with the fine
     * @param visitorID - the visitorID that is associated with the clientID
     * @param amount - the amount of the fine
     */
    void undoPayFine(Long clientID, Long visitorID, int amount);

    // report,
    // Library Statistic Report
    void generateReport(Long clientID, int days);

    // datetime,
    // Current Date Time
    void getFormattedDateTime(Long clientID);

    // advance,
    // Advance Time
    void advanceTime(Long clientID, int days, int hours);

    // return,
    // Return book
    void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns);

    // shutdown,
    // Shut Down
    void shutdown(Long clientID);

    // connect,
    // Client Connect
    void clientConnect(Long clientID);

    // disconnect,
    // Client Disconnect
    void clientDisconnect(Long clientID);

    // create,
    // Create New Account
    void createAccount(Long clientID, String username, String password, String role, Long visitorID);

    // login,
    // Log In
    void login(Long clientID, String username, String password);

    // logout,
    // Log Out
    void logout(Long clientID);

    // service,
    // Set book information service
    void setService(Long clientID, String service);

    // Todo supply argument signature
    // Will forward error strings to the library if connected
    // Checks to see if there is a connected user
    // will print to the current tab if not connected
    /*void forwardResponse();
*/
}
