package LBMSCommands;

import java.util.ArrayList;
import Library.Library;

/**
 * Borrows a book for a visitor; uses the ID of a specific book or books returned in the most recent library book search.
 * sig - borrow,visitor ID,{id};
 * 
 * @author Nikolas Tilley
 */
public class BorrowBook implements LBMSCommand
{
    private Library library;
    private Long visitorID;  // This may have to be a string that is converted to int in execute...
    private ArrayList<String> bookID;

    /**
     * Constructs a borrow book command object.
     * 
     * @param library - The library that the book will be borrowed from.
     * @param visitorID - The unique 10-digit ID of the visitor.
     * @param bookID - The comma-separated list of IDs for the books to be borrowed by the visitor. 5 at most.
     */
    public BorrowBook(Library library, Long visitorID, ArrayList<String> bookID)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }

    // Requires you to have preformed a book search, books must be in memory
    // TODO - Library.borrowBook(Integer vistorID, ArrayList<String> bookID)
    // { Library.getVisitor()
    //
    // TempBookListFromPrevList.getBook(iD)
    // -> Visitor.checkOutBooks }

    /**
     * Executes the BorrowBook command on the library.
     */
    public void execute()
    {
        this.library.borrowBooks(bookID,this.visitorID);
    }
}
