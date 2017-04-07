package LBMSCommands;

import Library.Library;

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
    // todo private Long clientID;
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

    /**
     * Executes the PayFine command on the library.
     */
    public void execute()
    {
        library.payFine(this.visitorID, this.amount);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }














    private void parse(String s)
    {

        ArrayList<String> args = new ArrayList<String>();

        String arg = "";


        for(char c : s.toCharArray())
        {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            }
            else
                arg += c;
        }

    }
}
