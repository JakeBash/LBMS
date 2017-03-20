package UIS;

import LBMSCommands.* ;
import Library.TimeClock ;
import Library.Library ;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap ;

// import java.util.ArrayDeque;
import java.util.ArrayList ;
// import java.util.Queue ;

/**
 * Parses user input and from that, creates commands. These
 * commands are added to a command queue to be executed.
 * 
 * @version 1
 * @author Nikolas Tilley
 */
public class CommandParser
{
    // Queue? a
    private ArrayList<LBMSCommand> commandQueue ;
    // Some kind of reference to our library and time clock or SOEMTHING
    // THIS HAS TO KNOW ABOUT LIBRARY AND CLOCKS AND ETC.

    private HashMap<String,Class<? extends LBMSCommand>> commands;
    
    public CommandParser()
    {
        commandQueue = new ArrayList<LBMSCommand>() ;
        this.commands = new HashMap<>();

        this.commands.put("advance",AdvanceTime.class);
        this.commands.put("arrive",BeginVisit.class);
        this.commands.put("end",EndVisit.class);

    }

    public void parseCommand(String s)
    {
        ArrayList<Object> args = new ArrayList<Object>(Arrays.asList(s.split(",")));

        String cmd = (String) args.remove(0);

        for(int i = 0; i < args.size(); i++) {
            if(!((String)args.get(i)).matches("[A-Za-z]+")) {
                String temp = (String) args.remove(i);
                args.add(i,Integer.parseInt(temp));
            }
        }

        this.createCommand(cmd,args);

    }

    /**
     * Depending on the keyword chosen by the user, a command must be created
     * 
     * @param cmd the string that the user uses to identify the Command
     * @param args the string of arguments that are to be used with the command
     * @return command command to be executed
     */
    public LBMSCommand createCommand(String cmd, ArrayList args)
    {
        //LBMSCommand command = AdvanceTime();
        /*
        if (cmd.equalsIgnoreCase("advance"))
            command = new AdvanceTime() ;   // Needs a timeclock arg, day, hour

        else if (cmd.equalsIgnoreCase("arrive"))
            command = new BeginVisit() ;    // Needs a Library arg, visitorID

        else if (cmd.equalsIgnoreCase("borrow"))
            command = new BorrowBook() ;    // Needs a Library arg

        else if (cmd.equalsIgnoreCase("borrowed"))
            command = new FindBorrowed() ;  // Needs a Library arg

        else if (cmd.equalsIgnoreCase("buy"))
            command = new PurchaseBook() ;  // Needs a Library arg

        else if (cmd.equalsIgnoreCase("datetime"))
            command = new GetTime() ;       // Needs a timeclock arg
        
        else if (cmd.equalsIgnoreCase("depart"))
            command = new EndVisit() ;      // needs a library arg

        // This is going to have a sort order possibly
        else if (cmd.equalsIgnoreCase("info"))
            command = new BookSearch() ;    // needs a library arg

        else if (cmd.equalsIgnoreCase("pay"))
            command = new PayFine() ;       // needs library arg

        else if (cmd.equalsIgnoreCase("register"))
            command = new RegisterVisitor() ;   // needs a library arg

        else if (cmd.equalsIgnoreCase("report"))
            command = new GenerateReport() ;    // needs library arg?

        else if (cmd.equalsIgnoreCase("return"))
            command = new ReturnBook() ;        // needs library arg

        // This is going to have a sort order possibly
        else if (cmd.equalsIgnoreCase("search"))
            command = new BookStoreSearch() ;   // needs library arg

        // Added to make this compile
        else
            return null ;
        */


        return null ;
    }

    public void testhm(String cmd){

        try {
            LBMSCommand cmdd = this.commands.get(cmd).newInstance();
            this.commandQueue.add(cmdd);
        }
        catch(InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }


    }

    /**
     *  @param command the command to be added to the commandQueue
     */
    public void addCommand(LBMSCommand command)
    {
        commandQueue.add(command) ;
    }

    /**
     * Executes a single command on the queue
     */
    public void executeCommand() 
    {
        if (!commandQueue.isEmpty())
        {
            LBMSCommand command = commandQueue.remove(0) ;
            command.execute();

        }
    }

    /**
     * Executes all the commands on the command queue
     */
    public void executeAllCommands()
    {
        while (!commandQueue.isEmpty())
            executeCommand() ;
    }

    public static void main(String[] args) {
        CommandParser cp = new CommandParser();
        Library test = new Library();

        cp.parseCommand("test,13,hella,14");
    }
}

