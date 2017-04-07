package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Begins a new visit at the library for a registered visitor.
 *
 * Command format: clientID,arrive,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class BeginVisit implements LBMSCommand
{
    private Library library;
    // Todo private Long clientID; // Have to add this information to command
    private Long visitorID;

    /**
     * Creates a BeginVisit command object.
     * 
     * @param library - The library the visitor is visiting.
     * @param visitorID - The ID of the visitor who is attempting to visit.
     */
    public BeginVisit(Library library, Long visitorID)
    {
        this.library = library;
        this.visitorID = visitorID;
    }

    /**
     * Executes the BeginVisit command on the library.
     */
    public void execute()
    {
        library.beginVisit(visitorID);
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

        // Check valid input with try catchs for casting or something
        // depending on length you will convert different
        // Todo delete for release
        for(String ss : args)
            System.out.println(ss);

    }
}
