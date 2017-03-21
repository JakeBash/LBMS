package Library;

import Visitors.VisitorStorage;

/**
 * Implements how state dependent commands are executed when the library
 * is Closed
 *
 * @author Nikolas Tilley
 */
public class LibraryClosed implements LibraryState
{
    public LibraryClosed()
    {

    }

    public void stateBeginVisit(Integer visitorID, VisitorStorage visitorStorage)
    {
        // TODO Need to update the observed string or something that this didnt happen
        System.out.println("nope") ;
    }

    public void stateCheckOutBook()
    {

    }

}
