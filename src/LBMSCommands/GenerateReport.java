package LBMSCommands;

import Library.Library;

/**
 * Description
 *
 * @author
 */
public class GenerateReport implements LBMSCommand
{
    private Library library;


    /**
     * Description
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
}
