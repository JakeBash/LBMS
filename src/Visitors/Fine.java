package Visitors;
import java.util.Date;

/**
 * Provides a structure for persisting fines accumulated by library visitors
 *
 * @author Kyler Freas
 */
public class Fine
{
    private int amount;
    private Date datePaid;

    /**
     * Description
     *
     * @param amount
     */
    public Fine(int amount)
    {
        this.amount = amount;
        this.datePaid = null;
    }
}
