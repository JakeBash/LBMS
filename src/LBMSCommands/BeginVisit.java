package LBMSCommands;

import Library.Library;

/**
 * Begins a new visit at the library for a registered visitor.
 * 
 * @author Nikolas Tilley
 */
public class BeginVisit implements LBMSCommand
{
    private Library library;
    private Long visitorID;

    /**
     * Creates a BeginVisit command object.
     * 
     * @param library, the library the visitor is visiting.
     * @param visitorID, the ID of the visitor who is attempting to visit.
     */
    public BeginVisit(Library library, Long visitorID)
    {
        this.library = library;
        this.visitorID = visitorID;
    }

    /**
     * Executes the BeginVisit command on the library
     */
    public void execute()
    {
        library.beginVisit(visitorID);
    }
}
