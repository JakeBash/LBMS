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

/**
 * Created by tyler on 4/1/17.
 */
public class GoogleBooks implements BookCatalog{
    private final String USER_AGENT = "Mozilla/5.0";
    @Override
    public ArrayList<Book> bookSearch(String title, ArrayList<String> authors, String isbn, String publisher){

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
                    System.out.println("ok for sell");
                }
            }
        }
        catch (Exception e){
            System.out.println("System throws exception." + e);

        }
        return null;
    }

    @Override
    public ArrayList<Book> getLastSearch() {
        return null;
    }

    @Override
    public ArrayList<Book> purchase(int quantity, ArrayList<Integer> ids) {
        return null;
    }




    public static void main(String args[]){
        ArrayList<String> authors = new ArrayList<>();
        BookCatalog thing = new GoogleBooks();
        authors.add("J. K. Rowling");
        thing.bookSearch("*", authors, "*", "*");


    }

}