package LBMSCommands;

import Library.Library;

/**
 * @author Nikolas Tilley
 */
public class BookSearch implements LBMSCommand
{

    private Library library ;
    private String title ;
    private ArrayList<String> authors ;
    private String isbn ;
    private String publisher ;

    // This may not be a String but a SortOrder
    private String sortOrder ;

    // ?????????? we should probably pass in a string of authors and parse by , here to
    // not over complicate the command parser

   /**
    * Creates a command to search the libray with the minimum amount of arguments
    *
    * @param library - the library to Search
    * @param title - the title of the book to Search
    * @param list - of authors of a book
    */
    public BookSearch(Library library, String title, ArrayList<String> authors)
    {
        this.library = library ;
        this.authors = authors ;
        this.isbn = "*" ;
        this.publisher = "*" ;


        // String or SortOrder?
        this.sortOrder = "none" ;

    }

    /**
     *  Narrows the search when provided more arguments
     * 
     * @param isbn - the ISBN of the book
     */
    public BookSearch(Library library, String title, ArrayList<String> authors,
                        String isbn )
    {
        this.library = library ;
        this.authors = authors ;
        this.isbn = isbn ;
        this.publisher = "*" ;


        // String or SortOrder?
        this.sortOrder = "none" ;

    }

    /**
     * Narrows the search when provided more arguments
     * 
     * @param publisher - the publisher of the book
     */
    public BookSearch(Library library, String title, ArrayList<String> authors,
                            String isbn, String publisher)
    {
        this.library = library ;
        this.authors = authors ;
        this.isbn = isbn ;
        this.publisher = publisher ;


        // String or SortOrder?
        this.sortOrder = "none" ;

    }

    /**
     * Sorts the list of books returned in a specified order by
     * title, publish-date, book-status
     * 
     * 
     */
    public BookSearch(Library library, String title, ArrayList<String> authors,
                            String isbn, String publisher, String sortOrder)
    {
        this.library = library ;
        this.authors = authors ;
        this.isbn = isbn ;
        this.publisher = publisher ;


        // String or SortOrder?
        this.sortOrder = sortOrder ;

    }

    public void execute()
    {
        // library.search().... 
        // sort results? or pass in search?
    }
}
