package LBMSCommands;

import Library.Library;

/**
 * Queries for a list of books currently borrowed by a specific visitor.
 * Command format: borrowed,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class FindBorrowed implements LBMSCommand
{
    private Library library;
    private Long visitorID;

    /**
     * A command that asks the library to find a visitor's checked out books.
     *
     * @param library - A library that has books checked out by visitors.
     * @param visitorID - The visitor that is being queried for checked out books.
     */
    public FindBorrowed(Library library, Long visitorID)
    {
        this.library = library;
        this.visitorID = visitorID;
    }

    /**
     * Executes the FindBorrowed command on the library.
     */
    public void execute()
    {
        library.getVisitorCheckedOutBooks(visitorID);
    }
}
