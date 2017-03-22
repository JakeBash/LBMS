package Library;

import Visitors.Visit;
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
        Visit visit = visitorStorage.startVisit(visitorID);
        String response = "arrive,";
        if (visit != null)
        {
            response += visitorID + "," + visit.
        }
        else
        {
            response += "duplicate;";
        }

        return response;
    }

    public String stateCheckOutBook()
    {
        // Todo return the correct response

        return "" ;
    }


}
