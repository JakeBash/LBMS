package Visitors;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * Represents the library's saved visitors and visits.
 * Provides methods to save, retrieve and query from all visitors and visits in the system.
 *
 * @author Kyler Freas
 */

public class VisitorStorage implements java.io.Serializable
{
    // Registered visitors
    private HashMap<Integer, Visitor> visitors;

    // Visits currently taking place in the library
    private HashMap<Integer, Visit> activeVisits;

    // Full history of visits in the library
    private ArrayList<Visit> visitHistory;

    // Data file location
    private static String file = "files/VisitorStorage.ser";

    // Default constructor. Initializes with empty visitor and visit hashes.
    public VisitorStorage()
    {
        this.visitors = new HashMap<>();
        this.activeVisits = new HashMap<>();
        this.visitHistory = new ArrayList<>();
    }

    // Returns a registered visitor matching a given ID
    public Visitor getVisitor(Integer ID)
    {
        return this.visitors.get(ID);
    }

    // Registers a new visitor in the system.
    // The visitor is added to the hash and saved to a text file at shutdown.
    // Registration assigns the registered visitor a unique ID for storage.
    // Returns the new visitor's ID
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        // Generate the new visitor
        Visitor visitor = new Visitor(firstName, lastName, address, phoneNumber);

        // Increment the visitor IDs
        Integer newKey;

        // If this is the first registration, start at 0
        if (this.visitors.keySet().size() == 0)
        {
            newKey = 0;
        } else {
            Integer lastKey = Collections.max(this.visitors.keySet());
            newKey = lastKey + 1;
        }

        // Set the visitor's id and store
        visitor.setID(newKey);
        this.visitors.put(newKey, visitor);

        // Return the new visitor
        return visitor;
    }

    // Begins a visit in the library
    public void startVisit(Integer visitorID)
    {
        // Create a new visit and add it to active
        Visit visit = new Visit(new Date(), visitorID);
        this.activeVisits.put(visitorID, visit);
    }

    // Ends a visit in the library
    public void endVisit(Integer visitorID)
    {
        // Find the visit for the given visitor ID
        Visit visit = this.activeVisits.get(visitorID);

        // Add in end time to visit
        visit.end();

        // Move from active visits to visit history
        this.activeVisits.remove(visit.getVisitorID());
        this.visitHistory.add(visit);
    }

    // Returns the visitor data stored, in string format, to be included in
    // a statistical report.
    // Data includes:
    //     Total number of visitors
    //     Average length of visit
    public String generateReport()
    {
        // String to hold the report data
        String reportString = "";

        // Get total # visitors and visits
        int totalVisitors = this.visitors.keySet().size();
        int totalVisits = this.visitHistory.size();

        // Calculate the average length of stay
        // Total visit time (milliseconds)
        long totalTime = 0;

        for (Visit visit : this.visitHistory)
        {
            Date start = visit.getStartTime();
            Date end = visit.getEndTime();

            totalTime += end.getTime() - start.getTime();
        }

        // Average length of stay, in milliseconds
        long milis = totalTime / totalVisits;

        // Format the date (hh:mm:ss)
        int hours = (int) ((milis / (1000*60*60)) % 24);
        int minutes = (int) ((milis / (1000*60)) % 60);
        int seconds = (int) (milis / 1000) % 60;

        String strHours = String.format("%02d", hours);
        String strMinutes = String.format("%02d", minutes);
        String strSeconds = String.format("%02d", seconds);

        String averageStay = strHours + ":" + strMinutes + ":" + strSeconds;

        // Add data to the report and return
        reportString = totalVisitors + "," + averageStay;

        return reportString;
    }

    // Serialize the entire visitor storage and save it to a text file.
    // Since this only happens at shutdown, all active visits are ended and saved.
    public void serialize()
    {
        // End all active visits
        for (Visit visit : this.activeVisits.values())
        {
            this.endVisit(visit.getVisitorID());
        }

        // Save to file
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    // Deserializes a VisitorStorage from the file
    public static VisitorStorage deserialize() {
        try {
            // Read from the file into input stream
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Initialize storage from data
            VisitorStorage visitorStorage = (VisitorStorage) in.readObject();

            // Close the streams and return
            in.close();
            fileIn.close();
            return visitorStorage;
        }
        catch (EOFException eof)
        {
            // Start a fresh storage
            return new VisitorStorage();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("VisitorStorage could not be found");
            c.printStackTrace();
        }

        // If an error occurs, return an empty storage
        return new VisitorStorage();
    }
}
