package BooksCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import Books.Book;

/**
 * The CSV Parser handles loading in the books from the books.txt file, into local storage. The stored catalog can later
 * be used to purchase new books for the library.
 *
 * @author Tyler Reimold
 */
public class CSVParser
{
    /**
     * It loads the file onto its ArrayList or else it gets the FileNotFoundException again.
     *
     * @param file - The static file that contains all of the books in the required format.
     * @return An ArrayList representing the now local Book Catalog.
     */
    public static ArrayList<Book> load(File file) throws FileNotFoundException
    {
        ArrayList<Book> books = new ArrayList<>();
        Scanner s = new Scanner(file);
        Boolean inQuotes = false;
        while(s.hasNext())
        {
           String line = s.nextLine();
           String isbn = "";
           String title = "";
           List<String> authors = null;
           String publisher = "";
           String publishDate = "";
           int pageCount = 0;
           String temp = "";
           int step = 0;
           for(char ch: line.toCharArray())
           {
               if(inQuotes)
               {
                   if (ch == '\"' || ch == '{' || ch == '}')
                   {
                       inQuotes = false;
                   }
                   else
                   {
                       temp = temp + ch;
                   }
               }
               else
               {
                   if(ch == '\"' || ch == '{' || ch == '}')
                   {
                       inQuotes = true;
                   }
                   else if(ch == ',')
                   {
                       if(step == 0)
                       {
                           isbn = temp;
                           temp = "";
                           step++;
                       }
                       else if(step == 1)
                       {
                           title = temp;
                           temp = "";
                           step++;
                       }
                       else if(step == 2)
                       {
                           String[] newlist = temp.substring(0,temp.length()).split(",");
                           authors = Arrays.asList(newlist);
                           temp = "";
                           step++;
                       }
                       else if(step == 3)
                       {
                           publisher = temp;
                           temp = "";
                           step++;
                       }
                       else if(step == 4)
                       {
                           publishDate = temp;
                           temp = "";
                           step++;
                       }
                   }
                   else
                   {
                       temp = temp + ch;
                   }
               }
           }
            pageCount = Integer.parseInt(temp);
            books.add(new Book(isbn, title, authors, publisher, publishDate, pageCount));
        }
        return books;
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args)
    {
        File file = new File("files/books.txt");
        try
        {
            load(file);
        }
        catch(FileNotFoundException error)
        {
            System.out.println("File not found.");
        }
    }
}


