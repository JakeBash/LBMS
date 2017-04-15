package LBMSCommands;
import LibraryProtectionProxy.LibrarySubject;


/**
 * A new client establishes a connection with the Library.
 *
 * Client ID is a unique ID generated for this client. The client ID
 * will be transmitted with all subsequent requests submitted by this client. The client ID
 * represents on LBMS client program and should not be confused with a visitor or employee ID
 *
 * connect;
 *
 * @author Nikolas Tilley
 */
public class Connect implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;

    /**
     *
     * @param proxy - The proxy that the client is connecting with.
     * @param clientID - The unique ID of the client connecting
     */
    public Connect(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
    }

    /**
     * Executes the Connect command on the library.
     */
    @Override
    public void execute() {
        this.proxy.clientConnect(clientID);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }
}
