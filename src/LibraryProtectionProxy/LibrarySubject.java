package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * Created by Nik on 4/8/17.
 */
public interface LibrarySubject {


    // Todo supply argument signature
    // info,
    // Book Search
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
    public void generateReport(Long clientID);


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
    // Todo if you connect, and never previously logged out from last connection, are you still logged in?
    // connect,
    // Client Connect
    public void clientConnect(Long clientID);


    // Todo supply argument signature
    // Todo decide if this logs you out, this is important for connecting
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID);


    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount();


    // Todo supply argument signature
    // login,
    // Log In
    public void login();


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

    // Passes responses from the UIS to the library if the commands cannot make it there
    public void forwardResponse(Long clientID, String response);

}
