package UIS;

import LBMSCommands.* ;
import Library.TimeClock ;
import Library.Library ;
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
    
    public CommandParser()
    {
        commandQueue = new ArrayList<LBMSCommand>() ;
    }

    public void parseCommand(String s)
    {
        

    }

    /**
     * Depending on the keyword chosen by the user, a command must be created
     * 
     * @param cmd the string that the user uses to identify the Command
     * @param args the string of arguments that are to be used with the command
     * @return command command to be executed
     */
    public LBMSCommand createCommand(String cmd, String args)
    {
        LBMSCommand command ;

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


        return command ;
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

}