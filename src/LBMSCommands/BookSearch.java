package LBMSCommands;

import Library.Library;
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
    private Library library;
    // Todo private long clientID; // Have to add this information to command
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private String sortOrder;

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
        this.sortOrder = sortOrder;
    }

    /**
     * Executes the BookSearch command on the library.
     */
    public void execute()
    {
        this.library.bookSearch(this.title, this.authors, this.isbn, this.publisher, this.sortOrder);
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

        // Check valid input with try catchs for casting or something
        // depending on length you will convert different
        // Todo delete when done testing
        for(String ss : args)
            System.out.println(ss);

    }





    // Testing Parsing
    public static void main(String [] args)
    {
        Library lib = new Library();
        BookSearch cmd = new BookSearch(lib, "title", new ArrayList<String>());
        cmd.parse("info,\"This is a, comma in a title.\",{author one, author 2, author 3},isbn,publisher,sort order;");
    }
}
