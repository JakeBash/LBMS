package BooksCatalog;
import java.io.File;
import java.util.ArrayList;
import Books.Book;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class FlatFileBookCatalog implements BookCatalog
{

    private File csv;
    public FlatFileBookCatalog()
    {

    }

    public ArrayList<Book> select(String ... a){
        //TODO
        CSVParser p = new CSVParser(csv);
        ArrayList<Book> books = p.load();
        return books;

    }




}
