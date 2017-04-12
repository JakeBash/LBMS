package BooksCatalog;

import Books.Book;
import BooksCatalog.BookCatalog;
import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 4/1/17.
 */
public class GoogleBooks implements BookCatalog{
    private final String USER_AGENT = "Mozilla/5.0";
    @Override
    public ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher){
        ArrayList<Book> books = new ArrayList<>();
        String requestString = "";
        if(!title.equals("*")){
            requestString = requestString + "intitle:" + title;
        }
        if(!isbn.equals("*")){
            requestString = requestString + "+isbn:" + isbn;
        }
        if(!publisher.equals("*")){
            requestString = requestString + "+inpublisher:" + publisher;
        }
        if(! authors.contains("*") || ! authors.isEmpty()) {
            for (String s : authors
                    ) {
                requestString = requestString + "+inauthor:" + s;

            }
        }
        requestString = "https://www.googleapis.com/books/v1/volumes?q=" + requestString + "&maxResults=40";
        try {

            URI uri = new URI(requestString.replace(" ", "%20"));
            HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();

            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonBooks = jsonReader.readObject();
            jsonReader.close();


            for (JsonObject o:jsonBooks.getJsonArray("items").getValuesAs(JsonObject.class)) {
                if(o.getJsonObject("saleInfo").getJsonString("country").toString().equals("\"US\"") &&
                        o.getJsonObject("saleInfo").getJsonString("saleability").toString().equals("\"FOR_SALE\"")){
                    String isbnarg = "";
                    String titlearg = "";
                    List<String> authorsarg = new ArrayList<String>();
                    String publisherarg = "";
                    String publishDatearg = "";
                    int pageCountarg = 0;
                    //Find JsonObject o isbn
                    for(JsonObject p:o.getJsonObject("volumeInfo").getJsonArray("industryIdentifiers").getValuesAs(JsonObject.class)){
                        if(p.getJsonString("type").toString().equals("\"ISBN_13\"")){
                            isbnarg = p.getJsonString("identifier").toString();
                        }
                    }

                    //Find JsonObject o title

                    titlearg = o.getJsonObject("volumeInfo").getJsonString("title").toString();
                    //Find JsonObject o authors

                    for(JsonString p: o.getJsonObject("volumeInfo").getJsonArray("authors").getValuesAs(JsonString.class)){
                        authorsarg.add(p.toString());
                    }

                    //Find JsonObject o publisher
                    publisherarg = o.getJsonObject("volumeInfo").getJsonString("publisher").toString();

                    //Find JsonObject o publisher date
                    publishDatearg = o.getJsonObject("volumeInfo").getJsonString("publishedDate").toString();

                    //Find JsonObject o page count
                    pageCountarg = o.getJsonObject("volumeInfo").getJsonNumber("pageCount").intValue(); //TODO Page count apparently not returned on all api calls. Thanks Google.
                    Book abook = new Book(isbnarg, titlearg, authorsarg, publisherarg, publishDatearg, pageCountarg);
                    System.out.println(abook.toString("bSearch"));
                    books.add(abook);
                }
            }
        }
        catch (Exception e){
            System.out.println("System throws exception." + e);

        }
        System.out.println(books);
        return books;
    }


    @Override
    public ArrayList<Book> purchase(int quantity, ArrayList<Integer> ids) {
        return null;
    }




    public static void main(String args[]){
        ArrayList<String> authors = new ArrayList<>();
        BookCatalog thing = new GoogleBooks();
        authors.add("hitler");
        thing.bookSearch("*", authors, "*", "*");


    }

}