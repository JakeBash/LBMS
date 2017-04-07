package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Queries for a list of books currently borrowed by a specific visitor.
 * Command format: clientID,borrowed,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class FindBorrowed implements LBMSCommand
{
    private Library library;
    // Todo private Long clientID;
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

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }






    private void parse(String s)
    {

        ArrayList<String> args = new ArrayList<String>();

        String arg = "";


        for(char c : s.toCharArray())
        {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            }
            else
                arg += c;
        }

    }
}
