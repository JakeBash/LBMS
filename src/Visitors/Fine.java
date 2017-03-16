package Visitors;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * Provides a structure for persisting fines accumulated by library visitors
 *
 * @author Kyler Freas (kmf5285)
 */
public class Fine
{
    private int amount;
    private Date datePaid;

    public Fine(int amount, Date datePaid)
    {
        this.amount = amount;
        this.datePaid = datePaid;
    }
}
