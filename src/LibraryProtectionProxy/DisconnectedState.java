package LibraryProtectionProxy;

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

    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks()
    {
        // Set Client UI to CLIENT_ERROR
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown()
    {
        // Set Client UI to CLIENT_ERROR Maybe...
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect()
    {
        // Allow Client to connect to library, notify lib of new client to update
        // response - "connect,client ID;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect()
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
