package Visitors;

import java.util.Calendar;

/**
 * A single visit in the library.
 *
 * @author Kyler Freas
 */
public class Visit implements java.io.Serializable
{
    private Calendar startDateTime;
    private Calendar endDateTime;
    private Long visitorID;

    /**
     * Default constructor. Initializes with start time and visitor ID.
     */
    public Visit(Calendar startTime, Long visitorID)
    {
        this.startDateTime = startTime;
        this.visitorID = visitorID;
    }

    /**
     * Sets the end time of the visit. Called by the Library when a visit should end.
     */
    public void end(Calendar endTime)
    {
        this.endDateTime = endTime;
    }

    /**
     * Getter for the visitor's ID field.
     *
     * @return The visitor's ID field.
     */
    public Long getVisitorID()
    {
        return this.visitorID;
    }

    /**
     * Getter for the visit's start time.
     *
     * @return The visit's start time.
     */
    public Calendar getStartDateTime()
    {
        return this.startDateTime;
    }

    /**
     * Getter for the visit's end time.
     *
     * @return The visit's end time.
     */
    public Calendar getEndDateTime()
    {
        return this.endDateTime;
    }

    /**
     * Formats the current date in the form YYYY/MM/DD.
     *
     * @return A String with the formatted date.
     */
    public String getFormattedDate(Calendar datetime)
    {
        return datetime.get(Calendar.YEAR) + "/" + datetime.get(Calendar.MONTH) + "/" + datetime.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Formats the current time in the form HH:MM:SS.
     *
     * @return A String with the formatted time.
     */
    public String getFormattedTime(Calendar datetime)
    {
        return datetime.get(Calendar.HOUR_OF_DAY) + ":" + datetime.get(Calendar.MINUTE) + ":" + datetime.get(Calendar.SECOND);
    }
}
