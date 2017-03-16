package Books;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/15/2017.
 */
public class Purchase
{
    private int quantity;
    private ArrayList<Book> purchasedBooks;
    private Date purchaseDate;

    public Purchase(ArrayList<Book> purchasedBooks, int quantity, Date purchaseDate)
    {
        this.purchasedBooks = purchasedBooks;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }
}
