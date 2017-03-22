package UIS;

import LBMSCommands.* ;
import Library.TimeClock ;
import Library.Library ;

import java.util.Arrays;


import java.util.ArrayList ;


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

    private Library library;
    
    public CommandParser(Library lib)
    {
        commandQueue = new ArrayList<LBMSCommand>() ;
        library = lib;

    }

    public void parseCommand(String s)
    {
        ArrayList<Object> args = new ArrayList<Object>(Arrays.asList(s.split(",")));

        /*
         * This code removes the semicolon from the end of the command
         */
        String last = (String)args.get(args.size()-1);
        last = last.substring(0,last.length()-1);
        args.set(args.size()-1,last);
        //end semicolon removal

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
    public void createCommand(String cmd, ArrayList args) {

        LBMSCommand command;

        switch (cmd) {

            case "advance":
                if (args.size() == 1) {
                    command = new AdvanceTime((TimeClock) args.get(0), (int) args.get(1));
                } else if (args.size() == 2) {
                    command = new AdvanceTime((TimeClock) args.get(0), (int) args.get(1), (int) args.get(2));
                }
                else {
                    break;
                }
                this.addCommand(command);
                break;

            case "arrive":
                if (args.size() == 1) {
                    command = new BeginVisit(library,(Integer) args.get(0));
                    this.addCommand(command);
                }
                break;

            case "borrow":
                if(args.size() == 3) {
                    command = new BorrowBook(library, (Integer) args.get(1), (ArrayList<String>) args.get(2));
                    this.addCommand(command);
                }
                break;

            case "borrowed":
                if (args.size() == 1) {
                    command = new FindBorrowed(library, (Integer) args.get(1));
                    this.addCommand(command);
                }
                break;

            case "buy":
                //TODO
                if (args.size() >= 2){
                    command = new PurchaseBook();
                }
                break;

            case "datetime":
                if (args.size() == 0){
                    //command = new GetTime();
                }
                break;

            case "depart":
                if (args.size() == 1){
                    command = new EndVisit(library,(Integer)args.get(0));
                }
                break;

            case "info":
                if (args.size() == 2){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>)args.get(1));
                }
                else if (args.size() == 3){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>) args.get(1),(String)args.get(2));
                }
                else if(args.size() == 4){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>) args.get(1),(String)args.get(2),(String)args.get(3));
                }
                else if(args.size() == 5){
                    command = new BookSearch(library,
                            (String)args.get(0),
                            (ArrayList<String>) args.get(1),
                            (String)args.get(2),
                            (String)args.get(3),
                            (String)args.get(4));
                }

                break;

            case "pay":
                if (args.size() == 1){
                    command = new PayFine();
                }
                break;

            case "register":
                if(args.size() == 4){
                    command = new RegisterVisitor(library,
                            (String)args.get(0),
                            (String)args.get(1),
                            (String)args.get(2),
                            (String)args.get(3));
                    this.addCommand(command);
                }
                break;

            case "report":
                if(args.size() == 1){
                    command = new GenerateReport();
                    this.addCommand(command);
                }
                break;

            case "return":
                if(args.size() == 2){
                    command = new GenerateReport(library,(Integer) args.get(0),(ArrayList<String> args.get(1)));
                    this.addCommand(command);
                }
                break;

            case "search":
                if(args.size() == 1){
                    command = new BookStoreSearch(library,(String) args.get(0));
                    this.addCommand(command);
                }
                else if (args.size() == 3){
                    command = new BookStoreSearch(library,
                            (String) args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String) args.get(2));
                    this.addCommand(command);
                }
                else if (args.size() == 4){
                    command = new BookStoreSearch(library,
                            (String) args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String) args.get(2),
                            (String)args.get(3));
                    this.addCommand(command);
                }
                else if(args.size() == 5){
                    command = new BookStoreSearch(library,
                            (String) args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String) args.get(2),
                            (String)args.get(3),
                            (String)args.get(4));
                    this.addCommand(command);
                }
                break;

            default:
                break;

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

        cp.parseCommand("test,13,hella,14;");
    }
}

