package LBMSCommands;

import Library.Library;

/**
 * Pays the library an amount toward a visitor's accumulated fines
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class PayFine implements LBMSCommand
{
    private Library library;
    private Long visitorID;
    private int amount;

    /**
     * Initialize the command with required parameters
     *
     * @param library - library object with data
     * @param visitorID - visitor who is paying the fine
     * @param amount - amount to pay
     */
    public PayFine(Library library, Long visitorID, int amount)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.amount = amount;
    }

    /**
     * Executes the PayFine command on the library.
     */
    public void execute()
    {
        library.payFine(this.visitorID, this.amount);
    }
}
