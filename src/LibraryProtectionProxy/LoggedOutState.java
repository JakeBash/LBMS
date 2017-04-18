package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

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

    private Library library;

    public LoggedOutState(Library library)
    {
        this.library = library;
    }

    // Can log in... That is about it as far as I know


    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        library.updateClientStatus(clientID, clientID + ",info,not-authorized;");
    }


    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        library.updateClientStatus(clientID, clientID + ",search,not-authorized;");
    }

    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        library.updateClientStatus(clientID, clientID + ",borrow,not-authorized;");
    }

    public void undoBorrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        library.updateClientStatus(clientID,clientID + ",undo borrow,not-authorized");
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        library.updateClientStatus(clientID, clientID + ",buy,not-authorized;");
    }

    public void undoPurchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        library.undoPurchaseBooks(clientID,quantity,ids);
    }

    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        library.updateClientStatus(clientID, clientID + ",register,not-authorized;");
    }

    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        // You must log in before you can begin a visit
        library.updateClientStatus(clientID, clientID + ",arrive,not-authorized;");
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        library.updateClientStatus(clientID, clientID + ",depart,not-authorized;");
    }

    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        library.updateClientStatus(clientID, clientID + ",borrowed,not-authorized;");
    }

    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        library.updateClientStatus(clientID, clientID + ",pay,not-authorized;");
    }

    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        library.updateClientStatus(clientID, clientID + ",report,not-authorized;");
    }

    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        library.updateClientStatus(clientID, clientID + ",datetime,not-authorized;");
    }

    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        library.updateClientStatus(clientID, clientID + ",advance,not-authorized;");
    }

    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        library.updateClientStatus(clientID, clientID + ",return,not-authorized;");
    }

    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // Todo do you need to be logged in to shutdown?
        library.updateClientStatus(clientID, clientID + ",shutdown,not-authorized;");
    }

    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        library.updateClientStatus(clientID, clientID + ",connect,already-connected;");
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
    {
        library.clientDisconnect(clientID);
        // todo need to make a state change
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        String response = clientID + ",create,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        library.login(clientID, username, password);
    }

    // Todo supply argument signature
    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        String response = clientID + ",logout,success;";
        library.updateClientStatus(clientID, response);
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
