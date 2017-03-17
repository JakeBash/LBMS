package Visitors;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * @author Kyler Freas (kmf5285)
 */
public class CheckOut
{
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;

    public CheckOut(Book book, Date borrowDate, Date dueDate, Date returnDate)
    {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
}
