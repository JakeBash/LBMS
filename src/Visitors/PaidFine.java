package Visitors;

import java.util.Calendar;

/**
 * Provides a structure for persisting fines paid by library visitors
 *
 */
public class PaidFine implements java.io.Serializable
{
    private int amount;
    private Calendar datePaid;

    /**
     * Default constructor. Date is initialized to current date.
     *
     * @param amount - amount charged due to the fine
     */
    public PaidFine(int amount, Calendar datePaid)
    {
        this.amount = amount;
        this.datePaid = datePaid;
    }

    /**
     * Getter for fine's paid date
     *
     * @return fine's paid date
     */
    public Calendar getDatePaid()
    {
        return this.datePaid;
    }

    /**
     * Getter for fine's amount
     *
     * @return fine's amount
     */
    public int getAmount()
    {
        return this.amount;
    }
}
