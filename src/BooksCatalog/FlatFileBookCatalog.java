package BooksCatalog;

import java.io.File;
import java.util.ArrayList;
import Books.Book;

/**
 * Description
 *
 * @author Tyler Reimold
 */
public class FlatFileBookCatalog implements BookCatalog
{
    private File csv;

    /**
     *
     */
    public FlatFileBookCatalog()
    {

    }

    /**
     *
     *
     * @param a
     * @return
     */
    public ArrayList<Book> select(String ... a)
    {
        //TODO
        //ArrayList<Book> books = p.load();
        //return books;
        return null;
    }
}
