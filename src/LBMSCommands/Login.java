package LBMSCommands;

import Library.Library;

import java.util.ArrayList;

/**
 * Logs a user in
 *
 * client ID,login,username,password;
 *
 * @author Nikolas Tilley
 */
public class Login implements LBMSCommand
{
    private Library library;
    private long clientID;
    private String username;
    private String password;

    /**
     *
     * @param library - The library the Login command is being executed on.
     * @param clientID - The ID of the client logging in.
     * @param username - The username of the user logging in.
     * @param password - The password of the user logging in.
     */
    public Login(Library library, long clientID, String username, String password)
    {
        this.library = library;
        this.clientID = clientID;
        this.username = username;
        this.password = password;
    }

    /**
     * Executes the Login command on the library.
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







    private void parse(String s)
    {

        ArrayList<String> args = new ArrayList<String>();

        String arg = "";


        for(char c : s.toCharArray())
        {

            if (c == ',' || c == ';') {
                args.add(arg);
                arg = "";
            }
            else
                arg += c;
        }

    }

}