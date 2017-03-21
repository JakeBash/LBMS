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

    public void stateBeginVisit(Integer visitorID, VisitorStorage visitorStorage) ;
    public void stateCheckOutBook() ;

}
