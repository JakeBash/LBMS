package LBMSCommands;

import Library.Library;

/**
 * Displays the current date and time in the simulation. This should include any days that have been added to the
 * calendar using the command to advance time.
 *
 * Command format: datetime;
 *
 * @author Nikolas Tilley
 */
public class Shutdown implements LBMSCommand
{
    private Library library;

    /**
     *  Creates a new GetTime command object.
     *
     * @param library - The clock keeping track of the date and time.
     */
    public Shutdown(Library library)
    {
        this.library = library;
    }

    /**
     * Executes the GetTime command on the library.
     */
    public void execute()
    {
        library.shutdown();
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
