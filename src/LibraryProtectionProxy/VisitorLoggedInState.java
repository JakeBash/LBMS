package LibraryProtectionProxy;

import Library.Library;
import Visitors.Visitor;

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
        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.borrowBook(clientID, bookID, visitorID);
            else
            {
                String response = clientID + ",borrow,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",borrow,invalid-id;");
        }
    }

    /**
     * The method that undoes the borrowing of a book for a client/visitor
     * @param clientID - the clientID of the person who tried to borrow the books
     * @param bookID - the book ISBN
     * @param visitorID - the visitorID associated with the borrow
     */
    public void undoBorrowBook(Long clientID, ArrayList<String> bookID,Long visitorID)
    {
        library.borrowBook(clientID,bookID,visitorID);
    }

    // buy,
    // Book Purchase
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        String response = clientID + ",buy,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    /**
     * The method that undoes a purchasing of books to the LBMS internal library
     * @param clientID - the clientID who purchased the books
     * @param quantity - the quantity of the books to be purchased
     * @param ids - the ids of the books to be purchased
     */
    public void undoPurchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        library.updateClientStatus(clientID,",undo buy, not-authorized;");
        library.undoPurchaseBooks(clientID,quantity,ids);
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
        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.beginVisit(clientID, visitorID);
            else
            {
                String response = clientID + ",arrive,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",arrive,invalid-id;");
        }
    }

    // depart,
    // End Visit
    public void endVisit(Long clientID, Long visitorID)
    {

        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.endVisit(clientID, visitorID);
            else
            {
                String response = clientID + ",depart,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",depart,invalid-id;");
        }
    }

    // Todo supply argument signature
    // borrowed,
    // Find Borrowed Books
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.getVisitorCheckedOutBooks(clientID, visitorID);
            else
            {
                String response = clientID + ",borrowed,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",return,invalid-id;");
        }
    }

    // pay,
    // Pay fine
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.payFine(clientID, visitorID, amount);
            else
            {
                String response = clientID + ",pay,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",pay,invalid-id;");
        }
    }

    /**
     * This method undos a paying of fines
     * @param clientID - the ClientID that is associated with the fine
     * @param visitorID - the visitorID that is associated with the clientID
     * @param amount - the amount of the fine
     */
    public void undoPayFine(Long clientID, Long visitorID, int amount)
    {

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
        String response = clientID + ",datetime,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // advance,
    // Advance Time
    public void advanceTime(Long clientID, int days, int hours)
    {
        String response = clientID + ",advance,not-authorized;";
        library.updateClientStatus(clientID, response);    }

    // return,
    // Return book
    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        Visitor visitor = library.getClient(clientID).getVisitor();
        if (visitor != null)
        {
            if (visitor.getID().compareTo(visitorID) == 0)
                library.returnBooks(clientID, visitorID, isbns);
            else
            {
                String response = clientID + ",return,not-authorized-to-make-commands-on-other's-behalf;";
                library.updateClientStatus(clientID, response);
            }
        }
        else
        {
            library.updateClientStatus(clientID, clientID + ",return,invalid-id;");
        }
    }

    // shutdown,
    // Shut Down
    public void shutdown(Long clientID)
    {
        String response = clientID + ",shutdown,not-authorized;";
        library.updateClientStatus(clientID, response);
    }

    // connect,
    // Client Connect
    public void clientConnect(Long clientID)
    {
        String response = clientID + ",connect,already-connected;";
        library.updateClientStatus(clientID, response);
    }

    // disconnect,
    // Client Disconnect
    // Logs user out and ends connection
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
        String response = clientID + ",login,already-logged-in;";
        library.updateClientStatus(clientID, response);
    }

    // logout,
    // Log Out
    public void logout(Long clientID)
    {
        library.logout(clientID);
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
    public void setService(Long clientID, String service)
    {
        String response = clientID + ",service,not-authorized;";
        library.updateClientStatus(clientID, response);
    }


}
