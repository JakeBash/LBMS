package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Sets the service to use for finding books that can be purchased for the Library.
 * All book query requests should be executed against the specified service.
 *
 * todo this command may set the service for all clients unless we store which service each client is using with clients
 *
 * Command format: client ID,service,info-service;
 *      info-service is one of: local, google
 *
 * @author Nikolas Tilley
 */
public class SetService implements LBMSCommand
{

    private LibrarySubject proxy;
    private Long clientID;
    private String service;


    /**
     *
     * @param proxy - The library proxy that the SetService command is being executed on
     * @param clientID - The ID of the client that is executing the SetService Command
     * @param service - The service that the client wishes to use.
     */
    public SetService(LibrarySubject proxy, Long clientID, String service)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.service = service;
    }


    /**
     * Executes the SetService command on the library.
     */
    @Override
    public void execute() {
        this.proxy.setService(clientID, service);


    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }
}
