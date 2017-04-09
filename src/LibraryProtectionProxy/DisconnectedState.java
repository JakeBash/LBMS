package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * Proxy state for when the client is not connected to the library
 *
 * @author Nikolas Tilley
 */
public class DisconnectedState implements LibraryProtectionProxyState {

    // todo disconnected commands would not be able to pass errors to the library... would
    // have to directly place error into textbox unless that command is like connect or shutdown

    private final String CLIENT_ERROR = "invalid-client-id;";

    public DisconnectedState()
    {

    }

    // Todo implement
    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR Maybe...
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        // Allow Client to connect to library, notify lib of new client to update
        // response - "connect,client ID;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout()
    {
        // Set Client UI to CLIENT_ERROR
    }


    ////////////////////////// maybe not in proxy ? ///////////////////////////

    // Todo supply argument signature
    // undo,
    // Undo
    public void undo()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // redo,
    // Redo
    public void redo()
    {
        // Set Client UI to CLIENT_ERROR
    }

    ///////////////////////////////////////////////////////////////////////////

    // Todo supply argument signature
    // service,
    // Set book information service
    public void setService()
    {
        // Set Client UI to CLIENT_ERROR
    }
}
