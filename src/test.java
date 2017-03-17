import Books.Book;
import Books.BookStorage;
import java.util.ArrayList;
import java.util.Arrays;
import Visitors.Visitor;
import Visitors.VisitorStorage;


/**
 * Created by Tyler on 3/13/2017.
 *
 */
public class test
{
    public static void main(String[] args)
    {
        testAddBooks();
    }

    public static void testAddBooks()
    {
        BookStorage bStore = new BookStorage();
        Book book1 = new Book("1111111111111", "Test Book Title 1", new ArrayList<String>(Arrays.asList("Example Author 1")), "Example Publisher 4", "2017-3-16", 500);
        Book book2 = new Book("2222222222222", "Test Book Title 2", new ArrayList<String>(Arrays.asList("Example Author 2", "Example Author 4")), "Example Publisher 4", "2017-3-16", 100);
        Book book3 = new Book("3333333333333", "Test Book Title 3", new ArrayList<String>(Arrays.asList("Example Author 3")), "Example Publisher 4", "2017-3-16", 250);
        ArrayList<Book> purchase = new ArrayList<>(Arrays.asList(book1, book2, book3));
        bStore.addBooks(purchase, 500);
        for(Book b : bStore.getBooks().values())
        {
            System.out.println(b.toString());
        // Test visitor storage
        testStoreVisitors();

        // Test generating report from visitor data
        testGenerateVisitorReport();

        // All tests passed!
        System.out.println("Testing succeeded");
    }

    // Test saving and retrieving visitor storage
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

    // Test generating partial report from visitor storage
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

        visitorStorage.endVisit(visitorID);

        // Generate the report
        String visitorReport = visitorStorage.generateReport();

        // Check for correct report data
        if (!visitorReport.equals("1,00:00:01")) {
            throw new java.lang.Error("Saved visitor could not be retrieved");
        }
    }
}
