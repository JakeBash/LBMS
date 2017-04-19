package Library;

import Books.BookStorage;
import Visitors.VisitorStorage;
import java.util.ArrayList;

/**
 * An interface that gives state dependent methods a common signature but delegates implementation to the concrete
 * classes.
 *
 * @author Nikolas Tilley
 */
public interface LibraryState
{
    String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage);
    String stateCheckOutBook(ArrayList<String> bkID, Long vID, VisitorStorage visitorStorage, TimeClock timeClock,
                                    BookStorage bookStorage);
}
