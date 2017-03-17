package BooksCatalog;

import java.io.File;
import java.util.ArrayList;
import Books.Book;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class CSVParser
{

    private File csv;
    public CSVParser(File csv)
    {
        this.csv = csv;
    }

    public ArrayList<Book> load(){
        //TODO
        ArrayList<Book> books = new ArrayList<Book>();
        return books;


    }
}
