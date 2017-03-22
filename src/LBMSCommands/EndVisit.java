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
    private Long visitorID; // This may have to be a string that is converted to int...

    /**
     * Constructs an EndVisit command to be executed.
     * 
     * @param library - the library that the visitor was visiting.
     * @param visitorID - the ID of an active visitor.
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
