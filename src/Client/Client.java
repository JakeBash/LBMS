package Client;

import Library.Library;
import LibraryProtectionProxy.LibraryProtectionProxy;
import UIS.CommandParser;
import Visitors.Visit;
import Visitors.Visitor;

/**
 * @author Nikolas Tilley
 */
public class Client
{
    private long clientId;
    private Visitor visitor;
    private CommandParser parser;
    private LibraryProtectionProxy proxy;

    public Client(long clientID, Library library)
    {
        this.clientId = clientID;
        this.visitor = null;
        this.parser = new CommandParser(library);
        this.proxy = new LibraryProtectionProxy(library);
    }


    // -------------------- Getters and setters -------------------- //

    public long getClientId()
    {
        return clientId;
    }

    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }

    public void setVisitor(Visitor v)
    {
        this.visitor = v;
    }
}
