package BooksCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import Books.Book;

/**
 * Description
 *
 * @author Tyler Reimold
 */
public class FlatFileBookCatalog
{
    private File csv;
    private List<Book> books;

    /**
     *
     */
    public FlatFileBookCatalog(File file)
    {
        this.csv = file;
        try {
            books = CSVParser.load(file);
        }
        catch(FileNotFoundException error){
            System.out.println("File not found");
        }
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
