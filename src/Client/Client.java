package Client;

import Library.Library;
import LibraryProtectionProxy.LibraryProtectionProxy;
import UIS.CommandParser;
import Books.Book;
import Visitors.Visitor;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Nikolas Tilley
 * @author Jake Bashaww
 */
public class Client
{
    private Long clientId;
    private Visitor visitor;
    private String status;
    /**
     * The last search from the book catalog.
     */
    private ArrayList<Book>  lastStoreSearch;
    private ArrayList<Book> lastStorageSearch;
    // Command Parser no
    // Proxy no
    // Library yee
    // private CommandParser parser; // todo this should get moved to GUICommandDisplay
    // private LibraryProtectionProxy proxy; // todo This should get moved to GUICommandDisplay

    /**
     * Constructs a Client object.
     *
     * @param clientID -
     */
    public Client(Long clientID)
    {
        this.clientId = clientID;
        this.visitor = null;
        //this.parser = new CommandParser(library);
        //this.proxy = new LibraryProtectionProxy(library);
        // Command Parser no
        // Proxy no
        // Library yee
    }


    // -------------------- Getters and setters -------------------- //

    public Long getClientId()
    {
        return clientId;
    }
    /**
     * Retrieves the results from the mst recent search of the Book Catalog.
     *
     * @return An ArrayList representing the results from the last search of the Book Catalog.
     */
    public ArrayList<Book> getLastStoreSearch() {return lastStoreSearch;}
    public ArrayList<Book> getLastStorageSearch() {return lastStoreSearch;}
    public void setLastStoreSearch(ArrayList<Book> books) {this.lastStoreSearch = books;}

    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public void setVisitor(Visitor v)
    {
        this.visitor = v;
    }


    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
}
