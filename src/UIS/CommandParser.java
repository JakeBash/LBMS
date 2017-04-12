package UIS;

import LBMSCommands.*;
import Library.Library;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Parses user input and from that, creates commands. These commands are added to a command queue to be executed.
 *
 * @author Nikolas Tilley
 * @author Kyle Kaniecki
 */
public class CommandParser
{
    private ArrayList<LBMSCommand> commandQueue;
    private Library library;
    private boolean execute;

    /**
     * Creates a new CommandParser to be used with the parsing of user entered commands.
     *
     * @param lib - The library that is being acted upon.
     */
    public CommandParser(Library lib)
    {
        commandQueue = new ArrayList<LBMSCommand>();
        library = lib;
        execute = false;
    }

    /**
     * Parses the command entered by the user.
     *
     * @param s - The command that was entered by the user.
     */
    public void parseCommand(String s)
    {
        ArrayList<Object> args = new ArrayList<>(Arrays.asList(s.split(",")));

        /*
         * This code removes the semicolon from the end of the command
         */
        String last = (String) args.get(args.size()-1);
        if(last.contains(";")){
            last = last.substring(0,last.length()-1);
            args.set(args.size()-1,last);
            this.execute = true;
        } else {
            this.execute = false;
        }

        //end semicolon removal

        ArrayList<String> authors = new ArrayList<>();
        for(int i = 0; i < args.size(); i++)
        {
            if(((String)args.get(i)).contains("{"))
            {
                if(((String)args.get(i)).contains("}"))
                {
                    String first = (String) args.remove(i);
                    authors.add(first.substring(1,first.length()-1));
                    args.add(i,authors);
                    continue;
                }
                String first = (String) args.remove(i);
                authors.add(first.substring(1,first.length()));
                while(!((String)args.get(i)).contains("}"))
                {
                    authors.add((String)args.remove(i));
                }
                first = (String)args.remove(i);
                authors.add(first.substring(0,first.length()-1));
                args.add(i,authors);
            }
        }

        String cmd = (String) args.remove(0);
        this.createCommand(cmd,args);
        if(execute)
        {
            this.executeAllCommands();
        }
    }

    /**
     * Depending on the keyword chosen by the user, a command must be created.
     * 
     * @param cmd - The string that the user uses to identify the Command.
     * @param args - The string of arguments that are to be used with the command.
     *
     */
    public void createCommand(String cmd, ArrayList args)
    {
        LBMSCommand command;
        switch (cmd)
        {
            case "advance":
                if (args.size() == 1)
                {
                    command = new AdvanceTime(library,Integer.parseInt((String)args.get(0)));
                }
                else if (args.size() == 2)
                {
                    command = new AdvanceTime(library, Integer.parseInt((String)args.get(0)), Integer.parseInt((String)args.get(1)));
                }
                else
                {
                    break;
                }
                this.addCommand(command);
                break;

            case "arrive":
                if (args.size() == 1)
                {
                    command = new BeginVisit(library,Long.parseLong((String)args.get(0)));
                    this.addCommand(command);
                }
                break;

            case "borrow":
                if(args.size() == 2) {
                    if( String.class.isInstance(args.get(1))) {
                        String temp = (String) args.remove(1);
                        args.add(1,new ArrayList<String>().add(temp));
                    }
                    command = new BorrowBook(library, Long.parseLong((String)args.get(0)),(ArrayList<String>)args.get(1));
                    this.addCommand(command);
                }
                break;

            case "borrowed":
                if (args.size() == 1)
                {
                    command = new FindBorrowed(library, Long.parseLong((String)args.get(0)));
                    this.addCommand(command);
                }
                break;

            case "buy":
                if (args.size() >= 2){
                    ArrayList<Integer> ids = new ArrayList<>();
                    for(int i = 1; i < args.size(); i++){
                        ids.add(Integer.parseInt((String)args.get(i)));
                    }
                    command = new PurchaseBook(library,Integer.parseInt((String)args.get(0)),ids);
                    this.addCommand(command);
                }
                break;

            case "datetime":
                if (args.size() == 0)
                {
                    command = new GetTime(library);
                    this.addCommand(command);
                }
                break;

            case "depart":
                if (args.size() == 1)
                {
                    command = new EndVisit(library,Long.parseLong((String)args.get(0)));
                    this.addCommand(command);
                }
                break;

            case "info":
                if (args.size() == 2){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>)args.get(1));
                    this.addCommand(command);
                }
                else if (args.size() == 3){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>) args.get(1),(String)args.get(2));
                    this.addCommand(command);
                }
                else if(args.size() == 4){
                    command = new BookSearch(library,(String)args.get(0),(ArrayList<String>) args.get(1),(String)args.get(2),(String)args.get(3));
                    this.addCommand(command);
                }
                else if(args.size() == 5){
                    command = new BookSearch(library,
                            (String)args.get(0),
                            (ArrayList<String>) args.get(1),
                            (String)args.get(2),
                            (String)args.get(3),
                            (String)args.get(4));
                    this.addCommand(command);
                }
                break;

            case "pay":
                if (args.size() == 1)
                {
                    command = new PayFine(library, Long.parseLong((String)args.get(0)), Integer.parseInt((String)args.get(1)));
                    this.addCommand(command);
                }
                break;

            case "register":
                if(args.size() == 4)
                {
                    command = new RegisterVisitor(library,
                            (String)args.get(0),
                            (String)args.get(1),
                            (String)args.get(2),
                            (String)args.get(3));
                    this.addCommand(command);
                }
                break;

            case "report":
                if(args.size() == 0)
                {
                    command = new GenerateReport(library);
                    this.addCommand(command);
                }
                break;

            case "return":
                if(args.size() >= 2)
                {
                    ArrayList<String> ids = new ArrayList<>();
                    for(int i = 1; i < args.size(); i++){
                        ids.add((String)args.get(i));
                    }
                    command = new ReturnBook(library,Long.parseLong((String)args.get(0)),ids);
                    this.addCommand(command);
                }
                break;

            case "search":
                if(args.size() == 1)
                {
                    command = new BookStoreSearch(library,(String) args.get(0));
                    this.addCommand(command);
                }
                else if (args.size() == 3)
                {
                    command = new BookStoreSearch(library,
                            (String)args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String)args.get(2));
                    this.addCommand(command);
                }
                else if (args.size() == 4)
                {
                    command = new BookStoreSearch(library,
                            (String) args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String) args.get(2),
                            (String)args.get(3));
                    this.addCommand(command);
                }
                else if(args.size() == 5)
                {
                    command = new BookStoreSearch(library,
                            (String) args.get(0),
                            (ArrayList<String>)args.get(1),
                            (String) args.get(2),
                            (String)args.get(3),
                            (String)args.get(4));
                    this.addCommand(command);
                }
                break;

            case "shutdown":
                library.shutdown();
                System.exit(0);
                break;

            default:
                break;
        }
    }

    /**
     * Adds a command to the command queue.
     *
     *  @param command - The command to be added to the commandQueue.
     */
    public void addCommand(LBMSCommand command)
    {
        commandQueue.add(command);
    }

    /**
     * Executes a single command on the queue.
     */
    public void executeCommand() 
    {
        if (!commandQueue.isEmpty())
        {
            LBMSCommand command = commandQueue.remove(0);
            command.execute();
        }
    }

    /**
     * Executes all the commands on the command queue.
     */
    public void executeAllCommands()
    {
        while (!commandQueue.isEmpty())
            executeCommand();
    }
}
