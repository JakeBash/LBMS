package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public interface LibrarySubject
{
    // info,
    // Book Search
    void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);


    // search,
    // Book Store Search
    void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);


    // borrow,
    // Borrow book
    void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID);

    void undoBorrowBook(Long clientID, ArrayList<String> bookID,Long visitorID);


    // buy,
    // Book Purchase
    void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids);

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
    void setService(Long clientId, String service);

    // Passes responses from the UIS to the library if the commands cannot make it there
    void forwardResponse(Long clientID, String response);



}
