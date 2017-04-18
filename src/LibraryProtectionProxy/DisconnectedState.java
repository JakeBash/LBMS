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
        // Disconnected state wont work with the way we update client status's
    }


    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // Disconnected state wont work with the way we update client status's

    }


    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }


    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // Disconnected state wont work with the way we update client status's
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
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        // Disconnected state wont work with the way we update client status's
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
        // Disconnected state wont work with the way we update client status's
        // Set Client UI to CLIENT_ERROR
    }
}
