package LBMSCommands;

import Library.Library;
import java.util.ArrayList;

/**
 * Handles the execution of the book search from the library's internal storage.
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class BookSearch implements LBMSCommand
{
    private Library library;
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    // This may not be a String but a SortOrder
    private String sortOrder;
    // ?????????? we should probably pass in a string of authors and parse by , here to
    // not over complicate the command parser

   /**
    * Creates a command to search the library for book(s) with the minimum amount of criteria required.
    *
    * @param library - The library that is being searched.
    * @param title - The title of the desired book(s).
    * @param authors - The authors of the desired book(s).
    */
    public BookSearch(Library library, String title, ArrayList<String> authors)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = "*";
        this.publisher = "*";
        // String or SortOrder?
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     *
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = "*";
        // String or SortOrder?
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     *
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        // String or SortOrder?
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     * 
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     * @param sortOrder - The sort order to be used when gathering the desired book(s).
     */
    public BookSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        // String or SortOrder?
        this.sortOrder = sortOrder;
    }

    /**
     * Executes the BookSearch command on the library
     */
    public void execute()
    {
        this.library.bookSearch(this.title, this.authors, this.isbn, this.publisher, this.sortOrder);
        // sort results? or pass in search?
    }
}
