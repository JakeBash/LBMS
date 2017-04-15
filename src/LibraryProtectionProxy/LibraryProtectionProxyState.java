package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * @author Nikolas Tilley
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
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID);

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids);

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber);

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID);

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID);

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID);

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount);

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days);

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID);

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours);

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns);

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID);

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect(Long clientID);

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID);

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID);

    // Todo supply argument signature
    // login,
    // Log In
    public void login(Long clientID, String username, String password);

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout();


    ////////////////////////// maybe not in proxy ? ///////////////////////////

    // Todo supply argument signature
    // undo,
    // Undo
    public void undo();

    // Todo supply argument signature
    // redo,
    // Redo
    public void redo();

    ///////////////////////////////////////////////////////////////////////////

    // Todo supply argument signature
    // service,
    // Set book information service
    public void setService();

    // Todo supply argument signature
    // Will forward error strings to the library if connected
    // Checks to see if there is a connected user
    // will print to the current tab if not connected
    /*public void forwardResponse();
*/
}
