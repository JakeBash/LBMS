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

    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        String response = "arrive,";

        if( visitorStorage.getVisitor(visitorID) == null )
            return response + "invalid-id;";

        Visit visit = visitorStorage.startVisit(visitorID);

        if (visit != null)
        {
            response += visitorID + "," + visit.getFormattedDate(visit.getStartDateTime()) + "," + visit.getFormattedTime(visit.getStartDateTime()) + ";";
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
