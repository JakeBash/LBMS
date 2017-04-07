package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Displays the current date and time in the simulation. This should include any days that have been added to the
 * calendar using the command to advance time.
 *
 * Command format: clientID,datetime;
 *
 * @author Nikolas Tilley
 */
public class GetTime implements LBMSCommand
{
    private Library library;
    // Todo private Long clientID;

    /**
     * Creates a new GetTime command object.
     *
     * @param library - The clock keeping track of the date and time.
     */
    public GetTime(Library library)
    {
        this.library = library;
    }

    /**
     * Executes the GetTime command on the library.
     */
    public void execute()
    {
        library.getFormattedDateTime();
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
