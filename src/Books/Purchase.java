package Books;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class handles the instantiation of book purchases made in the LBMS system as an object for later use in a
 * report.
 *
 * @author Jake Bashaw
 */
public class Purchase implements java.io.Serializable
{
    private int quantity;
    private ArrayList<Book> purchasedBooks;
    private Calendar purchaseDate;

    /**
     * Constructor for the creation of a book purchase.
     *
     * @param purchasedBooks - The books that were purchased
     * @param quantity - The amount of copies that were purchased for each book
     * @param purchaseDate - The date on which the books were purchased.
     */
    public Purchase(ArrayList<Book> purchasedBooks, int quantity, Calendar purchaseDate)
    {
        this.purchasedBooks = purchasedBooks;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public ArrayList<Book> getPurchasedBooks()
    {
        return this.purchasedBooks;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public Calendar getPurchaseDate() {
        return this.purchaseDate;
    }
}
