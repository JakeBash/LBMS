package LBMSCommands;

import Library.TimeClock;

/**
 * Displays the current date and time in the simulation.
 * This should include any days that have been added to the calendar
 * using the command to advance time.
 *
 * command format: datetime;
 *
 * @author Nikolas Tilley
 */
public class GetTime implements LBMSCommand
{
    private TimeClock timeClock ;

    /**
     *  Creates a new GetTime command object
     *
     * @param timeClock the clock keeping track of the date and time
     */
    public GetTime(TimeClock timeClock)
    {
        this.timeClock = timeClock ;
    }

    /**
     * Executes the GetTime command
     */
    public void execute()
    {
        timeClock.getCurrentDateTime() ;
    }
}
