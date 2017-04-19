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
public class LoggedOutState implements LibraryProtectionProxyState {

    private Library library;

    public LoggedOutState(Library library) {
        this.library = library;
    }

    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder) {
        library.updateClientStatus(clientID, clientID + ",info,not-authorized;");
    }


    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder) {
        library.updateClientStatus(clientID, clientID + ",search,not-authorized;");
    }

    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID, Long visitorID) {
        library.updateClientStatus(clientID, clientID + ",borrow,not-authorized;");
    }

    /**
     * The method that undoes the borrowing of a book for a client/visitor
     * @param clientID - the clientID of the person who tried to borrow the books
     * @param bookID - the book ISBN
     * @param visitorID - the visitorID associated with the borrow
     */
    public void undoBorrowBook(Long clientID, ArrayList<String> bookID, Long visitorID) {
        library.updateClientStatus(clientID, clientID + ",undo borrow,not-authorized");
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids) {
        library.updateClientStatus(clientID, clientID + ",buy,not-authorized;");
    }

    /**
     * The method that undoes a purchasing of books to the LBMS internal library
     * @param clientID - the clientID who purchased the books
     * @param quantity - the quantity of the books to be purchased
     * @param ids - the ids of the books to be purchased
     */
    public void undoPurchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids) {
        library.undoPurchaseBooks(clientID, quantity, ids);
    }

    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber) {
        library.updateClientStatus(clientID, clientID + ",register,not-authorized;");
    }

    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID) {
        // You must log in before you can begin a visit
        library.updateClientStatus(clientID, clientID + ",arrive,not-authorized;");
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID) {
        library.updateClientStatus(clientID, clientID + ",depart,not-authorized;");
    }

    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID) {
        library.updateClientStatus(clientID, clientID + ",borrowed,not-authorized;");
    }

    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount) {
        library.updateClientStatus(clientID, clientID + ",pay,not-authorized;");
    }

    /**
     * This method undos a paying of fines
     * @param clientID - the ClientID that is associated with the fine
     * @param visitorID - the visitorID that is associated with the clientID
     * @param amount - the amount of the fine
     */
    public void undoPayFine(Long clientID, Long visitorID, int amount)
    {
        library.updateClientStatus(clientID, clientID + ",undo pay, not-authorized");
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
        library.updateClientStatus(clientID, clientID + ",shutdown,not-authorized;");
    }

    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        library.updateClientStatus(clientID, clientID + ",connect,already-connected;");
    }

    // disconnect,
    // Client Disconnect
    public void clientDisconnect(Long clientID)
    {
        library.clientDisconnect(clientID);
    }

    // create,
    // Create New Account
    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        String response = clientID + ",create,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // login,
    // Log In
    public void login(Long clientID, String username, String password)
    {
        library.login(clientID, username, password);
    }

    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        String response = clientID + ",logout,not-authorized;";
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
    public void setService(Long clientID, String service)
    {
        String response = clientID + ",service,not-authorized;";
        library.updateClientStatus(clientID, response);
    }


}
