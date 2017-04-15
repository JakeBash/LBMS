package Library;

import Books.*;
import BooksCatalog.BookCatalog;
import BooksCatalog.FlatFileBookCatalog;
import Client.Client;
import Sort.*;
import Visitors.CheckOut;
import Visitors.Visit;
import Visitors.VisitorStorage;
import Visitors.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.File;
import LibraryProtectionProxy.LibrarySubject;

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
public class Library extends Observable implements LibrarySubject
{
    private final int OPEN = 0;
    private final int CLOSED = 1;

    private VisitorStorage visitorStorage;
    private BookStorage bookStorage;
    private BookCatalog bookCatalog;
    private String status;
    private TimeClock timeClock;
    private CheckTimeTask checkTimeTask;
    private Timer timer;
    private LibraryState currentState;
    private ArrayList<LibraryState> stateList;

    /**
     * Map of all current client connections. Can only be added to, no getter method.
     */
    private HashMap<Long, Client> clientList;

    /**
     * Initializes all required persistent state from existing files.
     */
    public Library()
    {
        // Init library states
        this.stateList = new ArrayList<LibraryState>();
        this.stateList.add(new LibraryOpen());
        this.stateList.add(new LibraryClosed());
        this.currentState = this.stateList.get(0);

        // Initialize client map
        this.clientList = new HashMap<Long, Client>();

        this.status = "";
        // Initialized with reference to self to give access to TimeClock
        this.visitorStorage = VisitorStorage.deserialize(this);
        this.bookStorage = BookStorage.deserialize(this);
        this.bookCatalog = new FlatFileBookCatalog(new File("files/books.txt"));
        this.timeClock = TimeClock.deserialize();

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
    public void bookSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        ArrayList<Book> searchRes = this.bookStorage.bookSearch(title, authors, isbn, publisher);
        String response = clientID + ",info,";

        if (sortBookList(searchRes, sortOrder))
        {
            response += searchRes.size() + "\n";
            for(Book b : searchRes)
            {
                response += b.toString("bSearch") + ";\n";
            }

            updateStatus(response); // Todo depreciate
            updateClientStatus(clientID, response);

        }
        else {
            response += "invalid-sort-order;";
            updateStatus(response); // todo depreciate
            updateClientStatus(clientID, response);

        }

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
    public void bookStoreSearch(Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        ArrayList<Book> searchRes = this.bookCatalog.bookSearch(title, authors, isbn, publisher);
        String response = clientID + ",search,";

        if (sortBookList(searchRes, sortOrder))
        {
            response += searchRes.size() + "\n";
            for(Book b : searchRes)
            {
                response += b.toString("sSearch") + ";\n";
            }
            this.getClient(clientID).setLastStoreSearch(searchRes);

            updateStatus(response); // todo depreciate
            updateClientStatus(clientID, response);

        }
        else {
            response += "invalid-sort-order;";
            updateStatus(response); // todo depreciate
            updateClientStatus(clientID, response);

        }
    }

    /**
     * This method borrows books for a specified visitor.
     * Handed off to the State of the Library
     *
     * @param bkID - An ArrayList of Book ISBNs
     * @param vID - A Visitor ID.
     */
    public void borrowBook(Long clientID, ArrayList<String> bkID,Long vID)
    {
        String str = clientID + "," + this.currentState.stateCheckOutBook(bkID, vID, this.visitorStorage, this.timeClock, this.bookStorage);
        updateStatus(str); // todo depreciate
    }

    /**
     * Purchases books from a recent Book Catalog search.
     *
     * @param quantity - The amount of books to be purchased.
     * @param ids - The temporary ID's of the books to be purchased.
     */
    public void purchaseBooks(Long clientID, int quantity, ArrayList<Integer> ids)
    {
        ArrayList<Book> purchasedBooks = this.bookCatalog.purchase(quantity, ids);

        bookStorage.addBooks(purchasedBooks, quantity, this.getTime());

        String response = clientID + ",buy,success\n";

        for (Book b : purchasedBooks)
        {
            response += b.toString("bPurchase") + quantity +";\n";
        }

        updateStatus(response); // todo depreciate
        updateClientStatus(clientID, response);
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
    public void registerVisitor(Long clientID, String firstName, String lastName, String address, String phoneNumber)
    {
        Visitor newVis = visitorStorage.registerVisitor(firstName, lastName, address, phoneNumber);
        String response = clientID + ",register,";

        if (newVis != null)
        {
            response += newVis.getID() + "," + this.timeClock.getFormattedDate() + ";";
        }
        else
        {
            response += "duplicate;";
        }

        updateStatus(response); // Todo depreciate
        updateClientStatus(clientID, response);
    }

    /**
     * Begins a visit in the library for a registered visitor.
     *
     * @param visitorID - The ID of the visitor that is starting their visit.
     */
    public void beginVisit(Long clientID, Long visitorID)
    {
        String response = clientID + "," + currentState.stateBeginVisit(visitorID, this.visitorStorage);
        updateStatus(response); // Todo depreciate
        updateClientStatus(clientID, response);
    }

    /**
     * Ends a visit for a registered visitor.
     *
     * @param visitorID - The ID of the visitor currently at the library.
     */
    public void endVisit(Long clientID, Long visitorID)
    {
        Visit visit = this.visitorStorage.endVisit(visitorID);

        String response = clientID + ",depart,";

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

        updateStatus(response); // Todo depreciate
        updateClientStatus(clientID, response);
    }

    /**
     * Retrieves a list of books currently checked out for a registered visitor.
     * Finds borrowed/checked out books
     *
     * @param visitorID - The visitor being queried for their checked out books.
     */
    public void getVisitorCheckedOutBooks(Long clientID, Long visitorID)
    {
        Visitor visitor = this.visitorStorage.getVisitor(visitorID);
        String response = clientID + ",borrowed,";

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

        updateStatus(response); // todo depreciate
        updateClientStatus(clientID, response);
    }

    /**
     * Pay a given amount toward a visitor's fine
     *
     * @param visitorID - id of visitor paying the fine
     * @param amount - amount to pay
     */
    public void payFine(Long clientID, Long visitorID, int amount)
    {
        // Todo this needs to make responses
        this.visitorStorage.payFine(visitorID, amount);


        String response = clientID + ",success" + "," + amount + ";";
        updateClientStatus(clientID, response);

    }

    /**
     * Generates a statistical report of the library
     *
     */
    public void generateReport(Long clientID, int days)
    {
        //TODO: Add in the rest of the report data needed
        LocalDate localDate = LocalDate.now();
        String response = clientID + "," + DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate) + "\n"
                + this.bookStorage.generateReport(days) + "\n"
                + this.visitorStorage.generateReport(days) + "\n";


        updateStatus(response); // Todo depreciate
        updateClientStatus(clientID, response);

    }

    /**
     * Checks the time of the Time Clock. Changes the state of the library depending on the time.
     */
    public void checkTime()
    {
        int hour = timeClock.getCalendarDate().get(Calendar.HOUR_OF_DAY);

        if((hour < 8 || hour >= 19) && (this.currentState == stateList.get(OPEN)))
            close();
        else if ((hour >= 8 && hour < 19) && (this.currentState == stateList.get(CLOSED)))
            open();
        else
           ; // Do nothing
    }

    /**
     * Changes the current state of the library from open to closed.
     */
    private void close()
    {
        this.visitorStorage.endAllVisits();
        this.currentState = stateList.get(CLOSED);
    }

    /**
     * Changes the current state of the library from closed to open.
     */
    private void open()
    {
        this.currentState = stateList.get(OPEN);
    }

    /**
     * Gets a formatted date and time for use in system responses.
     */
    public void getFormattedDateTime(Long clientID)
    {
        updateStatus(clientID + ",datetime," + timeClock.getFormattedDateTime() + ";" ); // todo depreciated
        updateClientStatus(clientID, clientID + ",datetime," + timeClock.getFormattedDateTime() + ";" );
    }

    /**
     * Gets the time of the system in a Date object.
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
     * @param days - The number of days to advance.
     * @param hours - The number of hours to advance.
     */
    public void advanceTime(Long clientID, int days, int hours)
    {

        if ((days >= 0 && days <= 7) && (hours >= 0 && hours <= 23))
        {
            timeClock.advanceTime(days, hours);
            visitorStorage.endAllVisits();
            //generateReport();
            updateStatus(clientID + ",advance,success;" ); // todo depreciate
            updateClientStatus(clientID, clientID + ",advance,success;");
        }
        else if (days < 0 || days > 7)
        {
            updateStatus(clientID + ",advance,invalid-number-of-days," + days +";" ); // todo depreciate
            updateClientStatus(clientID, clientID + ",advance,invalid-number-of-days," + days +";" );

        }
        else if (hours < 0 || hours > 23)
        {
            updateStatus(clientID + ",advance,invalid-number-of-hours," + hours +";" ); // todo depreciate
            updateClientStatus(clientID, clientID + ",advance,invalid-number-of-days," + hours +";" );

        }


    }


    public void returnBooks(Long clientID, Long visitorID, ArrayList<String> isbns)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book: this.bookStorage.getBooks().values())
        {
            if (isbns.contains(book.getIsbn()))
            {
                books.add(book);
            }
        }

