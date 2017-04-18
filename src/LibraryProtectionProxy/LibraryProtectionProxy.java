package LibraryProtectionProxy;

import LBMSCommands.Disconnect;
import Library.Library;
import Visitors.Visit;
import Visitors.Visitor;

import java.util.ArrayList;

/**
 * Todo should you be made to type in clientId for every command or is it auto appended by system?
 * Todo responses should also be preceded by clientID
 *
 * todo Library interface?!?!?!?!
 *
 * @author Nikolas Tilley
 */
public class LibraryProtectionProxy implements LibrarySubject
{
    private final int DISCONNECTED_STATE = 0;
    private final int LOGGED_OUT_STATE = 1;
    private final int VISITOR_LOGGED_IN_STATE = 2;
    private final int EMPLOYEE_LOGGED_IN_STATE = 3;
    private Visitor loggedInVisitor;
    private ArrayList<LibraryProtectionProxyState> stateList;
    private LibraryProtectionProxyState activeState;
    private Library library;

    // Clients either have proxies or a proxy knows about a client
    // Or they need to have a visitor
    public LibraryProtectionProxy( Library library )
    {
        this.library = library;

        stateList = new ArrayList<LibraryProtectionProxyState>();
        stateList.add(new DisconnectedState(library));
        stateList.add(new LoggedOutState(library));
        stateList.add(new VisitorLoggedInState(library));
        stateList.add(new EmployeeLoggedInState(library));

        setState(DISCONNECTED_STATE);
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
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        activeState.bookSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        activeState.bookStoreSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        activeState.borrowBook(clientID, bookID, visitorID);
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        activeState.purchaseBooks(clientID, quantity, ids);
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        activeState.registerVisitor(clientID, firstName, lastName, address, phoneNumber);
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        activeState.beginVisit(clientID, visitorID);
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        activeState.endVisit(clientID, visitorID);
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        activeState.getVisitorCheckedOutBooks(clientID, visitorID);
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        activeState.payFine(clientID, visitorID, amount);
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        activeState.generateReport(clientID, days);
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        activeState.getFormattedDateTime(clientID);
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        activeState.advanceTime(clientID, days, hours);
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        activeState.returnBooks(clientID, visitorID, isbns);
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        activeState.shutdown(clientID);
    }

    // Todo supply argument signature
    // Todo if you connect, and never previously logged out from last connection, are you still logged in?
    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        // Should add client to library's list of observers
        activeState.clientConnect(clientID);

        if (activeState instanceof DisconnectedState)
            setState(LOGGED_OUT_STATE);
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
    {
        // should remove client from library's list of observers
        if (activeState instanceof EmployeeLoggedInState || activeState instanceof VisitorLoggedInState)
            activeState.logout(clientID);

        activeState.clientDisconnect(clientID);
        setState(DISCONNECTED_STATE);
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        activeState.createAccount(clientID, username, password, role, visitorID);
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        activeState.login(clientID, username, password);

        if(activeState instanceof LoggedOutState)
        {
            Visitor loggedInUser = library.getVisitorStorage().getUsernames().get(username);
            if (loggedInUser.getRole().equals("Employee"))
            {
                this.setState(EMPLOYEE_LOGGED_IN_STATE);
                loggedInVisitor = loggedInUser;
            }
            else if (loggedInUser.getRole().equals("Visitor"))
            {
                this.setState(VISITOR_LOGGED_IN_STATE);
                loggedInVisitor = loggedInUser;
            }
            else
                ;//Do Nothing
        }
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        activeState.logout(clientID);
        setState(LOGGED_OUT_STATE);
        loggedInVisitor = null;
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





    // NOT GOING TO BE IN STATE
    // todo Should always work... unless disconnected?!?!?!? MB
    public void forwardResponse(Long clientID, String response)
    {
        if (!(activeState instanceof DisconnectedState))
            library.forwardResponse(clientID, response);
    }

    public boolean isConnected()
    {
        if (activeState instanceof DisconnectedState)
            return false;
        else
            return true;
    }

}
