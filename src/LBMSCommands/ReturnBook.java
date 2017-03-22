package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Returns a book borrowed by a library visitor.
 * Command format: return,visitor ID,id[,ids];
 *
 * @author Nikolas Tilley
 */
public class ReturnBook implements LBMSCommand
{
    private Library library;
    private String visitorID;
    private ArrayList<String> bookID;

    /**
     * Creates a ReturnBook command, which will return a book for a registered visitor.
     *
     * @param library - The library that the book is being returned to.
     * @param visitorID - The ID of the visitor that is returning the book.
     * @param bookID - The temporary ID of the book that is to be returned.
     */
    public ReturnBook(Library library, String visitorID, ArrayList<String> bookID)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }

    /**
     * Executes the ReturnBook command on the library.
     */
    public void execute()
    {
        //TODO: Implement returning of books
    }
}
