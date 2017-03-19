package LBMSCommands;

/**
 * Queries for a list of books currently borrowed by a specific visitor.
 * 
 * command format: borrowed,visitor ID;
 * 
 * @author Nikolas Tilley
 */
public class FindBorrowed implements LBMSCommand
{
    public FindBorrowed()
    {

    }

    // Library.getVisitor(Integer visitorID).getCheckedoutBooks()
    //
    // Library.getCheckedoutBooks(Integer visitorID) -> 
    //   Visitor.getCheckedOutBooks()
    public void execute()
    {

    }
}
