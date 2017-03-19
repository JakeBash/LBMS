package LBMSCommands;

import Library.Library;

/**
 * Ends a visit in progress.
 * 
 * command format: depart,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class EndVisit implements LBMSCommand
{

    private Library library ;
    private Integer visitorID ; // This may have to be a string that is converted to int...

    /**
     * Constructs an EndVisit command to be executed
     * 
     * @param library - the library that the visitor was visiting.
     * @param visitorID - the ID of an active visitor.
     */
    public EndVisit(Library library, Integer visitorID)
    {
        this.library = library ;
        this.visitorID = visitorID ;
    }


    /**
     * Calls the end visit method in the library
     * 
     * VisitorStorage.endVisit(Integer visitorID)
     * Library.endVisit(Integer visitorID)
     */
    public void execute()
    {
        library.endVisit(visitorID) ;
    }
}
