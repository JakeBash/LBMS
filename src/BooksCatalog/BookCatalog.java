package BooksCatalog;

import Books.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Tyler Reimold
 */
public interface BookCatalog
{
    ArrayList<Book> select(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder);
}
