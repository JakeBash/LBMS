package Library;

import Books.Book;
import Books.BookStorage;
import Visitors.Visit;
import Visitors.Visitor;
import Visitors.VisitorStorage;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Implements how state dependent commands are executed when the library
 * is Open
 *
 * @author Nikolas Tilley
 */
public class LibraryOpen implements LibraryState
{
    public LibraryOpen()
    {

    }

    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage)
    {
        String response = "arrive,";

        if( visitorStorage.getVisitor(visitorID) == null )
            return response + "invalid-id;";

        Visit visit = visitorStorage.startVisit(visitorID);

        if (visit != null)
        {
            response += visitorID + "," + visit.getFormattedDate(visit.getStartDateTime()) + "," + visit.getFormattedTime(visit.getStartDateTime()) + ";";
        }
        else
        {
            response += "duplicate;";
        }

        return response;
    }

    public String stateCheckOutBook(ArrayList<String> bkID,Long vID, VisitorStorage visitorStorage, TimeClock timeClock,
    BookStorage bookStorage)
    {
        Visitor currentV = visitorStorage.getVisitor(vID);
        ArrayList<Book> books = new ArrayList<>();

        for(String id : bkID) {
            for (Book bk : bookStorage.getLastSearch()) {
                if (bk.getIsbn().compareTo(id) == 0) {
                    books.add(bk);
                    break;
                }
            }
        }
        currentV.checkOutBooks(books,timeClock.getCalendarDate());

        Calendar c = timeClock.getCalendarDate();
        c.add(Calendar.DAY_OF_WEEK, 7);
        return "borrow," + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH)
                + ";" ;
    }


}
