import Books.Book;
import Books.BookStorage;
import java.util.ArrayList;
import java.util.Arrays;
import Visitors.Visitor;
import Visitors.VisitorStorage;

/**
 * This is used to test modules of code as they are created.
 *
 * @author Jake Bashaw
 * @author Kyler Freas
 * @author Nikolas Tilley
 * @author Tyler Reimold
 * @author Kyle Kaniecki
 */
public class test
{
    /**
     * Default constructor used to run and execute our created tests.
     */
    public static void main(String[] args)
    {
        // Test the addition of newly purchased books
        //testAddBooks();

        // Test internal search functionality for library owned books
        testBookStorageSearch();

        // Test visitor storage
        //testStoreVisitors();

        // Test generating report from visitor data
        //testGenerateVisitorReport();

        // All tests passed!
        System.out.println("Testing succeeded");
    }

    /**
     * Test adding newly purchased to the library's internal storage.
     */
    public static void testAddBooks()
    {
        BookStorage bStore = new BookStorage();

        Book book1 = new Book("1111111111111", "Test Book Title 1", new ArrayList<String>(Arrays.asList("Example Author 1")), "Example Publisher 4", "2017-3-16", 500);
        Book book2 = new Book("2222222222222", "Test Book Title 2", new ArrayList<String>(Arrays.asList("Example Author 2", "Example Author 4")), "Example Publisher 4", "2017-3-16", 100);
        Book book3 = new Book("3333333333333", "Test Book Title 3", new ArrayList<String>(Arrays.asList("Example Author 3")), "Example Publisher 4", "2017-3-16", 250);
        ArrayList<Book> purchase = new ArrayList<>(Arrays.asList(book1, book2, book3));

        bStore.addBooks(purchase, 500);
        for(Book b : bStore.getBooks().values()) {
            System.out.println(b.toString());
        }
    }

    /**
     * Test searching the libraries internal storage for books
     */
    public static void testBookStorageSearch()
    {
        BookStorage bStore = new BookStorage();

        Book book1 = new Book("1111111111111", "apple", new ArrayList<String>(Arrays.asList("Example Author 1")), "Example Publisher 4", "2017-3-16", 500);
        Book book2 = new Book("2222222222222", "pear", new ArrayList<String>(Arrays.asList("Example Author 2", "Example Author 4")), "Example Publisher 4", "2017-3-16", 100);
        Book book3 = new Book("3333333333333", "sandwich", new ArrayList<String>(Arrays.asList("Example Author 3")), "Example Publisher 4", "2017-3-16", 250);
        ArrayList<Book> purchase = new ArrayList<>(Arrays.asList(book1, book2, book3));

        bStore.addBooks(purchase, 500);

        String[] commInfo = {"app","*","1234567891234","*","*"};

        ArrayList<Book> searchBooks = bStore.get(commInfo);

        for(Book b : searchBooks)
        {
            //TODO: Might want to make a helper string for book search result format, also if there are no matching results, nothing is printed.
            System.out.println("info," + searchBooks.size() + "," + b.getAvailableCopies() + "," + b.getIsbn() + "," + b.getTitle() + "," + b.authorString() + "," + b.getPublisher() + "," + b.getPublishDate() + "," + b.getPageCount());
        }
    }

    /**
     * Test saving and retrieving visitor storage.
     */
    private static void testStoreVisitors() {
        // Recover the currently saved visitor storage, if any
        VisitorStorage visitorStorage = VisitorStorage.deserialize();

        // Create a test visitor to register
        Visitor testVisitor = new Visitor("Test", "Visitor", "1234 Test Rd", "6078675309",
                new ArrayList<>(), new ArrayList<>(), 0);

        // Register the visitor
        visitorStorage.registerVisitor(testVisitor);
        Integer visitorID = testVisitor.getID();

        // Save the object
        visitorStorage.serialize();

        // Recover the object from the text file
        VisitorStorage recovered = VisitorStorage.deserialize();

        // Ensure that registered visitor was saved and retrieved correctly
        if (recovered.getVisitor(visitorID) == null) {
            throw new java.lang.Error("Saved visitor could not be retrieved");
        }
    }

    /**
     * Test generating partial report from visitor storage.
     */
    private static void testGenerateVisitorReport() {
        VisitorStorage visitorStorage = new VisitorStorage();

        // Create a test visitor to register
        Visitor testVisitor = new Visitor("Test", "Visitor", "1234 Test Rd", "6078675309",
                new ArrayList<>(), new ArrayList<>(), 0);
        Integer visitorID = testVisitor.getID();

        // Register the visitor
        visitorStorage.registerVisitor(testVisitor);

        // Start and end a visit at the library
        visitorStorage.startVisit(visitorID);

        // Sleep to cause a 1 second visit time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interrupted) {
            System.out.println("Sleep was interrupted");
        }

        // End the visit
        visitorStorage.endVisit(visitorID);

        // Generate the report
        String visitorReport = visitorStorage.generateReport();

        // Check for correct report data
        if (!visitorReport.equals("1,00:00:01")) {
            throw new java.lang.Error("Saved visitor could not be retrieved");
        }
    }
}
