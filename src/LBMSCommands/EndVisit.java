package LBMSCommands;

import Library.Library;

/**
 * Ends a visit in progress.
 * Command format: depart,visitor ID.
 * 
 * @author Nikolas Tilley
 */
public class EndVisit implements LBMSCommand
{
    private Library library;
    private Long visitorID;

    /**
     * Constructs an EndVisit command to be executed.
     * 
     * @param library - The library that the visitor was visiting.
     * @param visitorID - The ID of an active visitor.
     */
    public EndVisit(Library library, Long visitorID)
    {
        this.library = library;
        this.visitorID = visitorID;
    }

    /**
     * Executes the EndVisit command on the library.
     */
    public void execute()
    {
        library.endVisit(visitorID);
    }
}
