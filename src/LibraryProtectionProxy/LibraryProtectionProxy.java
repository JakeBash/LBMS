package LibraryProtectionProxy;

import Client.Client;
import LBMSCommands.Disconnect;
import Library.Library;
import Visitors.Visit;
import Visitors.Visitor;

import java.util.ArrayList;

/**
 *
 *
 * @author Nikolas Tilley
 */
public class LibraryProtectionProxy implements LibrarySubject
{
    private final int DISCONNECTED_STATE = 0;
    private final int LOGGED_OUT_STATE = 1;
    private final int VISITOR_LOGGED_IN_STATE = 2;
    private final int EMPLOYEE_LOGGED_IN_STATE = 3;
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


    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        activeState.bookSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        activeState.bookStoreSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        activeState.borrowBook(clientID, bookID, visitorID);
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        activeState.purchaseBooks(clientID, quantity, ids);
    }

    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        activeState.registerVisitor(clientID, firstName, lastName, address, phoneNumber);
    }

    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        activeState.beginVisit(clientID, visitorID);
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        activeState.endVisit(clientID, visitorID);
    }

    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        activeState.getVisitorCheckedOutBooks(clientID, visitorID);
    }

    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        activeState.payFine(clientID, visitorID, amount);
    }

    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        activeState.generateReport(clientID, days);
    }

    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        activeState.getFormattedDateTime(clientID);
    }

    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        activeState.advanceTime(clientID, days, hours);
    }

    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        activeState.returnBooks(clientID, visitorID, isbns);
    }

    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        activeState.shutdown(clientID);
    }

    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        // Should add client to library's list of observers
        activeState.clientConnect(clientID);

        if (activeState instanceof DisconnectedState)
            setState(LOGGED_OUT_STATE);
    }

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

    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        activeState.createAccount(clientID, username, password, role, visitorID);
    }

    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        activeState.login(clientID, username, password);

        if(activeState instanceof LoggedOutState) {
            Visitor loggedInUser = library.getVisitorStorage().getUsernames().get(username);

            if (loggedInUser != null)
            {
                library.getClient(clientID).setVisitor(loggedInUser);

                if (loggedInUser.getRole().equalsIgnoreCase("employee")) {
                    this.setState(EMPLOYEE_LOGGED_IN_STATE);
                } else if (loggedInUser.getRole().equalsIgnoreCase("visitor")) {
                    this.setState(VISITOR_LOGGED_IN_STATE);
                } else
                    ;//Do Nothing
            }
        }
    }


    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        activeState.logout(clientID);
        setState(LOGGED_OUT_STATE);
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
    public void setService(Long clientID, String service)
    {
        activeState.setService(clientID, service );
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

    public Long getClientVisitorID(Long clientID)
    {
        if (activeState instanceof EmployeeLoggedInState || activeState instanceof VisitorLoggedInState)
            return library.getClient(clientID).getVisitor().getID();
        else
            return null;
    }

}
