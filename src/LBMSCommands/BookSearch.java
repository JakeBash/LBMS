package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Handles the execution of the book search from the library's internal storage.
 *
 * Signature - clientID,info,title,{authors},[isbn, [publisher,[sort order]]];
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class BookSearch implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private String sortOrder;


    /**
     * Creates a command to search the library for book(s) with the minimum amount of criteria required.
     *
     * @param proxy - The proxy library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     */
    public BookSearch(LibrarySubject proxy, Long clientID, String title, ArrayList<String> authors)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.title = title;
        this.authors = authors;
        this.isbn = "*";
        this.publisher = "*";
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     *
     * @param proxy - The proxy library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     */
    public BookSearch(LibrarySubject proxy, Long clientID, String title, ArrayList<String> authors, String isbn)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = "*";
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     *
     * @param proxy - The proxy library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     */
    public BookSearch(LibrarySubject proxy, Long clientID, String title, ArrayList<String> authors, String isbn, String publisher)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the library for book(s). Constructs with additional criteria from the previous
     * implementation.
     *
     * @param proxy - The proxy library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     * @param sortOrder - The sort order to be used when gathering the desired book(s).
     */
    public BookSearch(LibrarySubject proxy, Long clientID, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = sortOrder;
    }


    /**
     * Executes the BookSearch command on the library.
     */
    public void execute()
    {
        this.proxy.bookSearch(this.clientID, this.title, this.authors, this.isbn, this.publisher, this.sortOrder);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }

}
