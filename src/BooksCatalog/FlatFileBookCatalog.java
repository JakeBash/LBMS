package BooksCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import Books.Book;
import Client.Client;

/**
 * Handles the storage of the currently used library catalog. Holds books objects that employees of the library can
 * interact with to  search for and purchase new books for the library.
 *
 * @author Tyler Reimold
 */
public class FlatFileBookCatalog implements BookCatalog
{
    private List<Book> books;
    private ArrayList<Book> lastSearch;

    /**
     * Constructor for the FlatFileBookCatalog. Attempts to load a .txt file containing the Book Catalog.
     */
    public FlatFileBookCatalog(File file)
    {
        try
        {
            books = CSVParser.load(file);
        }
        catch (FileNotFoundException error)
        {
            System.out.println("File not found");
        }
    }

    /**
     * Getter method to retrieve the most recent search of the Book Catalog.
     *
     * @return An ArrayList representing the most recent search of the Book Catalog.
     */
    public ArrayList<Book> getlastSearch()
    {
        return lastSearch;
    }
    
    /**
     * Given a set of user search criteria, returns the books that meet the supplied criteria.
     *
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s)
     * @param publisher - The publisher of the desired book(s).
     * @return A String ArrayList representing the applicable books for the given command inputs.
     */
    public ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher)
    {
        // ArrayList of books that meet the current search criteria.
        ArrayList<Book> searchBooks = new ArrayList<>();
        // Loop to iterate all of the supplied search criteria.
        for (int step = 1; step <= 5; step++)
        {
            searchBooks = searchStep(step, title, authors, isbn, publisher, searchBooks);
            lastSearch = searchBooks;
        }
        return searchBooks;
    }

    /**
     * Helper method for bookSearch(). Creates an ArrayList with the applicable books at each step of the search
     * criteria process.
     *
     * @param step - The current level of search criteria being processed.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s)
     * @param publisher - The publisher of the desired book(s).
     * @param prevSearchBooks - The ArrayList of books that were gathered from the previous search step.
     * @return An ArrayList representing the applicable books for the current supplied search criteria.
     */
    private ArrayList<Book> searchStep(int step, String title, ArrayList<String> authors, String isbn, String publisher, ArrayList<Book> prevSearchBooks)
    {
        ArrayList<Book> newSearchBooks = new ArrayList<>();
        int tempID = 1;

        if(step == 1)
        {
            if(title.equals("*"))
            {
                for (Book b : this.books)
                {
                    b.setTempID(tempID++);
                    prevSearchBooks.add(b);
                }
            }
            else
            {
                for (Book b : this.books)
                {
                    if(b.getTitle().contains(title))
                    {
                        b.setTempID(tempID++);
                        prevSearchBooks.add(b);
                    }
                }
            }
            return prevSearchBooks;
        }
        else if(step == 2)
        {
            if(authors.contains("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for (Book b : prevSearchBooks)
                {
                    // Might not work as we want it. This will return TRUE if the stored book has ALL of the authors
                    // that the command supplies.
                    if(b.getAuthor().containsAll(authors))
                    {
                        b.setTempID(tempID++);
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else if(step == 3)
        {
            if(isbn.equals("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for(Book b : prevSearchBooks)
                {
                    if(b.getIsbn() == isbn)
                    {
                        b.setTempID(tempID++);
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else if(step == 4)
        {
            if(publisher.equals("*"))
            {
                return prevSearchBooks;
            }
            else
            {
                for (Book b : prevSearchBooks)
                {
                    if(b.getPublisher() == publisher)
                    {
                        b.setTempID(tempID++);
                        newSearchBooks.add(b);
                    }
                }
            }
            return newSearchBooks;
        }
        else
        {
            return prevSearchBooks;
        }
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args)
    {
        BookCatalog meme = new FlatFileBookCatalog(new File("files/books.txt"));
        ArrayList<String> authors = new ArrayList<>();
        authors.add("*");
        System.out.println(meme.bookSearch("*",authors,"*","*")) ;
    }
}