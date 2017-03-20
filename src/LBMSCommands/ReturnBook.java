package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Returns a book borrowed by a library visitor.
 *
 * Command format: return,visitor ID,id[,ids];
 *
 * @author Nikolas Tilley
 *
 */
public class ReturnBook implements LBMSCommand
{
    private Library library ;
    private String visitorID ;
    private ArrayList<String> bookID ; // ISBN????!?!?


    public ReturnBook(Library library, String visitorID,
                      ArrayList<String> bookID)
    {
        this.library = library ;
        this.visitorID = visitorID ;
        this.bookID = bookID ;
    }

    public void execute()
    {
        // todo implement returning of books
    }
}
