package LBMSCommands;

import Library.Library;

/**
 * Queries for a list of books currently borrowed by a specific visitor.
 * 
 * command format: borrowed,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class FindBorrowed implements LBMSCommand
{

    private Library library ;
    private Integer visitorID ;

    /**
     * A command that asks the library to find a visitors checked out books
     *
     * @param library - a library that has books checked out by visitors
     * @param visitorID - the visitor that is being queried for checked out books
     */
    public FindBorrowed(Library library, Integer visitorID)
    {
        this.library = library ;
        this.visitorID = visitorID ;
    }

    /**
     *
     * Library.getCheckedoutBooks(Integer visitorID) ->
     *  Visitor.getCheckedOutBooks()
     */
    public void execute()
    {
        library.getVisitorCheckedOutBooks(visitorID) ; // TODO This method needs to be implemented in library still
    }
}
