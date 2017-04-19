package Library;

import Books.BookStorage;
import Visitors.VisitorStorage;

import java.util.ArrayList;

/**
 * Implements how state dependent commands are executed when the library is closed.
 *
 * @author Nikolas Tilley
 */
public class LibraryClosed implements LibraryState
{
    /**
     * The constructor for the library closed state. This does nothing
     */
    public LibraryClosed()
    {

    }

    /**
     * The method to being a visit when the library is closed. This just yells at you
     * @param visitorID - the VID of who is logging in
     * @param visitorStorage - the visitorStorage
     * @return
     */
    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        return "The Library is closed from 19:00 to 8:00 - Cannot Begin Visit";
    }

    /**
     * The method that checks out a book when the library is closed
     * @param bkID
     * @param vID
     * @param visitorStorage
     * @param timeClock
     * @param bookStorage
     * @return
     */
    public String stateCheckOutBook(ArrayList<String> bkID, Long vID, VisitorStorage visitorStorage, TimeClock timeClock,
                                    BookStorage bookStorage)
    {
        return "The Library is closed from 19:00 to 8:00 - Cannot Checkout Book";
    }

}
