package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

/**
 * Proxy state for when the client is not connected to the library
 *
 * @author Nikolas Tilley
 */
public class DisconnectedState implements LibraryProtectionProxyState
{
    // have to directly place error into textbox unless that command is like connect or shutdown

    private final String CLIENT_ERROR = "invalid-client-id;";
    private Library library;

    public DisconnectedState(Library library)
    {
        this.library = library;
    }


    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // todo This will not work, can't update the UI if not connected, have to write to gui directly
        // todo - maybe connecting and disconnecting should not be commands... but instead be called directly
        // todo - from the client to the library and back to  the gui?
        library.updateClientStatus(clientID, clientID + ",info,not-authorized;");
    }


    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {

    }


    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        // Set Client UI to CLIENT_ERROR
    }


    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR Maybe...
    }


    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        library.clientConnect(clientID);
        // response - "connect,client ID;"
    }


    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login(Long clientID, String username, String password)
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
