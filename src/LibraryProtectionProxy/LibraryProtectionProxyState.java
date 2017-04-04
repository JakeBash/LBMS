package LibraryProtectionProxy;

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

    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch();

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch();

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook();

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks();

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor();

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit();

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit();

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks();

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine();

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport();

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime();

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime();

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks();

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown();

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect();

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect();

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



}
