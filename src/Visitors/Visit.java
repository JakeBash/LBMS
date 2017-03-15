package Visitors;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * A single visit in the library
 *
 * @author Kyler Freas (kmf5285)
 */
public class Visit implements java.io.Serializable
{
    private Date startTime;
    private Date endTime;
    private String visitorID;

    // Default constructor
    public Visit(Date startTime, String visitorID)
    {
        this.startTime = startTime;
        this.visitorID = visitorID;
    }

    // Sets the end time of the visit
    public void end()
    {
        this.endTime = new Date();
    }

    public String getVisitorID()
    {
        return this.visitorID;
    }

    public Date getStartTime()
    {
        return this.startTime;
    }

    public Date getEndTime()
    {
        return this.endTime;
    }
}
