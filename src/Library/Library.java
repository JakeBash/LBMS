package Library;

import Books.*;
import BooksCatalog.BookCatalog;
import BooksCatalog.FlatFileBookCatalog;
import UIS.PTUI;
import Sort.*;
import Visitors.CheckOut;
import Visitors.Visit;
import Visitors.VisitorStorage;
import Visitors.Visitor;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.File;

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
    private BookCatalog bookCatalog;
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
        this.bookCatalog = new FlatFileBookCatalog(new File("files/books.txt"));
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
        String response = "info,";

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
        else if(sortOrder.equals("none"))
        {
            ;
        }
        else
        {
            response += "invalid-sort-order;";
            updateStatus(response);
            return;
        }

        response += searchRes.size() + "\n";
        for(Book b : searchRes)
        {
            response += b.toString("bSearch") + ";\n";
        }

        updateStatus(response);
    }


    /**
     * Performs a search for books in the bookstore
     *
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s)
     * @param publisher - The publisher of the desired book(s).
     * @param sortOrder - The sort order to be used when gathering the desired book(s).
     */
    public void bookStoreSearch(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        ArrayList<Book> searchRes = this.bookCatalog.bookSearch(title, authors, isbn, publisher);
        String response = "info,";

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
        else if(sortOrder.equals("none"))
        {
            ;
        }
        else
        {
            response += "invalid-sort-order;";
            updateStatus(response);
            return;
        }

        response += searchRes.size() + "\n";
        for(Book b : searchRes)
        {
            response += b.toString("bSearch") + ";\n";
        }

        updateStatus(response);
    }


    public void borrowBooks(ArrayList<Integer> bkID,Long vID) {

        Visitor currentV = visitorStorage.getVisitor(vID);
        ArrayList<Book> books = new ArrayList<>();

        for(Integer id : bkID) {
            for (Book bk : bookStorage.getLastSearch()) {
                if (bk.getTempID() == id) {
                    books.add(bk);
                    break;
                }
            }
        }
        currentV.checkOutBooks(books,this.getTime());
    }

    /**
     * Description
     */
    public void purchaseBooks(int quantity, ArrayList<Integer> ids)
    {
        ArrayList<Book> purchasedBooks = this.bookCatalog.purchase(quantity, ids);

        bookStorage.addBooks(purchasedBooks, quantity);

        String response = "buy,success\n";

        for (Book b : purchasedBooks)
        {
            response += b.toString("bPurchase") + ";\n";
        }

        updateStatus(response);
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
        String response = "register,";

        if (newVis != null)
        {
            response += newVis.getID() + "," + this.timeClock.getFormattedDate() + ";";
        }
        else
        {
            response += "duplicate;";
        }

        updateStatus(response);
    }

    /**
     * Begins a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the visitor that is starting their visit.
     */
    public void beginVisit(Long visitorID)
    {
        String response = currentState.stateBeginVisit(visitorID, this.visitorStorage);
        updateStatus(response);
    }

    /**
     * Ends a visit for a registered visitor.
     *
     * @param visitorID - The ID of the visitor currently at the library.
     */
    public void endVisit(Long visitorID)
    {
        Visit visit = this.visitorStorage.endVisit(visitorID);

        String response = "depart,";

        if(visitorStorage.getVisitor(visitorID) == null)
        {
            updateStatus(response + "invalid-id;");
            return;
        }
        else if (visit != null)
        {
            response += visitorID + "," + visit.getFormattedDate(visit.getEndDateTime()) + "," + visit.getFormattedTime(visit.getEndDateTime()) + ";";
        }
        else
        {
            response += "duplicate;";
        }

        updateStatus(response);
    }

    /**
     * Retrieves a list of books currently checked out for a registered visitor.
     *
     * @param visitorID - The visitor being queried for their checked out books.
     */
    public void getVisitorCheckedOutBooks(Long visitorID)
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

        updateStatus(response);
    }

    /**
     * Pay a given amount toward a visitor's fine
     *
     * @param visitorID - id of visitor paying the fine
     * @param amount - amount to pay
     */
    public void payFine(Long visitorID, int amount)
    {
        this.visitorStorage.payFine(visitorID, amount);
    }

    /**
     * Generates a statistical report of the library
     *
     */
    public void generateReport()
    {
        //TODO: Add in the rest of the report data needed
        LocalDate localDate = LocalDate.now();
        updateStatus(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n"
                + this.bookStorage.generateReport() + "\n"
                + this.visitorStorage.generateReport() + "\n");

    }

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

    /**
     * Description
     */
    public void close()
    {
        // end all current Visits
        this.currentState = stateList.get(CLOSED);
    }

    /**
     * Description
     */
    public void open()
    {
        this.currentState = stateList.get(OPEN);
    }

    /**
     * Description
     */
    public void getFormattedDateTime()
    {
        updateStatus("datetime," + timeClock.getFormattedDateTime() + ";" );
    }

    /**
     * Gets the time of the system in a Date object
     *
     * @return The Time Clock's current time.
     */
    public Calendar getTime()
    {
        return timeClock.getCalendarDate();
    }

    /**
     * Method to advance the time of the library.
     *
     * @param days the number of days to advance.
     * @param hours the number of hours to advance.
     */
    public void advanceTime(int days, int hours)
    {

        if ((days >= 0 && days <= 7) && (hours >= 0 && hours <= 23))
        {
            timeClock.advanceTime(days, hours);
            visitorStorage.endAllVisits();
            //generateReport();
            updateStatus("advance,success;" );
        }
        else if (days < 0 || days > 7)
        {
            updateStatus("advance,invalid-number-of-days," + days +";" );
        }
        else if (hours < 0 || hours > 23)
        {
            updateStatus("advance,invalid-number-of-hours," + hours +";" );
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

    /**
     * Updates the status string of the model and notifies any observers.
     */
    public void updateStatus(String status){
        this.status = status;
        this.setChanged();
        this.notifyObservers();
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
