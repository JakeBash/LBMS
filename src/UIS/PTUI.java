package UIS;

import Library.Library;
import UIS.CommandParser;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class handles the input of user commands and represents the "view" of our LBMS application. The accepting and
 * distribution of commands is handled here.
 *
 * @author Kyle Kaniecki
 */
public class PTUI implements Observer
{
    private CommandParser commandparser;
    private BufferedReader reader;
    private Library lib;

    /**
     * Description
     */
    public PTUI()
    {
        this.commandparser = new CommandParser();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.lib = new Library();
        this.lib.addObserver(this);
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

    @Override
    public void update(Observable observable, Object o) {
        System.out.println(this.lib.getState());

    }
}