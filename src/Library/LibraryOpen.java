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

    public String stateBeginVisit(Integer visitorID, VisitorStorage visitorStorage)
    {
        visitorStorage.startVisit(visitorID);

        // Todo - return the correct repsonse

        return "" ;
    }

    public String stateCheckOutBook()
    {
        // Todo return the correct response

        return "" ;
    }


}
