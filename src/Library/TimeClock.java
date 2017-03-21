package Library;

import java.util.Calendar;
import java.util.Date;
// Todo DateFormat

/**
 * Description
 *
 * @author Nikolas Tilley
 * @version 0.1
 */
public class TimeClock
{
    private int dayOffset;
    private int hourOffset;

    public TimeClock()
    {
        dayOffset = 0;
        hourOffset = 0;
    }

    /**
     * @param dayOffset  the number of days offset fromt the current date
     * @param hourOffset the number of hours offset from the current time
     */
    public TimeClock(int dayOffset, int hourOffset)
    {
        this.dayOffset = dayOffset;
        this.hourOffset = hourOffset;
    }

    // Get offset number of days
    public int getOffDay()
    {
        return dayOffset;
    }

    // Get offset number of hours
    public int getOffHour()
    {
        return hourOffset;
    }

    public Date getCurrentDateTime()
    {
        Calendar c = Calendar.getInstance() ;
        c.add(c.DAY_OF_WEEK, dayOffset);
        c.add(c.HOUR_OF_DAY, hourOffset);
        return c.getTime();
    }

    /**
     * Advances the clock forward in Time
     * the number of days can be between 0 and 7,
     * the number of hours can be between 0 and 23
     *
     * @param days  the number of days to advance
     * @param hours the number of hours to advance
     */
    public void advanceTime(int days, int hours)
    {
        dayOffset += days;
        hourOffset += hours;
    }

    public static void main(String[] args) {
        TimeClock time = new TimeClock(2, 4);

        // Test 1
        if (time.getOffHour() == 4)
            System.out.println("PASSED Test - 1");
        else
            System.out.println("FAILED Test - 1: Expected 4, got " + time.getOffHour());



    }

}