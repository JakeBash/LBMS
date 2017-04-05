package LibraryProtectionProxy;

/**
 * Protection Proxy state for when client is connected, and the user is logged in
 * and that user is a Visitor
 *
 * Logged in visitors may:
 * Begin visits
 * Book info query from book storage
 * Borrow Books
 * Return Borrowed Books
 * End visits
 * Logout
 *
 * disconnect?
 * shutdown -- this should probably just be employees
 *
 * @author Nikolas Tilley
 */
public class VisitorLoggedInState implements LibraryProtectionProxyState
{
    public VisitorLoggedInState()
    {

    }

    // Visitors can Begin Visit
    // Book info querry from book storage
    // Borrow books
    // End Visit


    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch()
    {

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

    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit()
    {

    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks()
    {

        // Todo.... can you view your own checked out books?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine()
    {
        // todo Can only employees pay fines?
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
        // todo can only employees get the time?
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
        // todo Can employees only return books?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown()
    {
        // todo can visitors shutdown?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect()
    {
        // Todo should this be an "already connected" error
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    // Logs user out and ends connection
    public void clientDisconnect()
    {
        // Todo Needs a state change
        // todo can visitors disconnect clients?
        // response: "client ID,<request name>,not-authorized;"
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
        // Todo: should this be "already logged in"
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout()
    {
        // todo needs a state change
    }


    ////////////////////////// maybe not in proxy ? ///////////////////////////

    // Todo supply argument signature
    // undo,
    // Undo
    public void undo()
    {

    }

    // Todo supply argument signature
    // redo,
    // Redo
    public void redo()
    {

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
