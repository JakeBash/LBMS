package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Returns a book borrowed by a library visitor.
 * Command format: return,visitor ID,id[,ids];
 *
 * @author Nikolas Tilley
 * @author Kyler Freas
 */
public class ReturnBook implements LBMSCommand
{
    private Library library;
    private Long visitorID;
    private ArrayList<String> bookID; // ISBN????!?!?

    /**
     * Description
     *
     * @param library
     * @param visitorID
     * @param bookID
     */
    public ReturnBook(Library library, Long visitorID, ArrayList<String> bookID)
    {
        this.library = library;
        this.visitorID = visitorID;
        this.bookID = bookID;
    }

    /**
     * Executes the ReturnBook command on the library
     */
    public void execute()
    {
        this.library.returnBooks(this.visitorID, this.bookID);
    }
}
