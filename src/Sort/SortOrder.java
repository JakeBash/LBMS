package Sort;

import Books.Book;
import java.util.ArrayList;

/**
 * An interface to implement the SortOrder strategy. A user should be able to select the order they want search results
 * returned in. Sort orders should be aggregated to Searches (the context).
 *
 * @author Nikolas Tilley
 */
public interface SortOrder
{
    /**
     * Sorts a list of books using a pre-determined method.
     *
     * @param bookList - The list of books to be sorted.
     */
    public void sort(ArrayList<Book> bookList);
}
