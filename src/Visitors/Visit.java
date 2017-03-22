package Visitors;

import java.util.Calendar;
import java.util.Date;

/**
 * A single visit in the library
 *
 * @author Kyler Freas
 */
public class Visit implements java.io.Serializable
{
    private Calendar startDateTime;
    private Calendar endDateTime;
    private Integer visitorID;

    /**
     * Default constructor. Initializes with start time and visitor ID.
     */
    public Visit(Calendar startTime, Integer visitorID)
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
     * Getter for visitor's id field
     *
     * @return visitor's id field
     */
    public Integer getVisitorID()
    {
        return this.visitorID;
    }

    /**
     * Getter for visit's start time
     *
     * @return visit's start time
     */
    public Calendar getStartDateTime()
    {
        return this.startDateTime;
    }

    /**
     * Getter for visit's end time
     *
     * @return visit's end time
     */
    public Calendar getEndDateTime()
    {
        return this.endDateTime;
    }
}
