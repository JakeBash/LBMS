package Client;

import Library.Library;
import LibraryProtectionProxy.LibraryProtectionProxy;
import UIS.CommandParser;
import Visitors.Visitor;

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
    private CommandParser parser;
    private LibraryProtectionProxy proxy;

    /**
     * Constructs a Client object.
     *
     * @param clientID -
     * @param library - 
     */
    public Client(Long clientID, Library library)
    {
        this.clientId = clientID;
        this.visitor = null;
        this.parser = new CommandParser(library);
        this.proxy = new LibraryProtectionProxy(library);
        // Command Parser no
        // Proxy no
        // Library yee
    }


    // -------------------- Getters and setters -------------------- //

    public Long getClientId()
    {
        return clientId;
    }

    public void setClientId(Long clientId)
    {
        this.clientId = clientId;
    }

    public void setVisitor(Visitor v)
    {
        this.visitor = v;
    }
}
