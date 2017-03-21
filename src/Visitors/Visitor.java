package Visitors;

import java.util.ArrayList;
import Books.Book;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a visitor to the library. Provides all state associated with visitors including their personal data,
 * checked out books and accumulated fines.
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
    private ArrayList<UnpaidFine> unpaidFines;
    private ArrayList<PaidFine> paidFines;
    private Date registeredDate;
    private int balance;

    //TODO: Return proper responses for all methods

    /**
     * Default constructor. Checked out books and fines are initialized to empty ArrayLists,
     * balance is initialized to 0. Registered date is initialized to nothing since visitor
     * has not yet been registered.
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
        this.unpaidFines = new ArrayList<>();
        this.paidFines = new ArrayList<>();
        this.registeredDate = null;
        this.balance = 0;
    }

    /**
     * Registers the visitor in the library.
     * Sets the visitor's id and registered date.
     *
     * @param id - visitor's assigned id
     * @param registeredDate - date of registration
     */
    public void register(Integer id, Date registeredDate)
    {
        this.id = id;
        this.registeredDate = registeredDate;
    }

    /**
     * Checks out a book for a registered visitor.
     *
     * @param books - A list of books to be checked out.
     */
    public void checkOutBooks(ArrayList<Book> books, Date checkoutDate)
    {
        // Check that this will not exceed max of 5 books
        if (this.checkedOutBooks.size() + books.size() > 5)
        {
            return;
        }

        // Check that visitor does not have outstanding fines
        if (this.balance > 0)
        {
            return;
        }

        // Create the CheckOut objects for each book
        for (Book book: books)
        {
            book.checkout();
            this.checkedOutBooks.add(new CheckOut(book, checkoutDate));
        }
    }

    /**
     * Gets a list of the visitor's currently checked-out books.
     *
     * @return An ArrayList representing the visitor's currently checked-out books.
     */
    public ArrayList<Book> getCheckedOutBooks()
    {
        ArrayList<Book> books = new ArrayList<>();

        // Pull books out of each checkout object
        for (CheckOut checkout: this.checkedOutBooks)
        {
            books.add(checkout.getBook());
        }
        return books;
    }

    /**
     * Gets a list of the visitor's CheckOut objects.
     *
     * @return An ArrayList representing the visitor's CheckOut objects.
     */
    public ArrayList<CheckOut> getCheckOut()
    {
        return this.checkedOutBooks;
    }

    /**
     * Returns the visitor's books to the library. Applies any fines associated with books returned late.
     *
     * @param books - An arrayList representing the books to be returned.
     */
    public void returnBooks(ArrayList<Book> books, Date dateReturned)
    {
        for (Book book: books)
        {
            // Find the checkout object associated with the book.
            ArrayList<Book> checkedOut = this.getCheckedOutBooks();

            // Check that visitor has the book
            if (!checkedOut.contains(book))
            {
                continue;
            }

            CheckOut checkout = this.checkedOutBooks.get(checkedOut.indexOf(book));
            checkout.returnBook(dateReturned);

            // Calculate any fines applied to this book.
            int fineAmount = calculateFine(checkout);

            // Create fine object if necessary
            if (fineAmount > 0)
            {
                this.unpaidFines.add(new UnpaidFine(fineAmount, dateReturned));
                this.balance += fineAmount;
            }

            // Put the copy back in the library
            book.addCopies(1);
        }
    }

    /**
     * Pays a given amount toward the visitor's fine balance
     *
     * @param amount - amount to pay toward fines
     */
    public void payFine(int amount, Date datePaid)
    {
        this.balance -= amount;
        this.paidFines.add(new PaidFine(amount, datePaid));
    }

    /**
     * Calculates the fines applied to a returned book transaction. $10 is added to the fine for 1 day late, and $2 is
     * added for each additional week late. A fine cannot exceed $30.
     *
     * @param checkout - A Checkout object used to calculate fines.
     * @return An integer representing the amount charged to the visitor.
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

    /**
     * Gets a total of fines paid by the visitor within a given number of days in the past
     *
     * @param days - number of days of data to return
     * @return total amount of fines paid by the visitor in the last [days] number of days
     */
    public int getFinesPaid()
    {
        //TODO: Take into account number of days to include in report. Currently returning total since beginning of time
        /*
        // Apply date offset
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        */

        // Calculate total fines paid by visitor in date range
        int totalFines = 0;
        for (PaidFine fine: this.paidFines)
        {
            /*
            if (fine.getDatePaid().after(calendar.getTime()))
            {*/
                totalFines += fine.getAmount();
            //}
        }

        return totalFines;
    }

    /**
     * Gets a total of outstanding fines by the visitor within a given number of days in the past
     *
     * @param days - number of days of data to return
     * @return total amount of fines paid by the visitor in the last [days] number of days
     */
    public int getFinesUnpaid(int days)
    {
        // Apply date offset
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);

        // Calculate total fines paid by visitor in date range
        int totalFines = 0;
        for (UnpaidFine fine: this.unpaidFines)
        {
            if (fine.getDateAccumulated().after(calendar.getTime()))
            {
                totalFines += fine.getAmount();
            }
        }

        return totalFines;
    }

    /**
     * Override equals method to check for duplicate registered visitors
     *
     * @param visitorO - visitor to compare
     * @return whether the visitors are equal
     */
    @Override
    public boolean equals(Object visitorO) {

        // If the visitor is compared with itself then return true
        if (visitorO == this) {
            return true;
        }

        // Check if instance of visitor
        if (!(visitorO instanceof Visitor)) {
            return false;
        }

        // Typecast visitor to Visitor so that we can compare data members
        Visitor visitor = (Visitor) visitorO;

        // Compare first and last names
        return visitor.getFirstName().equals(this.firstName)
                && visitor.getLastName().equals(this.lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter for visitor's id field
     *
     * @return visitor's id field
     */
    public Integer getID()
    {
        return this.id;
    }

    /**
     * Getter for visitor's balance field
     *
     * @return visitor's balance field
     */
    public int getBalance()
    {
        return this.balance;
    }

    /**
     * Getter for visitor's registered date
     *
     * @return visitor's registered date
     */
    public Date getRegisteredDate()
    {
        return this.registeredDate;
    }

    /**
     * Setter for visitor's id field
     *
     * @param id - visitor's new id
     */
    public void setID(Integer id)
    {
        this.id = id;
    }
}
