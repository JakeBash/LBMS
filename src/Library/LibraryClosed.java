package Library;

import Visitors.VisitorStorage;

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
        // TODO Need to update the observed string or something that this didn't happen.
        return "The Library is closed from 19:00 to 8:00 - Cannot Begin Visit";
    }

    public String stateCheckOutBook()
    {
        return "The Library is closed from 19:00 to 8:00 - Cannot Checkout Book";
    }

}
