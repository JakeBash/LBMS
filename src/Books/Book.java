package Books;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class Book
{
    private int tempID;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishDate;
    private int pageCount;
    private int numCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, String publisher, String publishDate, int pageCount)
    {
        tempID = -1;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        // might not need quantity (See addBooks in BookStorage)
        //this.numCopies = quantity;
        //availableCopies = quantity;
    }



    public int getTempID()
    {
        return tempID;
    }

    public void setTempID(int id)
    {
        tempID = id;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public String getPublishDate()
    {
        return publishDate;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getNumCopies()
    {
        return numCopies;
    }

    public int getAvailableCopies()
    {
        return availableCopies;
    }

    public void addCopies(int amt)
    {
        numCopies += amt;
        availableCopies += amt;
    }

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
