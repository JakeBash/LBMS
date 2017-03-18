package LBMSCommands;

import Library.TimeClock;

/**
 * 
 *
 * 
 * Advances the clock forward in Time
 * the number of days can be between 0 and 7,
 * the number of hours can be between 0 and 23
 * 
 * @author Nikolas Tilley
 */
public class AdvanceTime implements LBMSCommand
{

    TimeClock clock ;
    int days ;
    int hours ;
    public AdvanceTime(TimeClock clock, int days)
    {
        this.clock = clock ;
        this.days = days ;
        this.hours = 0 ;
    }

    public AdvanceTime(TimeClock clock, int days, int hours)
    {
        this.clock = clock ;
        this.days = days ;
        this.hours = hours ;
    }

    public void execute()
    {
        clock.advanceTime(days, hours);
    }
}
