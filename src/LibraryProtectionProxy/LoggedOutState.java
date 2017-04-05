package LibraryProtectionProxy;

/**
 * Protection Proxy state for when the client is connected but
 * a user is not logged in
 *
 * Connected clients that are not logged in may:
 * Login
 * shutdown?
 * disconnect?
 *
 * @author Nikolas Tilley
 */
public class LoggedOutState implements LibraryProtectionProxyState
{

    public LoggedOutState()
    {

    }

    // Can log in... That is about it as far as I know

    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown()
    {
        // Todo do you need to be logged in to shutdown?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect()
    {
        // response - client ID,disconnect;
        // todo need to make a state change
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login()
    {
        // todo Needs a state change
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout()
    {
        // response: "client ID,<request name>,not-authorized;"
    }


    ////////////////////////// maybe not in proxy ? ///////////////////////////

    // Todo supply argument signature
    // undo,
    // Undo
    public void undo()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // redo,
    // Redo
    public void redo()
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    ///////////////////////////////////////////////////////////////////////////

    // Todo supply argument signature
    // service,
    // Set book information service
    public void setService()
    {
        // response: "client ID,<request name>,not-authorized;"
    }


}
