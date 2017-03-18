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
    private ArrayList<Purchase> bookPurchases;

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
        for(Book b : purchasedBooks)
        {
            // Checks if the book exists in the storage already
            if(this.books.containsKey(b.getIsbn()))
            {
                // If it does, then just add the newly purchased copies
                this.books.get(b.getIsbn()).addCopies(quantity);
            }
            else
            {
                // Adds the book to the storage and increments the amount of owned books.
                this.books.put(b.getIsbn(), b);
                this.books.get(b.getIsbn()).addCopies(quantity);
            }
        }
    }

    /**
     * Given a set of user search criteria, returns the books that meet the supplied criteria.
     *
     * @param commInput - The user input depicting the desired search information.
     * @return A String ArrayList representing the applicable books for the given command inputs.
     */
    public ArrayList<Book> get(String ... commInput)
    {
        // Counter to keep track of current search criteria being examined
        int step = 1;
        // ArrayList of books that meet the current search criteria
        ArrayList<Book> searchBooks = new ArrayList<>();
        // Loop to iterate all of the supplied search criteria.
        for(String criteria : commInput)
        {
            searchBooks = searchStep(step, criteria, searchBooks);
            step++;
        }
        return searchBooks;
    }

    private ArrayList<Book> searchStep(int step, String criteria, ArrayList<Book> prevSearchBooks)
    {
        ArrayList<Book> newSearchBooks = new ArrayList<>();

        if(step == 1)
        {
            if(criteria.equals("*"))
            {
                for (Book b : this.books.values())
                {
                    prevSearchBooks.add(b);
                }
            }
            else
            {
                for (Book b : this.books.values())
                {
                    if(b.getTitle().contains(criteria))
                    {
                        prevSearchBooks.add(b);
                    }
                }
            }
            return prevSearchBooks;
        }
        else if(step == 2)
        {
            if(criteria.equals("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for (Book b : prevSearchBooks)
                {
                    if(b.getAuthor().contains(criteria))
                    {
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else if(step == 3)
        {
            if(criteria.equals("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for (Book b : prevSearchBooks)
                {
                    if(b.getIsbn().contains(criteria))
                    {
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else if(step == 4)
        {
            if(criteria.equals("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for (Book b : prevSearchBooks)
                {
                    if(b.getPublisher().contains(criteria))
                    {
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else
        {
            //TODO Add Sort Order support
            return prevSearchBooks;
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
