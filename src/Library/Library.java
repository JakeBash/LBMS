package Library;

import Books.*;
import UIS.PTUI;
import Sort.*;
import Visitors.CheckOut;
import Visitors.VisitorStorage;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private final int OPEN = 0 ;
    private final int CLOSED = 1 ;

    private VisitorStorage visitorStorage;
    private BookStorage bookStorage;
    private String status;
    private TimeClock timeClock;
    private CheckTimeTask checkTimeTask;
    private Timer timer;
    private LibraryState currentState ;
    private ArrayList<LibraryState> stateList ;

    /**
     * Initializes all required persistent state from existing files.
     */
    public Library()
    {
        // TODO: Add catalog, purchases
        this.status = "";

        // Initialized with reference to self to give access to TimeClock
        this.visitorStorage = VisitorStorage.deserialize(this);

        this.bookStorage = BookStorage.deserialize();
        this.timeClock = TimeClock.deserialize();
        // Have to cancel task and Timer when shutting down
        this.timer = new Timer("Task Timer");
        this.checkTimeTask = new CheckTimeTask(this);
        timer.schedule(checkTimeTask, 0, 15000);

        // Init library states
        this.stateList = new ArrayList<LibraryState>();
        this.stateList.add(new LibraryOpen());
        this.stateList.add(new LibraryClosed());
        this.currentState = this.stateList.get(0);

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
            response += b.toString("bSearch") + "\n;";
        }

        this.status = response;
        notifyObservers();
    }

    /**
     * Registers a new visitor in the library.
     *
     * @param firstName - The first name of the visitor to be registered
     * @param lastName - the last name of the visitor to be registered.
     * @param address - The address of the visitor to be registered.
     * @param phoneNumber - The phone number of the visitor to be registered.
     * @return The newly registered visitor.
     */
    public void registerVisitor(String firstName, String lastName, String address, String phoneNumber)
    {
        Visitor newVis = visitorStorage.registerVisitor(firstName, lastName, address, phoneNumber);
        if (newVis != null)
        {
            this.status = "register," + newVis.getID() + "," + this.getTime().toString() + ";";
        }
        else
        {
            this.status = "register,duplicate;";
        }
        notifyObservers();
    }

    /**
     * Getter method for retrieving a registered library visitor
     *
     * @param visitorID - The ID of the desired visitor.
     * @return The desired visitor
     */
    public Visitor getVisitor(Integer visitorID)
    {
        //TODO: Do we need this???
        return this.visitorStorage.getVisitor(visitorID);
    }

    /**
     * Begins a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the visitor that is starting their visit.
     */
    public void beginVisit(Integer visitorID)
    {
        //TODO: Need to add a case when the visitor starting their visit ins't already in the library (Duplicate)
        this.currentState.stateBeginVisit(visitorID, this.visitorStorage);
    }

    /**
     * Ends a visit for a registered visitor.
     *
     * @param visitorID - The ID of the visitor currently at the library.
     */
    public void endVisit(Integer visitorID)
    {
        //TODO: Need to add a case when the visitor ID supplied doesn't match up to any active visits.
        this.visitorStorage.endVisit(visitorID);
    }


    /**
     * Retrieves a list of books currently checked out for a registered visitor.
     *
     * @param visitorID - The visitor being queried for their checked out books.
     */
    public void getVisitorCheckedOutBooks(Integer visitorID)
    {
        Visitor visitor = this.visitorStorage.getVisitor(visitorID);
        String response = "borrowed,";

        if (visitor != null)
        {
            ArrayList<CheckOut> checkouts = visitor.getCheckOut();
            response += checkouts.size() + "\n";
            for (CheckOut c : checkouts)
            {
                response += c.getBook().toString("fBorrow") + c.getBorrowDate() + "\n";
            }
        }
        else
        {
            response += visitorID;
        }

        this.status = response;
        notifyObservers();
    }

    /**
     * Shut down the system, persisting all data created in flat files.
     */
    public void shutdown()
    {
        //TODO: Serialize all other entities to be persisted
        this.timeClock.serialize();
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
        //TODO: Create observer response for this when completed.
        LocalDate localDate = LocalDate.now();
        String report = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n"
                + this.bookStorage.generateReport() + "\n"
                + this.visitorStorage.generateReport() + "\n";

        return report;
    }

    //TODO: Add remaining commands

    /**
     * Checks the time of the Time Clock. Changes the state of the library depending on the time.
     */
    public void checkTime()
    {
        int hour = timeClock.getCalendarDate().get(Calendar.HOUR_OF_DAY) ;

        if((hour < 8 || hour >= 19) && (this.currentState == stateList.get(OPEN)))
            close();
        else if ((hour >= 8 && hour < 19) && (this.currentState == stateList.get(CLOSED)))
            open();
        else
            ; // Do nothing
    }

    // TODO - implment closing the library
    public void close()
    {
        // end all current Visits
        this.currentState = stateList.get(CLOSED);
    }
    public void open()
    {
        this.currentState = stateList.get(OPEN);
    }

    public void getFormattedDateTime()
    {
        this.status = "datetime," + timeClock.getFormattedDateTime() + ";" ;
        notifyObservers();
    }

    /**
     * Gets the time of the system in a Date object
     *
     * @return The Time Clock's current time.
     */
    public Date getTime()
    {
        return timeClock.getCurrentDateTime();
    }

    /**
     * Method to advance the time of the library.
     *
     * @param days the number of days to advance.
     * @param hours the number of hours to advance.
     */
    public void advanceTime(int days, int hours)
    {
        // if you advance thorugh closing time, need to close visits
        // TODO - handle if you advance time past closing or more than one day
        // TODO - only take numbers 0-7 for days and 0-23 for hours
        // TODO - Must generate reports after each advance

        if ((days >= 0 && days <= 7) && (hours >= 0 && hours <= 23))
        {
            timeClock.advanceTime(days, hours);
            this.status = "advance,success;" ;
            notifyObservers();
        }

    }

    /**
     * Description
     *
     * @return
     */
    public String getStatus()
    {
        return this.status;
    }

    public static void main(String [] args)
    {
        //TODO: Remove later
        Library lib = new Library();
    }
}

/**
 * A runnable class that tells the library to check the time.
 */
class CheckTimeTask extends TimerTask
{
    private Library library;

    /**
     * Description
     *
     * @param library - the library that is checking the time;
     */
    public CheckTimeTask(Library library)
    {
        this.library = library;
    }

    /**
     * Tells the library to checkTime when the Thread is created
     */
    public void run()
    {
        library.checkTime();
    }
}
