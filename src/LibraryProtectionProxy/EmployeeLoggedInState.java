package LibraryProtectionProxy;

import java.util.ArrayList;

/**
 * Protection Proxy state for when the client is connected, the user is logged in,
 * and that user is an Employee of the library
 *
 * A logged in employee has access to all the commands
 *
 *
 * @author Nikolas Tilley
 */
public class EmployeeLoggedInState implements LibraryProtectionProxyState
{

    public EmployeeLoggedInState()
    {

    }


    // Todo supply argument signature
    // info,
    // Library Book Search
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {

    }

    // Todo supply argument signature
    // search,
    // Book Store Search
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {

    }

    // Todo supply argument signature
    // borrow,
    // Borrow book
    public void borrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {

    }

    // Todo supply argument signature
    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {

    }

    // Todo supply argument signature
    // register,
    // Register Visitor
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {

    }

    // Todo supply argument signature
    // arrive,
    // Begin Visit
    public void beginVisit(Long clientID, Long visitorID)
    {

    }

    // Todo supply argument signature
    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {

    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {

    }

    // Todo supply argument signature
    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {

    }

    // Todo supply argument signature
    // report,
    // Library Statistic Report
    public void generateReport(Long clientID)
    {

    }

    // Todo supply argument signature
    // datetime,
    // Current Date Time
    public void getFormattedDateTime(Long clientID)
    {

    }

    // Todo supply argument signature
    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {

    }

    // Todo supply argument signature
    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {

    }

    // Todo supply argument signature
    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {

    }

    // Todo supply argument signature
    // connect,
    // Client Connect
    // Probably should return an error because you are already connected
    public void clientConnect(Long clientID)
    {

    }

    // Todo supply argument signature
    // disconnect,
    // Client Disconnect
    // logs user our and ends connection
    public void clientDisconnect(Long clientID)
    {
        // todo needs a state change
    }

    // Todo supply argument signature
    // create,
    // Create New Account
    public void createAccount()
    {

    }

    // Todo supply argument signature
    // login,
    // Log In
    public void login()
    {

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

    }




}
