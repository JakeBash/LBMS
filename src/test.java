import Books.Book;
import Books.BookStorage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tyler on 3/13/2017.
 */
public class test
{
    public static void main(String[] args)
    {
        testAddBooks();
    }

    public static void testAddBooks()
    {
        BookStorage bStore = new BookStorage();
        Book book1 = new Book("1111111111111", "Test Book Title 1", new ArrayList<String>(Arrays.asList("Example Author 1")), "Example Publisher 4", "2017-3-16", 500);
        Book book2 = new Book("2222222222222", "Test Book Title 2", new ArrayList<String>(Arrays.asList("Example Author 2", "Example Author 4")), "Example Publisher 4", "2017-3-16", 100);
        Book book3 = new Book("3333333333333", "Test Book Title 3", new ArrayList<String>(Arrays.asList("Example Author 3")), "Example Publisher 4", "2017-3-16", 250);
        ArrayList<Book> purchase = new ArrayList<>(Arrays.asList(book1, book2, book3));
        bStore.addBooks(purchase, 500);
        for(Book b : bStore.getBooks().values())
        {
            System.out.println(b.toString());
        }
    }
}
