package Books;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all of the responsibilities of a single book in the library. Action such as adding more copies of
 * a book to the system or converting a book object into a output ready string are handled in this class.
 *
 * @author Jake Bashaw
 */
public class Book implements java.io.Serializable
{
    private int tempID;
    private String isbn;
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishDate;
    private int pageCount;
    private int numCopies;
    private int availableCopies;

    /**
     * Constructor for creating a new Book object.
     *
     * @param isbn - The ISBN for the given book.
     * @param title - The title for the given book.
     * @param authors - The author(s) for the given book.
     * @param publisher - The published of the given book.
     * @param publishDate - The publish date for the given book.
     * @param pageCount - The page count for the given book.
     */
    public Book(String isbn, String title, List<String> authors, String publisher, String publishDate, int pageCount)
    {

        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
    }

    /**
     * Simple getter method for retrieving the temporary id of a given book.
     *
     * @return The temporary id of the given book.
     */
    public int getTempID()
    {
        return this.tempID;
    }

    /**
     * Sets a book's temporary id for use in search commands.
     *
     * @param id - The new temporary id for the given book.
     */
    public void setTempID(int id)
    {
        this.tempID = id;
    }

    /**
     * Simple getter method for retrieving the ISBN of a book.
     *
     * @return An String representing the ISBN of the given book.
     */
    public String getIsbn()
    {
        return isbn;
    }

    /**
     * Simple getter method for retrieving the title of a book.
     *
     * @return An String representing the title of the given book.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Simple getter method for retrieving the author of a book.
     *
     * @return An String representing the author of the given book.
     */
    public List<String> getAuthor()
    {
        return authors;
    }

    /**
     * Simple getter method for retrieving the publisher of a book.
     *
     * @return An String representing the publisher of the given book.
     */
    public String getPublisher()
    {
        return publisher;
    }

    /**
     * Simple getter method for retrieving the publish date of a book.
     *
     * @return An String representing the publish date of the given book.
     */
    public String getPublishDate()
    {
        return publishDate;
    }

    /**
     * Simple getter method for retrieving the page count of a book.
     *
     * @return An integer representing the page count of the given book.
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * Simple getter method for retrieving the number of owned copies of a book.
     *
     * @return An integer representing the number of owned copies of the given book.
     */
    public int getNumCopies()
    {
        return numCopies;
    }

    /**
     * Simple getter method for retrieving the number available copies of a book.
     *
     * @return An integer representing the number of available copies of the given book.
     */
    public int getAvailableCopies()
    {
        return availableCopies;
    }

    /**
     * Takes the information of a given book object and turns it into an output-ready format.
     *
     * @param comm - A string representing the command invoking the toString() call.
     * @return An output-ready string representation of the given book object.
     */
    public String toString(String comm)
    {
        if(comm.equals("bSearch"))
            return this.availableCopies + "," + this.isbn + ",\"" + this.title + "\"," + authorString() + "," + this.publisher + "," + this.publishDate + "," + this.pageCount;
        else if(comm.equals("fBorrow"))
            return this.tempID + "," + this.isbn + ",\"" + this.title + ",";
        else if(comm.equals("sSearch"))
            return this.tempID + "," + this.isbn + ",\"" + this.title + "\"," + authorString() + "," + this.publishDate;
        else if(comm.equals("bPurchase"))
            return this.isbn + ",\"" +this.title + "\"," + authorString() + "," + this.publishDate + ",";
        else
            return "Invalid toString() identifier.";
    }

    /**
     * Helper method used to format the authors of a book, as detailed by the project requirements.
     *
     * @return The formatted string of the author(s) for a given book
     */
    public String authorString()
    {
        int i = 0;
        String authorString = "{";
        for(String a : authors)
        {
            if(authors.size() > 1 && i > 0)
                authorString += ",";
            authorString += a;
            i++;
        }
        authorString += "}";
        return authorString;
    }

    /**
     * Adds the purchased amount of the given book to the variables keeping track of available and total book copies.
     *
     * @param amt - The amount of book copies that were purchased.
     */
    public void addCopies(int amt)
    {
        numCopies += amt;
        availableCopies += amt;
    }

    /**
     * Decrements the amount of available book copies when a visitor checks out a copy of the given book.
     *
     * @return Returns true if there are available copies, false otherwise.
     */
    public boolean checkout()
    {
        if(availableCopies > 0)
        {
            availableCopies--;
            return true;
        }
        else
            return false;
    }
}
