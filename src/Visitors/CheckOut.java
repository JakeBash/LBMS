package Visitors;

import Books.Book;
import java.util.Date;
import java.util.Calendar;

/**
 * Provides a structure to persist data associated with books checked out
 * by library visitors.
 *
 * @author Kyler Freas
 */
public class CheckOut
{
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;

    /**
     * Default constructor. Requires a book object to link the checkout to.
     * Due date is initialized to 14 days after the date of initialization
     * (i.e. two weeks after checkout).
     *
     * @param book - book to which this checkout is associated
     */
    public CheckOut(Book book, Date borrowDate)
    {
        this.book = book;
        this.borrowDate = borrowDate;

        // Number of days before fines are applied (2 weeks)
        int daysUntilDue = 14;

        // Calculate the due date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysUntilDue);

        this.dueDate = calendar.getTime();
        this.returnDate = null;
    }

    /**
     * Sets the return date of the checkout.
     */
    public void returnBook(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    /**
     * Getter for checked out book
     *
     * @return checked out book
     */
    public Book getBook()
    {
        return this.book;
    }

    /**
     * Getter for the date on which the book(s) were checked out.
     *
     * @return The date on which the book(s) were checked out.
     */
    public Date getBorrowDate()
    {
        return this.borrowDate;
    }

    /**
     * Getter for checked out book's due date
     *
     * @return checked out book's due date
     */
    public Date getDueDate()
    {
        return this.returnDate;
    }

    /**
     * Getter for checked out book's return date
     *
     * @return checked out book's return date
     */
    public Date getReturnDate()
    {
        return this.returnDate;
    }
}
