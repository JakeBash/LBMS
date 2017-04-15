package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Logs a user in
 *
 * client ID,login,username,password;
 *
 * @author Nikolas Tilley
 * @author Jake Bashaw
 */
public class Login implements LBMSCommand
{
    private LibrarySubject proxy;
    private Long clientID;
    private String username;
    private String password;

    /**
     *
     * @param clientID - The ID of the client logging in.
     * @param username - The username of the user logging in.
     * @param password - The password of the user logging in.
     */
    public Login(LibrarySubject proxy, Long clientID, String username, String password)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.username = username;
        this.password = password;
    }

    /**
     * Executes the Login command on the library.
     */
    @Override
    public void execute()
    {
        this.proxy.login(clientID, username, password);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    @Override
    public void undo()
    {

    }
}
