package LBMSCommands;

import Books.Book;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Sorts an array of books by publish date. Newly published books
 * are listed before older books.
 *
 * @author Nikolas Tilley
 */
public class ByPublishDate implements SortOrder
{
    public ByPublishDate() {}

    public void sort(ArrayList<Book> bookList)
    {
        bookList.sort(BookDateComparator);
    }


    /**
     * Implements a Comparator that compares books by publish date.
     * Sorts the list in descending order. Newest published books are first.
     *
     */
    private static Comparator<Book> BookDateComparator = new Comparator<Book>()
    {
        /**
         *
         * @param b1 - A book to compare
         * @param b2 - Another book to compare
         * @return a negative, zero, or a positive integer if b1's publish date
         * is alphanumerically less than, equal to, or greater than b2's publish date
         */
        @Override
        public int compare(Book b1, Book b2)
        {
            String BookTitle1 = b1.getPublishDate() ;
            String BookTitle2 = b2.getPublishDate() ;

            return BookTitle2.compareTo(BookTitle1) ;
        }
    } ;


    // Testing compare methods
    public static void main(String [] args)
    {
        Book b1 = new Book("0123456789","cBook", null, "apub",
                "2010-08-19", 441) ;
        Book b2 = new Book("1234567890", "aBook", null, "apub",
                "1991-01-01", 441) ;
        Book b3 = new Book("2345678901", "bBook", null, "apub",
                "2015-12-08", 441) ;
        Book b4 = new Book("2345678901", "bBook", null, "apub",
                "2015-11-08", 441) ;
        Book b5 = new Book("2345678901", "bBook", null, "apub",
                "2015-12-11", 441) ;

        ArrayList<Book> bookList = new ArrayList<Book>() ;
        bookList.add(b1) ;
        bookList.add(b2) ;
        bookList.add(b3) ;
        bookList.add(b4) ;
        bookList.add(b5) ;

        ByPublishDate byPublishDate = new ByPublishDate() ;
        byPublishDate.sort(bookList);


        // Test 1
        if (bookList.get(0).equals(b5) &&
                bookList.get(1).equals(b3) && bookList.get(2).equals(b4) &&
                bookList.get(3).equals(b1) && bookList.get(4).equals(b2))
            System.out.println("Test 1 PASSED") ;
        else
        {
            System.out.println("Test 1 FAILED") ;
            System.out.println("Got: " + bookList.get(0).getPublishDate() +" Expected: " + b5.getPublishDate()) ;
            System.out.println("Got: " + bookList.get(1).getPublishDate() +" Expected: " + b3.getPublishDate()) ;
            System.out.println("Got: " + bookList.get(2).getPublishDate() +" Expected: " + b4.getPublishDate()) ;
            System.out.println("Got: " + bookList.get(3).getPublishDate() +" Expected: " + b1.getPublishDate()) ;
            System.out.println("Got: " + bookList.get(4).getPublishDate() +" Expected: " + b2.getPublishDate()) ;

        }



    }




}
