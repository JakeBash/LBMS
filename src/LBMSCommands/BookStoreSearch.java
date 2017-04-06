package LBMSCommands;

import java.util.ArrayList;
import Library.Library;

/**
 * Handles the execution of a search of the external library book store catalog.
 *
 * search,title,[{authors},isbn[,publisher[,sort order]]];
 * 
 * @author Nikolas Tilley
 */
public class BookStoreSearch implements LBMSCommand
{
    private Library library;
    // Todo private long clientID;
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private String sortOrder;

    /**
     * Creates a command to search the book store for books to buy. Constructs with the minimum amount of search criteria
     * required.
     * 
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     */
    public BookStoreSearch(Library library, String title)
    {
        this.library = library;
        this.title = title;
        this.authors = new ArrayList<>();
        this.isbn = "*";
        this.publisher = "*";
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the book store for books to buy. Constructs with additional criteria from the
     * previous implementation.
     * 
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     */
    public BookStoreSearch(Library library, String title, ArrayList<String> authors, String isbn)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = "*";
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the book store for books to buy. Constructs with additional criteria from the
     * previous implementation.
     *
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     */
    public BookStoreSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = "none";
    }

    /**
     * Creates a command to search the book store for books to buy. Constructs with additional criteria from the
     * previous implementation.
     * 
     * @param library - The library that is being searched.
     * @param title - The title of the desired book(s).
     * @param authors - The authors of the desired book(s).
     * @param isbn - The ISBN of the desired book(s).
     * @param publisher - The publisher of the desired book(s).
     * @param sortOrder - The sort order to be used when gathering the desired book(s).
     */
    public BookStoreSearch(Library library, String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder)
    {
        this.library = library;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sortOrder = sortOrder;
    }

    /**
     * Executes the BookStoreSearch command on the library.
     */
    public void execute()
    {
        this.library.bookStoreSearch(this.title, this.authors, this.isbn, this.publisher, this.sortOrder);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }






    private void parse(String s)
    {

        ArrayList<String> args = new ArrayList<String>();
        // ArrayList<String> authors = new ArrayList<String>();
        String arg = "";

        boolean parseLiteral = false;

        for(char c : s.toCharArray())
        {
            if ( (c == '{' || c == '\"') && !parseLiteral ) {
                parseLiteral = true;
                continue; // Skip { and "
            }
            else if ( (c == '}' || c == '\"') && parseLiteral ) {
                parseLiteral = false;
                continue; // Skip } and "
            }

            if ((c == ',' || c == ';') && !parseLiteral) {
                args.add(arg);
                arg = "";
            }
            else
                arg += c;
        }

    }

}
