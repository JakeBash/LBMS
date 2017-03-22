package BooksCatalog;

import Books.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Description
 *
 * @author Tyler Reimold
 */
public interface BookCatalog
{
    ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher);

    ArrayList<Book> getLastSearch();
}
