package LBMSCommands;

import Library.Library;

/**
 * This can be used to create a new employee account, or to create an account associated with an existing visitor.
 *
 * client ID,create,username,password,role,visitor ID;
 *
 * @author Nikolas Tilley
 */
public class CreateAccount implements LBMSCommand
{

    private Library library;
    private long clientID;

    private String username;
    private String password;
    private String role; // TODO This could be a string, or a bool "isEmployee"
    private long visitorID;


    /**
     *
     * @param library - The library the CreateAccount command is being executed on.
     * @param clientID - The ID of the client executing the CreateAccount command.
     * @param username - The username of the new account.
     * @param password - The password of the new account.
     * @param role - The role of the new account. Accounts can be employees or visitors.
     * @param visitorID - The ID of the visitor associated with the account to be created.
     */
    public CreateAccount(Library library, long clientID, String username, String password,
                         String role, long visitorID)
    {
        this.library = library;
        this.clientID = clientID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.visitorID = visitorID;
    }


    /**
     * Executes the CreateAccount command on the library.
     */
    @Override
    public void execute() {

    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo() {

    }

}
