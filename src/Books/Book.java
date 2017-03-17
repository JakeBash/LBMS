package Books;

import java.util.ArrayList;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class Book
{
    private int tempID;
    private String isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private String publishDate;
    private int pageCount;
    private int numCopies;
    private int availableCopies;

    public Book(String isbn, String title, ArrayList<String> authors, String publisher, String publishDate, int pageCount)
    {
        tempID = -1;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
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

    public ArrayList<String> getAuthor()
    {
        return authors;
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

    public String toString()
    {
        return this.tempID + "," + this.availableCopies + "," + this.isbn + "," + this.title + "," + authorString() + "," + publisher + "," + publishDate + "," + numCopies;
    }

    public String authorString()
    {
        String authorString = "{";
        for(String a : authors)
        {
            authorString += a;
            if(authors.size() > 1)
                authorString += ",";
        }
        authorString += "}";
        return authorString;
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
