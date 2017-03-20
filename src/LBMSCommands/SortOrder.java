package LBMSCommands;

import Books.Book;
import java.util.ArrayList;


/**
 * An interface to implement the SortOrder strategy.
 * A user should be able to select the order they want
 * search results returned in.
 *
 * @author Nikolas Tilley
 */
public interface SortOrder
{
    public void sort(ArrayList<Book> bookList) ;

}
