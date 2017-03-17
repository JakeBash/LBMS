package BooksCatalog;
import Books.Book;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public interface BookCatalog
{

    ArrayList<Book> load(File file);

    ArrayList<Book> get(String ... a);


}
