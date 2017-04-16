package Client;

import Books.Book;
import Visitors.Visitor;
import java.util.ArrayList;

/**
 * Description
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class Client
{
    private Long clientId;
    private Visitor visitor;
    private String status;
    private ArrayList<Book>  lastStoreSearch;
    private ArrayList<Book> lastStorageSearch;

    /**
     * The display string of 
     */

    /**
     * Constructs a Client object.
     *
     * @param clientID -
     */
    public Client(Long clientID)
    {
        this.clientId = clientID;
        this.visitor = null;
    }


    // -------------------- Getters and setters -------------------- //

    public Long getClientId()
    {
        return clientId;
    }

    public ArrayList<Book> getLastStoreSearch()
    {
        return lastStoreSearch;
    }

    public ArrayList<Book> getLastStorageSearch()
    {
        return lastStoreSearch;
    }

    public void setLastStoreSearch(ArrayList<Book> books)
    {
        this.lastStoreSearch = books;
    }

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
