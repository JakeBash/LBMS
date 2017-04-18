package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Pays the library an amount toward a visitor's accumulated fines.
 *
 * Command format: clientID,pay,visitor ID,amount;
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class PayFine implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;
    private Long visitorID;
    private int amount;

    /**
     * Initialize the PayFine command with required parameters.
     *
     * @param library - Library object with data.
     * @param visitorID - The visitor who is paying the fine.
     * @param amount - The amount to be paid.
     */
    public PayFine(Library library, Long visitorID, int amount)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.amount = amount;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////
    /**
     * Initialize the PayFine command with required parameters.
     *
     * @param proxy- The proxy library the PayFine command is being executed on.
     * @param clientID - The client that is making the PayFine request.
     * @param visitorID - The visitor who is paying the fine.
     * @param amount - The amount to be paid.
     */
    public PayFine(LibrarySubject proxy, Long clientID, Long visitorID, int amount)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.visitorID = visitorID;
        this.amount = amount;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the PayFine command on the library.
     */
    public void execute()
    {
        proxy.payFine(clientID, visitorID, amount);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {
        proxy.undoPayFine(clientID,visitorID,amount);
    }
}
