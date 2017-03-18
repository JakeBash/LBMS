package LBMSCommands;

import Library.Library;

/**
 * Begins a new visit by a registered visitor.
 * 
 * @author Nikolas Tilley
 */
public class BeginVisit implements LBMSCommand
{

    Library library ;
    Integer visitorID ;

    /**
     * Creates a begin BeginVisit Command
     * 
     * @param library, the library the visitor is visiting
     * @param visitorID, the ID of the visitor who is attemting to visit
     */
    public BeginVisit(Library library, Integer visitorID)
    {
        this.library = library ;
        this.visitorID = visitorID ;
    }

    public void execute()
    {
        library.beginVisit(visitorID);
    }
}
