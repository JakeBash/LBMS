package LibraryProtectionProxy;

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

    public LoggedOutState()
    {

    }

    // Can log in... That is about it as far as I know

    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport(Long clientID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // Todo do you need to be logged in to shutdown?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
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
