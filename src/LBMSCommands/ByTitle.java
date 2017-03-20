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

    /**
     * Sorts a list of books by title
     *
     * @param bookList a list of books to be sorted
     */
    public void sort(ArrayList<Book> bookList)
    {
        bookList.sort(BookTitleComparator);
    }

    /**
     * Implements a Comparator that compares books by title.
     * Sorts the list in ascending order. Numbers first, followed
     * by alphabetically
     *
     */
    private static Comparator<Book> BookTitleComparator = new Comparator<Book>()
    {
        /**
         *
         * @param b1 - A book to compare
         * @param b2 - Another book to compare
         * @return a negative, zero, or a positive integer if b1's title
         * is alphanumerically less than, equal to, or greater than b2's title
         */
        @Override
        public int compare(Book b1, Book b2)
        {
            String BookTitle1 = b1.getTitle().toUpperCase() ;
            String BookTitle2 = b2.getTitle().toUpperCase() ;

            return BookTitle1.compareTo(BookTitle2) ;
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

        booklist.sort(BookTitleComparator);

        // Test 1
        if (booklist.get(0).equals(b2) &&
                booklist.get(1).equals(b3) && booklist.get(2).equals(b1))
            System.out.println("Test 1 PASSED") ;
        else
        {
            System.out.println("Test 1 FAILED") ;
            System.out.println("Got: " + booklist.get(0).getTitle() +" Expected: " + b2.getTitle()) ;
            System.out.println("Got: " + booklist.get(1).getTitle() +" Expected: " + b3.getTitle()) ;
            System.out.println("Got: " + booklist.get(2).getTitle() +" Expected: " + b1.getTitle()) ;
        }



    }

}
