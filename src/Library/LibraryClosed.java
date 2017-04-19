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
    public LibraryClosed()
    {

    }

    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        return "The Library is closed from 19:00 to 8:00 - Cannot Begin Visit";
    }

    public String stateCheckOutBook(ArrayList<String> bkID, Long vID, VisitorStorage visitorStorage, TimeClock timeClock,
                                    BookStorage bookStorage)
    {
        return "The Library is closed from 19:00 to 8:00 - Cannot Checkout Book";
    }

}
