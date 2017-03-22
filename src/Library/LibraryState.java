package Library;

import Visitors.VisitorStorage;

/**
 * An interface that gives state dependent methods a common signautre but
 * delegates implementation to the concrete classes
 *
 * @author Nikolas Tilley
 */
public interface LibraryState
{

    public String stateBeginVisit(Long visitorID, VisitorStorage visitorStorage) ;
    public String stateCheckOutBook() ;

}
