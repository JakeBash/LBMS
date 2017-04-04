package LibraryProtectionProxy;

import Library.Library;

import java.util.ArrayList;

/**
 *
 *
 * @author Nikolas Tilley
 */
public class LibraryProtectionProxy
{

    private final int LOGGED_OUT = 0;
    private final int VISITOR_LOGGED_IN = 1;
    private final int EMPLOYEE_LOGGED_IN = 2;

    // private visitor/user loggedInVisitor
    private ArrayList<LibraryProtectionProxyState> stateList;
    private LibraryProtectionProxyState state;
    private Library library;

    // Clients either have proxies or a proxy knows about a client
    // Or they need to have a visitor
    public LibraryProtectionProxy( Library library )
    {

        this.library = library;

        stateList = new ArrayList<LibraryProtectionProxyState>();
        stateList.add(new LoggedOutState());
        stateList.add(new VisitorLoggedInState());
        stateList.add(new EmployeeLoggedInState());

        setState(LOGGED_OUT);
    }



    /**
     *
     * @param index the index of the state to change to
     */
    private void setState(int index)
    {
        state = stateList.get(index);
    }




    // TODO implement library interface
    ////////////////////////////////////////////////////////////////


}
