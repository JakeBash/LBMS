package BooksCatalog;

import Books.Book;
import java.util.ArrayList;

/**
 * This interface lays out a base structure for a BookCatalog. We will later add an implementation of this interface to
 * allow the use of Google Books web services for the purchasing and querying of the book catalog.
 *
 * @author Tyler Reimold
 */
public interface BookCatalog
{
    /**
     * Performs a search on the current available catalog of books.
     *
     * @param title - The title of the book(s) that is being searched for.
     * @param authors - The author of the books(s) that is being searched for.
     * @param isbn - The ISBN of the book(s) being searched for.
     * @param publisher - The publisher of the book(s) that is being searched for.
     * @return An ArrayList representing the the book(s) that meet the search criteria.
     */
    ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher);

    /**
     * Retrieves the results from the mst recent search of the Book Catalog.
     *
     * @return An ArrayList representing the results from the last search of the Book Catalog.
     */
    ArrayList<Book> getLastSearch();

    /**
     * Initiates a purchase of the desired books from that are chosen from the most recent Book Catalog search.
     *
     * @param quantity - The amount of books to be purchased.
     * @param ids - The temporary id's of the books that are to be purchased.
     * @return An ArrayList representing the purchased books.
     */
    ArrayList<Book> purchase(int quantity, ArrayList<Integer> ids);
}
