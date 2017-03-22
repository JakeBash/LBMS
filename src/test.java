import Books.Book;
import Books.BookStorage;
import java.util.ArrayList;
import java.util.Arrays;
import Visitors.Visitor;
import Library.Library;

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
    // Library object for executing test commands
    private static Library library = new Library();

    /**
     * Default constructor used to run and execute our created tests.
     */
    public static void main(String[] args)
    {
        // Test the addition of newly purchased books
        testAddBooks();

        // Test internal search functionality for library owned books
        testBookStorageSearch();

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

        //ArrayList<Book> searchBooks = bStore.bookSearch("apple", new ArrayList<String>(Arrays.asList("Example Author 1")), "1111111111111", "Example Publisher 4", "*");

        //for(Book b : searchBooks)
        //{
            //TODO: Might want to make a helper string for book search result format, also if there are no matching results, nothing is printed.
            //System.out.println("info," + searchBooks.size() + b.toString("bSearch"));
        //}
    }
}
