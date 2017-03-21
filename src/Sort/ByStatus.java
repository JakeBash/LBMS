package Sort;

import Books.Book;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Sorts a list of books by the status of available copies. Books with no copies available are not shown.
 *
 * @author Nikolas Tilley
 */
public class ByStatus implements SortOrder
{
    public ByStatus() {}

    /**
     * Sorts a list of books by title.
     *
     * @param bookList - A list of books to be sorted.
     */
    public void sort(ArrayList<Book> bookList)
    {
        bookList.sort(BookStatusComparator);
        for (int i = 0; i < bookList.size(); i++)
        {
            if (bookList.get(i).getAvailableCopies() == 0 )
            {
                bookList.remove(i);
                i--;
            }
        }
    }

    /**
     * Implements a Comparator that compares books by available copies. Sorts the list in ascending order.
     */
    private static Comparator<Book> BookStatusComparator = new Comparator<Book>()
    {
        /**
         * Description
         *
         * @param b1 - A book to compare
         * @param b2 - Another book to compare
         * @return a negative, zero, or a positive integer if b1's available copies
         * is numerically less than, equal to, or greater than b2's available copies
         */
        @Override
        public int compare(Book b1, Book b2)
        {
            int Book1StatusCount = b1.getAvailableCopies();
            int Book2StatusCount = b2.getAvailableCopies();
            return Book2StatusCount - Book1StatusCount;
        }
    };




    // Testing compare methods
    public static void main(String [] args)
    {
        Book b1 = new Book("0123456789","cBook", null, "apub",
                "02-02-2501", 441);
        b1.addCopies(4);
        Book b2 = new Book("1234567890", "aBook", null, "apub",
                "02-02-2501", 441);
        b2.addCopies(1);
        Book b3 = new Book("2345678901", "bBook", null, "apub",
                "02-02-2501", 441);
        b3.addCopies(2);
        Book b4 = new Book("2345678901", "bBook", null, "apub",
                "02-02-2501", 441);
        b3.addCopies(0);
        Book b5 = new Book("2345678901", "bBook", null, "apub",
                "02-02-2501", 441);
        b3.addCopies(0);

        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);

        ByStatus byStatus = new ByStatus();
        byStatus.sort(bookList);

        // Test 1
        if (bookList.get(0).equals(b1) &&
                bookList.get(1).equals(b3) && bookList.get(2).equals(b2))
            System.out.println("Test 1 PASSED");
        else
        {
            System.out.println("Test 1 FAILED");
            System.out.println("Got: " + bookList.get(0).getAvailableCopies() + " " + bookList.get(0).getTitle() +
                    " Expected: " + " " + b1.getAvailableCopies() + " " + b1.getTitle());
            System.out.println("Got: " + bookList.get(1).getAvailableCopies() + " " + bookList.get(1).getTitle() +
                    " Expected: " + " " + b3.getAvailableCopies() + " " + b3.getTitle());
            System.out.println("Got: " + bookList.get(2).getAvailableCopies() + " " + bookList.get(2).getTitle() +
                    " Expected: " + " " + b2.getAvailableCopies() + " " + b2.getTitle());
        }

        // Test 2
        if (bookList.size() == 3)
            System.out.println("Test 2 PASSED");
        else
        {
            System.out.println("Test 2 FAILED");
            System.out.println("Got bookList size: " + bookList.size() + " Expected: 3");
        }
    }
}
