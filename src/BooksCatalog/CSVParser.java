package BooksCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Books.Book;


/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class CSVParser
{
    /*
     * It loads the file onto its ArrayList or else
     * it gets the FileNotFoundException again.
     */
    public static ArrayList<Book> load(File file) throws FileNotFoundException{
        ArrayList<Book> books = new ArrayList<Book>();
        Scanner s = new Scanner(file);
        Boolean inQuotes = false;
        while(s.hasNext()){
           String line = s.nextLine();
           Book b = new Book();
           for(char ch: line.toCharArray()){


           }
        }

        return books;


    }

    public static void main(String[] args){
        File file = new File("files/books.txt");
        try {
            load(file);
        }
        catch(FileNotFoundException error){
            System.out.println("File not found.");
        }

    }
}


