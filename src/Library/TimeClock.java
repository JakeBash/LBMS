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

    /**
     * Gets the number of offset days
     * @return the number of days the calendar has been advanced for simulations
     */
    public int getOffDay()
    {
        return dayOffset;
    }

    /**
     * Gets the number of offset hours
     * @return the number of hours the calendar has been advanced for simulations
     */
    public int getOffHour()
    {
        return hourOffset;
    }

    /**
     * Gets the date and time for the instance the method is invoked
     * @return a Date object that accounts for the the day and hour offsets
     */
    public Date getCurrentDateTime()
    {
        Calendar c = Calendar.getInstance();
        c.add(c.DAY_OF_WEEK, dayOffset);
        c.add(c.HOUR_OF_DAY, hourOffset);
        return c.getTime();
    }

    /**
     * Gets the date in calendar format
     */
    public Calendar getCalendarDate()
    {
        Calendar c = Calendar.getInstance();
        c.add(c.DAY_OF_WEEK, dayOffset);
        c.add(c.HOUR_OF_DAY, hourOffset);
        return c;

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


    // TODO - Need to be able to save and come back from file

    public static void main(String[] args)
    {
        TimeClock time = new TimeClock(2, 4);
        //TimeClock time = new TimeClock();

        // Test 1
        if (time.getOffHour() == 4)
            System.out.println("PASSED Test - 1");
        else
            System.out.println("FAILED Test - 1: Expected 4, got " + time.getOffHour());

        // Test 2
        time.advanceTime(1, 0);
        if (time.getOffDay() == 3)
            System.out.println("PASSED Test - 2");
        else
            System.out.println("FAILED Test - 2: Expected 3, got " + time.getOffDay());

        if (time.getCurrentDateTime().compareTo(Calendar.getInstance().getTime()) > 0)
            System.out.println("PASSED Test - 3");
        else
            System.out.println("FAILED Test - 3: Expected our calendar with time advanced" +
                    "\nto be greater than the current time, got : " + time.getCurrentDateTime());

    }

}