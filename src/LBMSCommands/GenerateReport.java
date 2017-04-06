package LBMSCommands;

import Library.Library;

/**
 * Generates a report of library statistics.
 *
 * @author Nikolas Tilley
 */
public class GenerateReport implements LBMSCommand
{
    private Library library;

    /**
     * Creates a command to generate a statistical report for the library.
     */
    public GenerateReport(Library lib)
    {
        this.library = lib;
    }

    /**
     * Executes the GenerateReport command on the library.
     */
    public void execute()
    {
        this.library.generateReport();
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
