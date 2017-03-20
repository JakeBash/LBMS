package LBMSCommands;

import Books.Book;
import java.util.ArrayList ;
import java.util.Comparator;

/**
 * Sorts a list of books by Title
 *
 * @author Nikolas Tilley
 */
public class ByTitle implements SortOrder
{
    public ByTitle() {}

    public void sort(ArrayList<Book> bookList)
    {
        bookList.sort(BookTitleComparator);
    }


    public static Comparator<Book> BookTitleComparator = new Comparator<Book>()
    {

        public int compare(Book b1, Book b2)
        {
            String BookTitle1 = b1.getTitle().toUpperCase() ;
            String BookTitle2 = b2.getTitle().toUpperCase() ;

            //ascending
            return BookTitle1.compareTo(BookTitle2) ;

            //descending
            //return BookTitle2.compareTo(BookTitle1) ;
        }
    } ;




    // Testing compare methods
    public static void main(String [] args)
    {
        Book b1 = new Book("0123456789","cBook", null, "apub",
                "02-02-2501", 441) ;
        Book b2 = new Book("1234567890", "aBook", null, "apub",
                "02-02-2501", 441) ;
        Book b3 = new Book("2345678901", "bBook", null, "apub",
                "02-02-2501", 441) ;

        ArrayList<Book> booklist = new ArrayList<Book>() ;
        booklist.add(b1) ;
        booklist.add(b2) ;
        booklist.add(b3) ;

        System.out.println("Unsorted: ") ;
        for (Book book : booklist)
            System.out.println(book.getTitle()) ;

        booklist.sort(BookTitleComparator);

        System.out.println("\nSorted by title:") ;
        for (Book book : booklist)
            System.out.println(book.getTitle()) ;


    }

}
