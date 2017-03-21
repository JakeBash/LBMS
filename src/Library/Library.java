package Library;

import Books.*;
import UIS.PTUI;
import Sort.*;
import Visitors.VisitorStorage;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Library class serves as the "brain" of the LBMS system so-to-say. Interactions between the library's internal
 * storage of visitors and books is facilitated within this class.
 *
 * @author Kyler Freas
 * @author Jake Bashaw
 * @author Nikolas Tilley
 * @author Tyler Reimold
 * @author Kyle Kaniecki
 */
public class Library extends Observable
{
    private VisitorStorage visitorStorage;
    private BookStorage bookStorage;
    private String state;
    private TimeClock timeClock;
    private CheckTimeTask checkTimeTask;
    private Timer timer;

    /**
     * Initializes visitor and book storage from existing files.
     */
    public Library()
    {
        // TODO: Add catalog, purchases, state
        this.visitorStorage = VisitorStorage.deserialize();
        this.bookStorage = BookStorage.deserialize();
        this.state = "";

        // TODO Needs to read from file the days and hours advanced
        this.timeClock = new TimeClock();

        // Have to cancel task and Timer when shutting down
        this.timer = new Timer("Task Timer");
        this.checkTimeTask = new CheckTimeTask(this);
        timer.schedule(checkTimeTask, 0, 15000);

    }

    /**
     * Performs a search for books owned by the library.
     *
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s)
     * @param publisher - The publisher of the desired book(s).
     * @param sortOrder - The sort order to be used when gathering the desired book(s).
     */
    public void bookSearch(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        ArrayList<Book> searchRes = this.bookStorage.bookSearch(title, authors, isbn, publisher);
        String response = "info," + searchRes.size() + "\n";

        if (sortOrder.equals("title"))
        {
            ByTitle bt = new ByTitle();
            bt.sort(searchRes);
        }
        else if (sortOrder.equals("publish-date"))
        {
            ByPublishDate bpd = new ByPublishDate();
            bpd.sort(searchRes);
        }
        else if (sortOrder.equals("book-status"))
        {
            ByStatus bs = new ByStatus();
            bs.sort(searchRes);
        }

        for(Book b : searchRes)
        {
            response += b.toString("bSearch") + "\n";
        }

        this.state = response;
        notifyObservers();
    }

    /**
     * Registers a new visitor in the library. The response is the newly registered visitor.
     *
     * @param firstName - The first name of the visitor to be registered
     * @param lastName - the last name of the visitor to be registered.
     * @param address - The address of the visitor to be registered.
     * @param phoneNumber - The phone number of the visitor to be registered.
     * @return The newly registered visitor.
     */
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        return this.visitorStorage.registerVisitor(firstName, lastName, address, phoneNumber);
    }

    /**
     * Getter method for retrieving a registered library visitor
     *
     * @param visitorID - The ID of the desired visitor.
     * @return The desired visitor
     */
    public Visitor getVisitor(Integer visitorID)
    {
        return this.visitorStorage.getVisitor(visitorID);
    }

    /**
     * Begin a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the visitor that is starting their visit.
     */
    public void beginVisit(Integer visitorID)
    {
        this.visitorStorage.startVisit(visitorID);
    }

    /**
     * Ends a visit for a registered visitor.
     *
     * @param visitorID - The ID of the visitor currently at the library.
     */
    public void endVisit(Integer visitorID)
    {
        this.visitorStorage.endVisit(visitorID);
    }


    /**
     * Returns a list of books that a visitor
     *
     * @param visitorID - the visitor being queried for their checked out book
     * @return - an ArrayList that contains between 0 - 5 checked out books
     */
    public ArrayList<Book> getVisitorCheckedOutBooks(Integer visitorID)
    {
        // TODO - handel null Visitor in visitor storage
        Visitor visitor = this.visitorStorage.getVisitor(visitorID);

        if ( visitor != null )
            return visitor.getCheckedOutBooks();
        else
            return null;
    }

    /**
     * Shut down the system, persisting all data created in flat files.
     */
    public void shutdown()
    {
        //TODO: Serialize all other entities to be persisted
        this.visitorStorage.serialize();
        this.bookStorage.serialize();
    }

    /**
     * Generates a statistical report of the library
     *
     * @return A String representing the statistical report for the library.
     */
    public String generateReport()
    {
        //TODO: Add in the rest of the report data needed
        LocalDate localDate = LocalDate.now();
        String report = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n"
                + this.bookStorage.generateReport() + "\n"
                + this.visitorStorage.generateReport() + "\n";

        return report;
    }

    //TODO: Add remaining commands

    /**
     * Checks the time of the Time Clock
     * Changes the state of the library depending on the time
     */
    public void checkTime()
    {
        System.out.println("The time is: " + timeClock.getCalendarDate());
        // Logic to tell library to change state
    }

    /**
     * Gets the time of the system in a Date object
     * @return the timeclocks current time
     */
    public Date getTime()
    {
        return timeClock.getCurrentDateTime();
    }

    /**
     * Method to advance the time of the library
     *
     * @param days the number of days to advance
     * @param hours the number of hours to advance
     */
    public void advanceTime(int days, int hours)
    {
        timeClock.advanceTime(days, hours);
    }


    public String getState()
    {
        return this.state;
    }

    // FOR TESTING
    public static void main(String [] args)
    {
        Library lib = new Library();
    }


}

/**
 * A runnable class that tells the library to
 * check the time
 */
class CheckTimeTask extends TimerTask
{
    private Library library;

    /**
     * @param library - the library that is checking the time;
     */
    public CheckTimeTask(Library library)
    {
        this.library = library;
    }

    /**
     * Tells the library to checkTime when the Thread is created
     *
     */
    public void run()
    {
        library.checkTime();
    }

}
