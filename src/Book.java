/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class Book
{
    private int isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishDate;
    private int pageCount;
    private int numCopies;
    private int availableCopies;

    public Book(int isbn, String title, String author, String publisher, String publishDate, int pageCount, int quantity)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        this.numCopies = quantity;
        availableCopies = quantity;
    }

    public int getIsbn()
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
