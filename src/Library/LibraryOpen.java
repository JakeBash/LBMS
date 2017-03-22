package Library;

import Visitors.Visit;
import Visitors.VisitorStorage;

/**
 * Implements how state dependent commands are executed when the library is open.
 *
 * @author Nikolas Tilley
 */
public class LibraryOpen implements LibraryState
{
    /**
     * Constructor for the open library state.
     */
    public LibraryOpen()
    {

    }

    /**
     * Starts a visit for a registered user at the library.
     *
     * @param visitorID - The ID of the visitor who is starting their visit.
     * @param visitorStorage - The storage of visitors that is being accessed.
     * @return A String representing the output that will displayed to the user.
     */
    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        String response = "arrive,";

        if(visitorStorage.getVisitor(visitorID) == null)
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

    /**
     * Checks out a book for a registered visitor at the library.
     *
     * @return A String representing the output that will displayed to the user.
     */
    public String stateCheckOutBook()
    {
        //TODO: Return the correct response
        return "" ;
    }
}
