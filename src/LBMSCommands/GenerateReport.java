package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Generates a report of library statistics.
 * Command format: clientID,report[,days];
 *
 * @author Nikolas Tilley
 * @author kyler F.
 */
public class GenerateReport implements LBMSCommand
{

    private LibrarySubject proxy;
    private Long clientID;
    private int days;


    /**
     * Creates a command to generate statistical reports for the library.
     *
     * @param proxy - The proxy library the client is interacting with.
     * @param clientID - The ID of the client that is making the GenerateReport request.
     */
    public GenerateReport(LibrarySubject proxy, Long clientID)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.days = 0;
    }



    /**
     * Creates a command to generate statistical reports for the library.
     *
     * @param proxy - The proxy library the client is interacting with.
     * @param clientID - The ID of the client that is making the GenerateReport request.
     * @param days - The number of days in the past the report should cover
     */
    public GenerateReport(LibrarySubject proxy, Long clientID, int days)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.days = days;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////




    /**
     * Executes the GenerateReport command on the library.
     */
    public void execute()
    {
        this.proxy.generateReport(clientID, days);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
