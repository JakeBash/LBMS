package Visitors;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class Book
{
    private String isbn;
    private String title;
    private ArrayList<String> authors;
    private String publisher;
    private Date publishedDate;
    private int pageCount;
    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, ArrayList<String> authors, String publisher,
                Date publishedDate, int pageCount, int totalCopies, int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }
}
