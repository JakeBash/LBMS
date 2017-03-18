package Visitors;
import Books.Book;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * @author Kyler Freas
 */
public class CheckOut
{
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;

    // Default constructor
    public CheckOut(Book book)
    {
        this.book = book;
        this.borrowDate = new Date();

        // Number of days before fines are applied (2 weeks)
        int daysUntilDue = 14;

        // Calculate the due date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysUntilDue);

        this.dueDate = calendar.getTime();
        this.returnDate = null;
    }

    // Sets the return date of the checkout
    public void returnBook()
    {
        this.returnDate = new Date();
    }

    public Book getBook()
    {
        return this.book;
    }

    public Date getDueDate()
    {
        return this.returnDate;
    }

    public Date getReturnDate()
    {
        return this.returnDate;
    }
}