        double fines = this.visitorStorage.returnBooks(visitorID, books);

        if (fines > 0) {
            updateStatus(clientID + ",return,overdue,$" + Double.toString(fines) + isbns + ";"); // Todo depreciate
            updateClientStatus(clientID, clientID + ",return,overdue,$" + Double.toString(fines) + isbns + ";");
        } else {
            updateStatus(clientID + ",return,success;"); // todo depreciate
            updateClientStatus(clientID, clientID + ",return,success;");
        }
    }


    /**
     * Returns the current status for use with command responses.
     */
    public String getStatus()
    {
       return this.status;
    }

    public String getClientStatus(Long clientID)
    {
        if (clientList.get(clientID) != null)
            return clientList.get(clientID).getStatus();
        else
            return "invalid-client-id;";
    }



    /**
     * Shut down the system, persisting all data created in flat files.
     */
    public void shutdown(Long clientID)
    {
        //TODO: Serialize all other entities to be persisted
        this.timeClock.serialize();
        this.visitorStorage.serialize();
        this.bookStorage.serialize();
        System.exit(0);
    }

    /**
     * Creates new client connection with library.
     */
    public void clientConnect(Long clientID)
    {
        this.clientList.put(clientID, new Client(clientID));
    }

    /**
     * Ends the connection with the ACTIVE CLIENT.
     */
    public void clientDisconnect(Long clientID)
    {
        this.clientList.remove(clientID);
        // Todo client should be notified somehow... probably at proxy or cmd parser level
    }

    /**
     * Helper method for getting client
     */
    private Client getClient(Long clientID){
        return this.clientList.get(clientID);
    }


    ///////////////////////// R2 Requirements /////////////////////////

    public void createAccount(Long clientID, String username, String password, String role, Long visitorID)
    {
        String response;

        if (visitorStorage.getVisitor(visitorID) == null)
        {
            response = clientID + ",create,invalid-visitor;";
            updateClientStatus(clientID, response);
        }
        else
        {
            String result = visitorStorage.createAccountCheck(username, visitorID);

            if (result.equals("duplicate username"))
            {
                response = clientID + ",create,duplicate-username;";
            }
            else if (result.equals("duplicate visitor"))
            {
                response = clientID + ",create,duplicate-visitor;";
            }
            else
            {
                visitorStorage.getVisitor(visitorID).createAccount(username, password, role);
                visitorStorage.addTakenUsername(username);
                response = clientID + ",create,success;";
            }
            updateClientStatus(clientID, response);
        }
    }

    public void login()
    {

    }

    public void logout()
    {

    }

    public void setService()
    {

    }

    public void forwardResponse(Long clientID, String response)
    {
        updateClientStatus(clientID, response);
    }

    ///////////////////////// Helper Methods for Updating Status ////////////////////////////

    /**
     * Updates the status string of the model and notifies any observers.
     */
    public void updateStatus(String status)
    {
        this.status = status;
        this.setChanged();
        this.notifyObservers();
    }

    // Todo implement me!!!!!
    public void updateClientStatus(Long clientID, String status)
    {
        if (clientList.get(clientID) != null)
        {
            clientList.get(clientID).setStatus(status);
            System.out.println(clientList.get(clientID).getStatus()); // Todo remove -- here for debugging
        }
        // setchanged
        // notifyobservers
    }

    ///////////////////////// Helper Methods for Sorting Lists of Books ////////////////////////////

    private boolean sortBookList(ArrayList<Book> searchRes, String sortOrder)
    {
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
            return false;
        }
        return true;
    }

    /**
     *  Main method for testing.
     */
    public static void main(String [] args)
    {
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
     * Constructor to create a CheckTimeTask.
     *
     * @param library - The library that is checking the time.
     */
    public CheckTimeTask(Library library)
    {
        this.library = library;
    }

    /**
     * Tells the library to checkTime when the Thread is created.
     */
    public void run()
    {
        library.checkTime();
    }
}
