package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

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

    private LibrarySubject proxy;
    private Long clientID;
    private int quantity;
    private ArrayList<Integer> ids;


    /**
     * Creates a PurchaseBook command to purchase a subset of the books returned from a previous search.
     *
     * @param library - The library that the PurchaseBook command is being executed on.
     * @param quantity - The number of copies of the book that are being purchased.
     * @param ids - The temporary ID of the book returned from the most recent BookStoreSearch.
     */
    public PurchaseBook(Library library, int quantity, ArrayList<Integer> ids)
    {
        this.library = library;
        this.quantity = quantity;
        this.ids = ids;
    }



    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Creates a PurchaseBook command to purchase a subset of the books returned from a previous search.
     *
     * @param proxy - The proxy library that the PurchaseBook command is being executed on.
     * @param clientID - The ID of the client that is making the PurchaseBook request.
     * @param quantity - The number of copies of the book that are being purchased.
     * @param ids - The temporary ID of the book returned from the most recent BookStoreSearch.
     */
    public PurchaseBook(LibrarySubject proxy, Long clientID, int quantity, ArrayList<Integer> ids)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.quantity = quantity;
        this.ids = ids;
    }


    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Executes the PurchaseBook command on the library.
     */
    public void execute()
    {
        proxy.purchaseBooks(clientID, quantity, ids);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {
        proxy.undoPurchaseBooks(clientID,quantity,ids);
    }

}
