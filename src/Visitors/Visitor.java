package Visitors;

import java.util.ArrayList;
import Books.Book;
import java.util.concurrent.TimeUnit;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * @author Kyler Freas
 */
public class Visitor implements java.io.Serializable
{
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Integer id;
    private ArrayList<CheckOut> checkedOutBooks;
    private ArrayList<Fine> fines;
    private int balance;

    /**
     * Default constructor. Checked out books and fines are initialized to empty ArrayLists,
     * balance is initialized to 0.
     *
     * @param firstName - visitor's first name
     * @param lastName - visitor's last name
     * @param address - visitor's home address
     * @param phoneNumber - visitor's phone number
     */
    public Visitor(String firstName, String lastName, String address, String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.checkedOutBooks = new ArrayList<>();
        this.fines = new ArrayList<>();
        this.balance = 0;
    }

    /**
     * Checks a book out for the visitor
     *
     * @param books - a list of books being checked out
     */
    public void checkOutBooks(ArrayList<Book> books)
    {
        //TODO: Figure out where/how to return response

        // Check that this will not exceed max of 5 books
        if (this.checkedOutBooks.size() + books.size() > 5) { return; }

        // Check that visitor does not have outstanding fines
        if (this.balance > 0) { return; }

        // Create the CheckOut objects for each book
        for (Book book: books)
        {
            book.checkout();
            this.checkedOutBooks.add(new CheckOut(book));
        }
    }

    /**
     * Gets a list of the visitor's checked-out books
     *
     * @return ArrayList of visitor's checked-out books
     */
    public ArrayList<Book> getCheckedOutBooks()
    {
        ArrayList<Book> books = new ArrayList<>();

        // Pull books out of each checkout object
        for (CheckOut checkout: this.checkedOutBooks) {
            books.add(checkout.getBook());
        }

        return books;
    }

    /**
     * Returns the visitor's books to the library
     *
     * @param books the books to be returned
     */
    public void returnBooks(ArrayList<Book> books)
    {
        for (Book book: books)
        {
            // Find the checkout object associated with the book.
            ArrayList<Book> checkedOut = this.getCheckedOutBooks();

            // Check that visitor has the book
            if (!checkedOut.contains(book)) { continue; }

            CheckOut checkout = this.checkedOutBooks.get(checkedOut.indexOf(book));
            checkout.returnBook();

            // Calculate any fines applied to this book.
            int fineAmount = calculateFine(checkout);

            // Put the copy back in the library
            book.addCopies(1);
        }
    }

    /**
     * Calculates the fines applied to a returned book transaction.
     * $10 added for 1 day late, $2 added for each additional week late.
     * Cannot exceed $30.
     *
     * @param checkout - checkout object for which to calculate fines
     * @return the amount charged to the visitor
     */
    private int calculateFine(CheckOut checkout)
    {
        int fineAmount = 0;
        long days = checkout.getReturnDate().getTime() - checkout.getDueDate().getTime();
        days = TimeUnit.MILLISECONDS.toDays(days);

        if (days >= 1)
        {
            fineAmount = Integer.min(10 + (int) (2 * (days / 7)), 30);
        }

        return fineAmount;
    }

    public Integer getID()
    {
        return this.id;
    }

    public void setID(Integer id)
    {
        this.id = id;
    }
}
