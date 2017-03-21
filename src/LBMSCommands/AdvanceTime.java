package LBMSCommands;

import Library.TimeClock;

/**
 * Advances the clock forward in Time. The number of days can be between 0 and 7, and the number of hours can be between
 * 0 and 23
 * 
 * @author Nikolas Tilley
 */
public class AdvanceTime implements LBMSCommand
{
    private TimeClock clock;
    private int days;
    private int hours;

    /**
     * Description
     *
     * @param clock
     * @param days
     */
    public AdvanceTime(TimeClock clock, int days)
    {
        this.clock = clock;
        this.days = days;
        this.hours = 0;
    }

    /**
     * Description
     *
     * @param clock
     * @param days
     * @param hours
     */
    public AdvanceTime(TimeClock clock, int days, int hours)
    {
        this.clock = clock;
        this.days = days;
        this.hours = hours;
    }

    /**
     * Executes the AdvanceTime command on the library
     */
    public void execute()
    {
        clock.advanceTime(days, hours);
    }
}
