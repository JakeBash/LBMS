package UIS;

import UIS.CommandParser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JakeDesktop on 3/13/2017.
 */
public class PTUI
{
    /**
     +     * Command parser
     +     */
    private CommandParser commandparser;

    private BufferedReader reader;

    public PTUI() {

        this.commandparser = new CommandParser();
    this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void getCommand() throws IOException {
        this.commandparser.parseCommand(reader.readLine());
    }

    public String getOutput() {
        //TODO
        //ArrayList<> output = this.commandparser.executeAllCommands();
        //return output;
        return "";
    }
    //TODO
    public static void main(String[] args) {
        PTUI test = new PTUI();
        try {
            test.getCommand();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
