package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

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

    private Library library;

    public VisitorLoggedInState(Library library)
    {
        this.library = library;
    }

    // Visitors can Begin Visit
    // Book info querry from book storage
    // Borrow books
    // End Visit


    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        library.bookSearch(clientID, title, authors, isbn, publisher, sortOrder);
    }

    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        String response = clientID + ",search,not-authorized;";
        library.updateClientStatus(clientID, response);

    }

    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        library.borrowBook(clientID, bookID, visitorID);
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        String response = clientID + ",buy,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        String response = clientID + ",register,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {
        library.beginVisit(clientID, visitorID);
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {
        library.endVisit(clientID, visitorID);
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {

        // Todo.... can you view your own checked out books?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // todo Can only employees pay fines?
        // response: "client ID,<request name>,not-authorized;"
    }


    // report,
    // Library Statistic Report
    public void generateReport(Long clientID, int days)
    {
        String response = clientID + ",report,not-authorized;";
        library.updateClientStatus(clientID, response);
    }


    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {
        // todo can only employees get the time?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        String response = clientID + ",advance,not-authorized;";
        library.updateClientStatus(clientID, response);    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        // todo Can employees only return books?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        // todo can visitors shutdown?
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        // Todo should this be an "already connected" error
        // response: "client ID,<request name>,not-authorized;"
    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    // Logs user out and ends connection
    public void clientDisconnect(Long clientID)
    {
        library.clientDisconnect(clientID);
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
        String response = clientID + ",login,already-logged-in;";
        library.updateClientStatus(clientID, response);
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
