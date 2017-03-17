package Books;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class that handles the libraries' internal storage of purchased library books. The library will interact with
 * storage class to get information about library books (ISBN, Title, Authors, Copies Left, etc), as well as to store
 * newly purchased books.
 *
 * @author Jake Bashaw
 */
public class BookStorage implements java.io.Serializable
{
    /**
     * Owned books that are currently stored in the library
     */
    private HashMap<String, Book> books;

    /**
     * Log of book purchases performed by the library. MIGHT ACTUALLY WANT TO BE HELD IN THE BOOK CATALOG CLASS
     */
    //private ArrayList<Purchase> bookPurchases;

    /**
     * Data file location
     */
    private static String file = "files/BookStorage.ser";

    /**
     * Default constructor. Initializes with an empty book storage HashMap.
     */
    public BookStorage()
    {
        this.books = new HashMap<>();
    }

    /**
     * Simple getter method for external retrieval of the libraries' book storage
     *
     * @return A HashMap representing the current book storage of the library
     */
    public HashMap<String, Book> getBooks()
    {
        return this.books;
    }

    /**
     * Given a list of purchased books and the associated quantity of purchased books, they are added to the book
     * storage. If the book already exists in the storage, the amount of copies owned by the library is increased.
     *
     * @param purchasedBooks - An ArrayList representing the most recent purchase from the BookCatalog
     * @param quantity - The amount of book copies purchased in the most recent transaction
     */
    public void addBooks(ArrayList<Book> purchasedBooks, int quantity)
    {
        // Iterates over the supplied list of books
        for(int i=0; i<purchasedBooks.size(); i++)
        {
            Book book = purchasedBooks.get(i);
            // Checks if the book exists in the storage already
            if(this.books.containsKey(book.getIsbn()))
            {
                // If it does, then just add the newly purchased copies
                this.books.get(book.getIsbn()).addCopies(quantity);
            }
            else
            {
                // Adds the book to the storage and increments the amount of owned books.
                this.books.put(book.getIsbn(), book);
                this.books.get(book.getIsbn()).addCopies(quantity);
            }
        }
    }

    /**
     * Generates the pertinent information from the BookStorage class for use in a library statistical report.
     *
     * @return A string representing the pertinent information for use in the report.
     */
    public String generateReport()
    {
        String reportString = "";
        int numBooks = 0;

        // Get the total number of books owned by the library
        for(Book book : this.books.values())
        {
            numBooks += book.getNumCopies();
        }

        // Get the total number of book purchases in the date range
        //TODO: Don't know how to do this for a date range. Might have to be done in BookCatalog.

        return reportString;
    }

    /**
     * Serialize the entire book storage and save it to a text file.
     */
    public void serialize()
    {
        // Save to file
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserializes a BookStorage from the file
     */
    public static BookStorage deserialize() {
        try {
            // Read from the file into input stream
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Initialize storage from data
            BookStorage bookStorage = (BookStorage) in.readObject();

            // Close the streams and return
            in.close();
            fileIn.close();
            return bookStorage;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("BookStorage could not be found");
            c.printStackTrace();
        }

        // If an error occurs, return an empty book storage
        return new BookStorage();
    }
}
