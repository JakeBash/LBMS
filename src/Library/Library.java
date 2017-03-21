package Library;

import Books.BookStorage;
import Books.Book;
import Visitors.VisitorStorage;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
public class Library
{
    private VisitorStorage visitorStorage;
    private BookStorage bookStorage;

    private TimeClock timeClock ;
    private CheckTimeTask checkTimeTask ;
    private Timer timer ;

    /**
     * Initializes visitor and book storage from existing files.
     */
    public Library()
    {
        // TODO: Add catalog, purchases, state
        this.visitorStorage = VisitorStorage.deserialize();
        this.bookStorage = BookStorage.deserialize();


        // TODO Needs to read from file the days and hours advanced
        this.timeClock = new TimeClock() ;

        // Have to cancel task and Timer when shutting down
        this.timer = new Timer("Task Timer") ;
        this.checkTimeTask = new CheckTimeTask(this) ;
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
     * @return An arrayList representing the books that meet the supplied search criteria.
     */
    public ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        return this.bookStorage.bookSearch(title, authors, isbn, publisher, sortOrder);
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
        Visitor visitor = this.visitorStorage.getVisitor(visitorID) ;

        if ( visitor != null )
            return visitor.getCheckedOutBooks() ;
        else
            return null ;
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
        String report = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n" + this.bookStorage.generateReport() + "\n" + this.visitorStorage.generateReport() + "\n";
        return report;
    }

    //TODO: Add remaining commands
    public void checkTime()
    {
        System.out.println("The time is: " + timeClock.getTime()) ;
        // Logic to tell library to change state
    }



    // FOR TESTING
    public static void main(String [] args)
    {
        Library lib = new Library() ;
    }


}

/**
 * A runnable class that tells the library to
 * check the time
 */
class CheckTimeTask extends TimerTask
{
    private Library library ;

    /**
     * @param library - the library that is checking the time ;
     */
    public CheckTimeTask(Library library)
    {
        this.library = library ;
    }

    /**
     * Tells the library to checkTime when the Thread is created
     *
     */
    public void run()
    {
        library.checkTime() ;
    }

}
