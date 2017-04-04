package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

/**
 *
 *
 * @author Nikolas Tilley
 */
public class LibraryProtectionProxy
{

    private final int LOGGED_OUT = 0;
    private final int VISITOR_LOGGED_IN = 1;
    private final int EMPLOYEE_LOGGED_IN = 2;

    // private visitor/user loggedInVisitor
    private ArrayList<LibraryProtectionProxyState> stateList;
    private LibraryProtectionProxyState activeState;
    private Library library;

    // Clients either have proxies or a proxy knows about a client
    // Or they need to have a visitor
    public LibraryProtectionProxy( Library library )
    {

        this.library = library;

        stateList = new ArrayList<LibraryProtectionProxyState>();
        stateList.add(new LoggedOutState());
        stateList.add(new VisitorLoggedInState());
        stateList.add(new EmployeeLoggedInState());

        setState(LOGGED_OUT);
    }



    /**
     * Sets the state of the protection proxy
     *
     * @param index the index of the state to change to
     */
    private void setState(int index)
    {
        activeState = stateList.get(index);
    }











    // TODO implement library interface
    ////////////////////////////////////////////////////////////////

    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch()
    {
        activeState.bookSearch();
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch()
    {
        activeState.bookStoreSearch();
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook()
    {
        activeState.borrowBook();
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks()
    {
        activeState.purchaseBooks();
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor()
    {
        activeState.registerVisitor();
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit()
    {
        activeState.beginVisit();
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit()
    {
        activeState.endVisit();
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks()
    {
        activeState.getVisitorCheckedOutBooks();
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine()
    {
        activeState.payFine();
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport()
    {
        activeState.generateReport();
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime()
    {
        activeState.getFormattedDateTime();
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime()
    {
        activeState.advanceTime();
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks()
    {
        activeState.returnBooks();
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown()
    {
        activeState.shutdown();
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect()
    {
        // Should add client to library's list of observers
        activeState.clientConnect();
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect()
    {
        // should remove client from library's list of observers
        activeState.clientDisconnect();
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount()
    {
        activeState.createAccount();
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login()
    {
        activeState.login();
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout()
    {
        activeState.logout();
    }


    ////////////////////////// maybe not in proxy ? ///////////////////////////

    // Todo supply argument signature
    // undo,
    // Undo
    public void undo()
    {
        activeState.undo();
    }

    // Todo supply argument signature
    // redo,
    // Redo
    public void redo()
    {
        activeState.redo();
    }

    ///////////////////////////////////////////////////////////////////////////

    // Todo supply argument signature
    // service,
    // Set book information service
    public void setService()
    {
        activeState.setService();
    }


}
