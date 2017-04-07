package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Ends a visit in progress.
 * Command format: clientID,depart,visitor ID.
 * 
 * @author Nikolas Tilley
 */
public class EndVisit implements LBMSCommand
{
    private Library library;
    // todo private Long clientID;
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
