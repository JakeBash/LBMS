package LBMSCommands;

import Library.Library;

/**
 * Sets the service to use for finding books that can be purchased for the Library.
 * All book query requests should be executed against the specified service.
 *
 * todo this command may set the service for all clients unless we store which service each client is using with clients
 *
 * client ID,service,info-service;
 *      info-service is one of: local, google
 *
 * @author Nikolas Tilley
 */
public class SetService implements LBMSCommand
{

    private Library library;
    private long clientID;
    private String service;


    /**
     *
     * @param library - The library that the SetService command is being executed on
     * @param clientID - The ID of the client that is executing the SetService Command
     * @param service - The service that the client wishes to use.
     */
    public SetService(Library library, long clientID, String service)
    {
        this.library = library;
        this.clientID = clientID;
        this.service = service;
    }


    /**
     * Executes the SetService command on the library.
     */
    @Override
    public void execute() {

    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }
}
