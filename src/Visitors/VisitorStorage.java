package Visitors;

import Library.Library;
import Books.Book;
import java.io.*;
import java.util.*;

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
    private HashMap<Long, Visitor> visitors;

    // Visits currently taking place in the library
    private HashMap<Long, Visit> activeVisits;

    // Full history of visits in the library
    private ArrayList<Visit> visitHistory;

    // Full list of taken usernames of visitors
    private HashMap<String, Visitor> usernames;

    // Data file location
    private static String file = "files/VisitorStorage.ser";

    // Default admin user
    private Visitor admin;

    /**
     * Default constructor. Initializes with empty visitor and visit hashes.
     */
    public VisitorStorage(Library library)
    {
        this.library = library;
        this.visitors = new HashMap<>();
        this.activeVisits = new HashMap<>();
        this.visitHistory = new ArrayList<>();
        this.usernames = new HashMap<>();

        // Create admin/default user
        this.admin = new Visitor("admin", "admin", "GCCIS Building 70", "5555555555");
        this.admin.createAccount("admin", "admin", "Employee");
        this.admin.setID(9000000000L);
        this.usernames.put("admin", this.admin);

        this.visitors.put(admin.getID(), admin);
    }

    /**
     * Returns a registered visitor matching a given ID.
     *
     * @param ID - The ID of the registered visitor.
     * @return The visitor associated with the supplied ID.
     */
    public Visitor getVisitor(Long ID)
    {
        return this.visitors.get(ID);
    }

    /**
     * Description
     *
     * @return
     */
    public HashMap<String, Visitor> getUsernames()
    {
        return this.usernames;
    }

    /**
     * Registers a new visitor in the system. The visitor is added to the hash and saved to a text file at shutdown.
     * Registration assigns the registered visitor a unique ID for storage.
     *
     * @param firstName - The first name of the visitor to be registered
     * @param lastName - The last name of the visitor to be registered.
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

        // Generate a random visitor ID
        Long newKey = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

        // Set the visitor's id, registered date and store
        visitor.register(newKey, this.library.getTime());
        this.visitors.put(newKey, visitor);

        // Return the new visitor
        return visitor;
    }

    /**
     * Description
     *
     * @param username -
     * @param visitorID -
     * @return
     */
    public String createAccountCheck(String username, Long visitorID)
    {
        if (this.getVisitor(visitorID).getUsername() != null)
            return "duplicate visitor";
        else if (this.usernames.containsKey(username))
            return "duplicate username";
        else
            return "success";
    }

    public void addTakenUsername(String username, Long visitorID)
    {
        this.usernames.put(username, this.getVisitor(visitorID));
    }

    public boolean login(String username, String password)
    {
        if (this.usernames.containsKey(username))
        {
            if (this.usernames.get(username).getPassword().equals(password))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public Visitor getVisitorByUsername(String username)
    {
        return this.usernames.get(username);
    }

    /**
     * Begins a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the registered visitor.
     * @return The newly created visit.
     */
    public Visit startVisit(Long visitorID)
    {
        // Check if visitor is already visiting the library
        if (this.activeVisits.containsKey(visitorID))
        {
            return null;
        }

        // Create a new visit and add it to active
        Visit visit = new Visit(this.library.getTime(), visitorID);
        this.activeVisits.put(visitorID, visit);
        return visit;
    }

    /**
     * Ends a visit in the library for a currently active visitor.
     *
     * @param visitorID - The ID of the currently active visitor.
     * @return The visit that has just been ended.
     */
    public Visit endVisit(Long visitorID)
    {
        // Check that visitor is actually visiting
        if (!this.activeVisits.containsKey(visitorID))
        {
            return null;
        }

        // Find the visit for the given visitor ID
        Visit visit = this.activeVisits.get(visitorID);

        // Add in end time to visit
        visit.end(this.library.getTime());

        // Move from active visits to visit history
        this.activeVisits.remove(visit.getVisitorID());
        this.visitHistory.add(visit);

        return visit;
    }

    /**
     * Gets the total outstanding balance of visitor fines.
     *
     * @return The total fine amount.
     */
    private int getTotalUnpaidFines(int days)
    {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        int totalBalance = 0;
        for (Visitor visitor: this.getFilteredVisitors(days))
        {
            totalBalance += visitor.getFinesUnpaid(days);
        }

        return totalBalance;
    }

    /**
     * Gets the total amount of fines paid by visitors.
     *
     * @return The total paid fine amount.
     */
    private int getTotalPaidFines(int days)
    {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        int totalBalance = 0;
        for (Visitor visitor: this.getFilteredVisitors(days))
        {
            totalBalance += visitor.getFinesPaid(days);
        }

        return totalBalance;
    }

    /**
     * Pays the fine associated with a registered visitor.
     *
     * @param visitorID - The ID of the visitor.
     * @param amount - The amount of the fine that is being paid.
     */
    public void payFine(Long visitorID, int amount)
    {
        Visitor visitor = this.getVisitor(visitorID);

        // Check for invalid visitor ID
        if (visitor == null) { return; }

        // Check for invalid amount
        if (amount < 0 || amount > visitor.getBalance()) { return; }

        visitor.payFine(amount, this.library.getTime());
    }

    public void undoPayFine(Long visitorID, int amount)
    {
        Visitor v = this.getVisitor(visitorID);

        if (v == null) { return; }

        v.undoPayFine(amount, this.library.getTime());

    }

    /**
     * Returns a list of books for a given visitor
     *
     * @param visitorID - id of visitor returning books
     * @param books - books to be returned
     */
    public double returnBooks(Long visitorID, ArrayList<Book> books)
    {
        Visitor visitor = this.getVisitor(visitorID);
        return visitor.returnBooks(books, this.library.getTime());
    }

    /**
     * Helper method for report generation.
     * Filters the visitor list by registered date for a given number of days
     * in the past.
     *
     * @param days - number of days in the past to collect data
     * @return list of filtered visitors
     */
    private ArrayList<Visitor> getFilteredVisitors(int days)
    {
        if (days == 0)
        {
            return new ArrayList<>(this.visitors.values());
        }
        ArrayList<Visitor> filteredVisitors = new ArrayList<>();

        for (Visitor visitor: this.visitors.values())
        {
            // Calculate the date range
            Calendar startDate = this.library.getTime();
            startDate.add(Calendar.DAY_OF_YEAR, -days);

            if (visitor.getRegisteredDate().after(startDate))
            {
                filteredVisitors.add(visitor);
            }
        }

        return filteredVisitors;
    }

    /**
     * Helper method for report generation.
     * Calculates the number of visitors registered in a given time frame.
     *
     * @param days - number of days in the past to collect data
     * @return number of visitors registered
     */
    private int getTotalVisitors(int days)
    {
        return this.getFilteredVisitors(days).size();
    }

    /**
     * Helper method for report generation.
     * Calculates the average length of stay at the library.
     *
     * @param days - number of days in the past to collect data
     * @return average visit time (hh:mm:ss)
     */
    private String getAverageVisit(int days)
    {
        // Holds the visits filtered by start date
        ArrayList<Visit> filteredVisits = new ArrayList<>();

        if (days == 0)
        {
            // Report on all data
            filteredVisits = this.visitHistory;
        }
        else
        {
            // Calculate the date range
            Calendar startDate = this.library.getTime();
            startDate.add(Calendar.DAY_OF_YEAR, -days);

            for (Visit visit: this.visitHistory)
            {
                if (visit.getStartDateTime().after(startDate))
                {
                    filteredVisits.add(visit);
                }
            }
        }

        int totalVisits = filteredVisits.size();

        // Prevent division by 0
        if (totalVisits == 0) { totalVisits = 1; }

        // Calculate the average length of stay
        // Total visit time (milliseconds)
        long totalTime = 0;

        for (Visit visit : filteredVisits)
        {
            Calendar start = visit.getStartDateTime();
            Calendar end = visit.getEndDateTime();
            totalTime += end.getTimeInMillis() - start.getTimeInMillis();
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

        return strHours + ":" + strMinutes + ":" + strSeconds;
    }

    /**
     * Generates the visitor data stored, in string format, to be included in a statistical report.
     * Data includes: Total number of visitors, Average length of visit.
     *
     * @return A String representing the applicable Visitor data for a statistical report.
     */
    public String generateReport(int days)
    {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        // String to hold the report data
        String reportString;

        // Get total # visitors
        int totalVisitors = this.getTotalVisitors(days);

        // Get average length of visits
        String avgVisitTime = this.getAverageVisit(days);

        // Get total fines collected
        int totalFinesPaid = this.getTotalPaidFines(days);

        // Get total outstanding fines
        int totalFinesOutstanding = this.getTotalUnpaidFines(days) - totalFinesPaid;

        // Add data to the report and return
        reportString = "Number of Visitors: " + totalVisitors + "\n"
                + "Average Length of Visit: " + avgVisitTime + "\n"
                + "Fines Collected: " + totalFinesPaid + "\n"
                + "Fines Outstanding: " + totalFinesOutstanding;

        return reportString;
    }

    /**
     * Sets the library object for this VisitorStorage.
     * Used upon deserialization to link with Library's TimeClock.
     *
     * @param library - Library object to link with the VisitorStorage.
     */
    public void setLibrary(Library library)
    {
        this.library = library;
    }

    /**
     * Ends all active visits. Visits are automatically put into visit history.
     */
    public void endAllVisits()
    {
        for (Visit visit : this.activeVisits.values())
        {
            this.endVisit(visit.getVisitorID());
        }
    }
    /**
     * Serialize the entire visitor storage and save it to a text file. Since this only happens at shutdown, all active
     * visits are ended and saved.
     */
    public void serialize()
    {
        // End all active visits
        this.endAllVisits();

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
     * Deserializes a VisitorStorage from the file.
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
