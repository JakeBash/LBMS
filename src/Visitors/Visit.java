package Visitors;

import java.util.Date;

/**
 * A single visit in the library
 *
 * @author Kyler Freas
 */
public class Visit implements java.io.Serializable
{
    private Date startTime;
    private Date endTime;
    private Integer visitorID;

    /**
     * Default constructor. Initializes with start time and visitor ID.
     */
    public Visit(Date startTime, Integer visitorID)
    {
        this.startTime = startTime;
        this.visitorID = visitorID;
    }

    /**
     * Sets the end time of the visit. Called by the Library when a visit should end.
     */
    public void end()
    {
        this.endTime = new Date();
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
    public Date getStartTime()
    {
        return this.startTime;
    }

    /**
     * Getter for visit's end time
     *
     * @return visit's end time
     */
    public Date getEndTime()
    {
        return this.endTime;
    }
}
