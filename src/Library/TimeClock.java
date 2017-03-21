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
    private int daysAdvanced ;
    private int hoursAdvanced ;

	 public TimeClock()
	 {
         daysAdvanced = 0 ;
         hoursAdvanced = 0 ;
	 }
    
    /**
     *
     * @param daysAdvanced the number of days offset fromt the current date
     * @param hoursAdvanced the number of hours offset from the current time
     */
	 public TimeClock(int daysAdvanced, int hoursAdvanced)
	 {
         this.daysAdvanced = daysAdvanced ;
         this.hoursAdvanced = hoursAdvanced ;
	 }
    
 
    // Add Days to offset
    // Get offset number of days
    public int getOffDay()
    {
        return daysAdvanced ;
    }

    
    // Add Hours to offset
    // Get offset number of hours
    public int getOffHour()
    {
        return hoursAdvanced ;
    }

    public String getTime()
    {
        return Calendar.getInstance().getTime().toString() ;
    }

    /**
     * Advances the clock forward in Time
     * the number of days can be between 0 and 7,
     * the number of hours can be between 0 and 23
     * 
     * @param days the number of days to advance
     * @param hours the number of hours to advance
     */
    public void advanceTime(int days, int hours)
    {
        daysAdvanced += days ;
        hoursAdvanced += hours ;
    }

    public void getCurrentDateTime()
    {
        // TODO - implement clock and how to get time including offsets from advance
    }
    
    /**
     * Notifies the list of subscribers to check the time
     *
     */
    public void Notify()
    {
    }
    
    
   public static void main (String [] args) {
       TimeClock time = new TimeClock(2, 4) ;

       // Test 1
       if (time.getOffHour() == 4)
           System.out.println("PASSED Test - 1") ;
       else
           System.out.println("FAILED Test - 1: Expected 4, got " + time.getOffHour()) ;
   
   }
    
}