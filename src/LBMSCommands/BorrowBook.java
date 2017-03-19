package LBMSCommands;

import java.util.ArrayList;

import Library.Library;

/**
 * Borrows a book for a visitor; uses the ID of a specific book 
 * or books returned in the most recent library book search.
 * 
 * sig - borrow,visitor ID,{id};
 * 
 * @author Nikolas Tilley
 */
public class BorrowBook implements LBMSCommand
{

    private Library library ;
    private Integer visitorID ;  // This may have to be a string that is converted to int in execute...
    private ArrayList<String> bookID ;

    /**
     * Constructs a borrow book command
     * 
     * @param library - Library that the book will be borrowed from
     * @param visitorID - is the unique 10-digit ID of the visitor.
     * @param bookID - is the comma-separated list of IDs for the books
     *                 to be borrowed by the visitor. 5 at most.
     */
    public BorrowBook(Library library, Integer visitorID,
                        ArrayList<String> bookID)
    {
        this.library = library ;
        this.visitorID = visitorID ;
        this.bookID = bookID ;
    }

    public void execute()
    {

    }
}
