package Library;

import Visitors.VisitorStorage;

/**
 * Implements how state dependent commands are executed when the library
 * is Open
 *
 * @author Nikolas Tilley
 */
public class LibraryOpen implements LibraryState
{
    public LibraryOpen()
    {

    }

    public void stateBeginVisit(Integer visitorID, VisitorStorage visitorStorage)
    {
        visitorStorage.startVisit(visitorID);
    }

    public void stateCheckOutBook()
    {

    }


}
