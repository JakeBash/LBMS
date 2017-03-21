package Visitors;

import Library.Library;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

/**
 * Represents the library's saved visitors and visits. Provides methods to save, retrieve and query from all visitors
 * and visits in the system.
 *
 * @author Kyler Freas
 */

public class VisitorStorage implements java.io.Serializable
{
    // Reference to the library to check the library's time
    // Not persisted in storage
    private transient Library library;

    // Registered visitors
    private HashMap<Integer, Visitor> visitors;

    // Visits currently taking place in the library
    private HashMap<Integer, Visit> activeVisits;

    // Full history of visits in the library
    private ArrayList<Visit> visitHistory;

    // Data file location
    private static String file = "files/VisitorStorage.ser";

    /**
     * Default constructor. Initializes with empty visitor and visit hashes.
     */
    public VisitorStorage(Library library)
    {
        this.library = library;
        this.visitors = new HashMap<>();
        this.activeVisits = new HashMap<>();
        this.visitHistory = new ArrayList<>();
    }

    /**
     * Returns a registered visitor matching a given ID.
     *
     * @param ID - The ID of the registered visitor.
     * @return The visitor associated with the supplied ID.
     */
    public Visitor getVisitor(Integer ID)
    {
        return this.visitors.get(ID);
    }

    /**
     * Registers a new visitor in the system. The visitor is added to the hash and saved to a text file at shutdown.
     * Registration assigns the registered visitor a unique ID for storage.
     *
     * @param firstName - The first name of the visitor to be registered
     * @param lastName - the last name of the visitor to be registered.
     * @param address - The address of the visitor to be registered.
     * @param phoneNumber - The phone number of the visitor to be registered.
     * @return The newly registered visitor.
     */
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        // Generate the new visitor
        Visitor visitor = new Visitor(firstName, lastName, address, phoneNumber);

        // Check if visitor is already registered.
        // Registration is aborted if visitor already exists.
        if (this.visitors.containsValue(visitor)) { return null; }

        // Increment the visitor IDs
        Integer newKey;

        // If this is the first registration, start at 0
        if (this.visitors.keySet().size() == 0)
        {
            newKey = 0;
        }
        else
        {
            Integer lastKey = Collections.max(this.visitors.keySet());
            newKey = lastKey + 1;
        }

        // Set the visitor's id and store
        visitor.setID(newKey);
        this.visitors.put(newKey, visitor);

        // Return the new visitor
        return visitor;
    }

    /**
     * Begins a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the registered visitor.
     */
    public void startVisit(Integer visitorID)
    {
        //TODO: Need to add the functionality to not allow a new visit of someone already in the library
        // Check if visitor is already visiting the library
        if (this.activeVisits.containsKey(visitorID)) { return; }

        // Create a new visit and add it to active
        Visit visit = new Visit(this.library.getTime(), visitorID);
        this.activeVisits.put(visitorID, visit);
    }

    /**
     * Ends a visit in the library for a currently active visitor.
     *
     * @param visitorID - The ID of the currently active visitor.
     */
    public void endVisit(Integer visitorID)
    {
        //TODO: Need to add the functionality for trying to end a visit for a visitor ID that doesn't exist/isn't currently at the library.

        // Check that visitor is actually visiting
        if (!this.activeVisits.containsKey(visitorID)) { return; }

        // Find the visit for the given visitor ID
        Visit visit = this.activeVisits.get(visitorID);

        // Add in end time to visit
        visit.end(this.library.getTime());

        // Move from active visits to visit history
        this.activeVisits.remove(visit.getVisitorID());
        this.visitHistory.add(visit);
    }

    /**
     * Gets the total outstanding balance of visitor fines
     *
     * @return total fine amount
     */
    private int getTotalUnpaidFines() {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        int totalBalance = 0;
        for (Visitor visitor: this.visitors.values())
        {
            totalBalance += visitor.getBalance();
        }

        return totalBalance;
    }

    /**
     * Gets the total amount of fines paid by visitors
     *
     * @return total paid fine amount
     */
    private int getTotalPaidFines() {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        int totalBalance = 0;
        for (Visitor visitor: this.visitors.values())
        {
            totalBalance += visitor.getFinesPaid();
        }

        return totalBalance;
    }

    /**
     * Generates the visitor data stored, in string format, to be included in a statistical report.
     * Data includes: Total number of visitors, Average length of visit
     *
     * @return A String representing the applicable Visitor data for a statistical report.
     */
    public String generateReport()
    {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time

        // String to hold the report data
        String reportString;

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

        // Get total fines collected
        String totalFinesPaid = String.valueOf(this.getTotalPaidFines());

        // Get total outstanding fines
        String totalFinesOutstanding = String.valueOf(this.getTotalUnpaidFines());

        // Add data to the report and return
        reportString = "Number of Visitors: " + totalVisitors + "\n"
                + "Average Length of Visit: " + averageStay + "\n"
                + "Fines Collected: " + totalFinesPaid + "\n"
                + "Fines Outstanding: " + totalFinesOutstanding;

        return reportString;
    }

    /**
     * Sets the library object for this VisitorStorage.
     * Used upon deserialization to link with Library's TimeClock
     *
     * @param library - library object to link with the VisitorStorage
     */
    public void setLibrary(Library library)
    {
        this.library = library;
    }

    /**
     * Serialize the entire visitor storage and save it to a text file. Since this only happens at shutdown, all active
     * visits are ended and saved.
     */
    public void serialize()
    {
        // End all active visits
        for (Visit visit : this.activeVisits.values())
        {
            this.endVisit(visit.getVisitorID());
        }
        // Save to file
        try
        {
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

    /**
     * Deserializes a VisitorStorage from the file
     *
     * @return An instance of VisitorStorage generated from the previously saved .ser file.
     */
    public static VisitorStorage deserialize(Library library)
    {
        try
        {
            // Read from the file into input stream
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Initialize storage from data
            VisitorStorage visitorStorage = (VisitorStorage) in.readObject();
            visitorStorage.setLibrary(library);

            // Close the streams and return
            in.close();
            fileIn.close();
            return visitorStorage;
        }
        catch (EOFException eof)
        {
            // Start a fresh storage
            return new VisitorStorage(library);
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
        return new VisitorStorage(library);
    }
}
