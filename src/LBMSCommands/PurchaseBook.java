package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Purchases a subset of the books returned from a previous search of the Book Catalog.
 *
 * Command format: clientID,buy,quantity,id[,ids];

 *
 * @author Nikolas Tilley
 */
public class PurchaseBook implements LBMSCommand
{
    private Library library;
    // todo private long clientID;
    private int quantity;
    private ArrayList<Integer> ids;

    /**
     * Creates a PurchaseBook command to purchase a subset of the books returned from a previous search.
     */
    public PurchaseBook(Library library, int quantity, ArrayList<Integer> ids)
    {
        this.library = library;
        this.quantity = quantity;
        this.ids = ids;
    }

    /**
     * Executes the PurchaseBook command on the library.
     */
    public void execute()
    {
        library.purchaseBooks(this.quantity, this.ids);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }




    private void parse(String s) {
        ArrayList<String> args = new ArrayList<String>();
        // List of book IDS

        String arg = "";


        for (char c : s.toCharArray()) {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            } else
                arg += c;
        }
        // Todo, when you see {} start adding to list of ids
        // Todo cast to proper types and set use try/catches
    }


}
