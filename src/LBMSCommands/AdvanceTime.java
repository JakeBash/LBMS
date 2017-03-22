package LBMSCommands;

import Library.Library;


/**
 * Advances the library forward in Time. The number of days can be between 0 and 7, and the number of hours can be between
 * 0 and 23
 * 
 * @author Nikolas Tilley
 */
public class AdvanceTime implements LBMSCommand
{
    private Library library;
    private int days;
    private int hours;

    /**
     * Description
     *
     * @param library
     * @param days
     */
    public AdvanceTime(Library library, int days)
    {
        this.library = library;
        this.days = days;
        this.hours = 0;
    }

    /**
     * Description
     *
     * @param library
     * @param days
     * @param hours
     */
    public AdvanceTime(Library library, int days, int hours)
    {
        this.library = library;
        this.days = days;
        this.hours = hours;
    }

    /**
     * Executes the AdvanceTime command on the library
     */
    public void execute()
    {
        library.advanceTime(days, hours);
    }
}
