package BooksCatalog;

import Books.Book;
import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * the class that handles the GoogleBooks API
 */
public class GoogleBooks implements BookCatalog
{
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * This method overrides the regular book search method and get the books from Google
     * @param title - The title of the book(s) that is being searched for.
     * @param authors - The author of the books(s) that is being searched for.
     * @param isbn - The ISBN of the book(s) being searched for.
     * @param publisher - The publisher of the book(s) that is being searched for.
     * @return
     */
    @Override
    public ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher)
    {
        ArrayList<Book> books = new ArrayList<>();
        String requestString = "";
        if(!title.equals("*"))
        {
            requestString = requestString + "intitle:" + title;
        }
        if(!isbn.equals("*"))
        {
            requestString = requestString + "+isbn:" + isbn;
        }
        if(!publisher.equals("*"))
        {
            requestString = requestString + "+inpublisher:" + publisher;
        }
        if(! authors.contains("*") || ! authors.isEmpty())
        {
            for (String s : authors)
            {
                requestString = requestString + "+inauthor:" + s;
            }
        }
        requestString = "https://www.googleapis.com/books/v1/volumes?q=" + requestString + "&maxResults=40";
        try
        {
            URI uri = new URI(requestString.replace(" ", "%20"));
            HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }

            in.close();
            con.disconnect();
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonBooks = jsonReader.readObject();
            jsonReader.close();
            int tempId = 1;

            for (JsonObject o:jsonBooks.getJsonArray("items").getValuesAs(JsonObject.class))
            {
                if(o.getJsonObject("saleInfo").getJsonString("country").toString().equals("\"US\"")
                        && o.getJsonObject("saleInfo").getJsonString("saleability").toString().equals("\"FOR_SALE\""))
                {
                    String isbnarg = "";
                    String titlearg = "";
                    List<String> authorsarg = new ArrayList<>();
                    String publisherarg = "";
                    String publishDatearg = "";
                    int pageCountarg = 0;
                    //Find JsonObject o isbn
                    for(JsonObject p:o.getJsonObject("volumeInfo").getJsonArray("industryIdentifiers").getValuesAs(JsonObject.class)){
                        if(p.getJsonString("type").toString().equals("\"ISBN_13\""))
                        {
                            isbnarg = p.getJsonString("identifier").toString();
                        }
                    }

                    //Find JsonObject o title
                    try
                    {
                        titlearg = o.getJsonObject("volumeInfo").getJsonString("title").toString();
                        //Find JsonObject o authors
                    }
                    catch (NullPointerException e)
                    {
                        titlearg = o.getJsonObject("volumeInfo").getJsonString("publisher").toString();
                    }
                    try
                    {
                        for (JsonString p : o.getJsonObject("volumeInfo").getJsonArray("authors").getValuesAs(JsonString.class))
                        {
                            authorsarg.add(p.toString());
                        }
                    }
                    catch (NullPointerException e)
                    {
                        authorsarg.add("*");
                    }

                    //Find JsonObject o publisher
                    publisherarg = o.getJsonObject("volumeInfo").getJsonString("publisher").toString();
                    try
                    {
                        //Find JsonObject o publisher date
                        publishDatearg = o.getJsonObject("volumeInfo").getJsonString("publishedDate").toString();
                    }
                    catch (NullPointerException e)
                    {
                        publishDatearg = "10/10/1997";
                    }

                    try
                    {
                        //Find JsonObject o page count
                        pageCountarg = o.getJsonObject("volumeInfo").getJsonNumber("pageCount").intValue();
                    }
                    catch (NullPointerException e)
                    {
                        pageCountarg = 0;
                    }
                    Book abook = new Book(isbnarg, titlearg, authorsarg, publisherarg, publishDatearg, pageCountarg);
                    abook.setTempID(tempId++);
                    System.out.println(abook.toString("sSearch"));
                    books.add(abook);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("System throws exception." + e);
        }
        return books;
    }

    /**
     * Main method for testing
     */
    public static void main(String args[])
    {
        ArrayList<String> authors = new ArrayList<>();
        BookCatalog thing = new GoogleBooks();
        thing.bookSearch("communist", authors, "*", "*");
    }
}