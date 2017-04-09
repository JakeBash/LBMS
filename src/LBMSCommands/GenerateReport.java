package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Generates a report of library statistics.
 * Command format: clientID,report[,days];
 *
 * @author Nikolas Tilley
 */
public class GenerateReport implements LBMSCommand
{
    private Library library;
    // todo private Long clientID;
    private int days;

    /**
     * Creates a command to generate a statistical report for the library.
     */
    public GenerateReport(Library lib, int days)
    {
        this.library = lib;
        this.days = days;
    }

    /**
     * Executes the GenerateReport command on the library.
     */
    public void execute()
    {
        this.library.generateReport(days);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }
}
