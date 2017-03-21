package UIS;

import UIS.CommandParser;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class handles the input of user commands and represents the "view" of our LBMS application. The accepting and
 * distribution of commands is handled here.
 *
 * @author Kyle Kaniecki
 */
public class PTUI
{
    private CommandParser commandparser;
    private BufferedReader reader;

    /**
     * Description
     */
    public PTUI()
    {
        this.commandparser = new CommandParser();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Description
     *
     * @throws IOException
     */
    public void getCommand() throws IOException
    {
        this.commandparser.parseCommand(reader.readLine());
    }

    /**
     * Description
     *
     * @return
     */
    public String getOutput()
    {
        //TODO
        //ArrayList<> output = this.commandparser.executeAllCommands();
        //return output;
        return "";
    }

    public static void main(String[] args)
    {
        //TODO: Remove later
        PTUI test = new PTUI();
        try
        {
            test.getCommand();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}