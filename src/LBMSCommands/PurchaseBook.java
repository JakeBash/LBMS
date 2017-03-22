package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Description
 *
 * @author Nikolas Tilley
 */
public class PurchaseBook implements LBMSCommand
{
    //TODO: BookCatalogs have yet to be implemented
    private Library library;
    private int quantity;
    private ArrayList<Integer> ids;

    /**
     *
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
}
