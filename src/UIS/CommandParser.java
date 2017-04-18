package UIS;

import LBMSCommands.*;
import Library.Library;
import LibraryProtectionProxy.LibrarySubject;


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
    private LibrarySubject proxy;
    private boolean execute;

    /**
     * Creates a new CommandParser to be used with the parsing of user entered commands.
     *
     * @param proxy - The proxy library that is being acted upon.
     */
    public CommandParser(LibrarySubject proxy)
    {
        commandQueue = new ArrayList<LBMSCommand>();
        this.proxy = proxy;
        execute = false;
    }

    /**
     * Parses the command entered by the user.
     *
     * @param s - The command that was entered by the user.
     */
    public void parseCommand(String s) {

        ArrayList<String> args = new ArrayList<String>();
        String arg = "";

        boolean parseLiteral = false;
        for (char c : s.toCharArray()) {
            if ((c == '{' || c == '\"') && !parseLiteral) {
                parseLiteral = true;
                continue; // Skip { and "
            } else if ((c == '}' || c == '\"') && parseLiteral) {
                parseLiteral = false;
                continue; // Skip } and "
            }

            if (c == ',' && !parseLiteral) {
                args.add(arg);
                arg = "";
            } else if (c == ';' && !parseLiteral) {
                args.add(arg);
                arg = "";
                break;
            } else
                arg += c;
        }

        // The client technically will not be able to enter anything less than length 1... or 2 if we have a dropdown box for commands
        if (args.size() >= 2)
        {
            String cmd = args.get(1);
            this.createCommand(cmd,args);
            if (s.endsWith(";"))
                this.executeAllCommands();
            else
                proxy.forwardResponse( Long.parseLong(args.get(0)), args.get(0) + ",partial-request;");//Partial Request Error
        }
        else {
            // todo this should only go to the client that is using the cmd parser
            proxy.forwardResponse(Long.parseLong(args.get(0)), "Invalid argument length;");
        }



    }

    /**
     * Depending on the keyword chosen by the user, a command must be created.
     *
     * @param cmd - The string that the user uses to identify the Command.
     * @param args - The string of arguments that are to be used with the command.
     *
     */
    public void createCommand(String cmd, ArrayList<String> args)
    {
        LBMSCommand command;
        switch (cmd)
        {
            case "advance":
                if (args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    int days = Integer.parseInt(args.get(2));
                    command = new AdvanceTime(proxy, clientID, days);
                }
                else if (args.size() == 4)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    int days = Integer.parseInt(args.get(2));
                    int hours = Integer.parseInt(args.get(3));
                    command = new AdvanceTime(proxy, clientID, days, hours);
                }
                else
                {
                    break;
                }
                this.addCommand(command);
                break;

            case "arrive":
                if (args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    command = new BeginVisit(proxy, clientID, visitorID);
                    this.addCommand(command);
                }
                break;

            case "borrow":
                if(args.size() == 4) {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    ArrayList<String> bookIDs = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    command = new BorrowBook(proxy, clientID, visitorID, bookIDs);
                    this.addCommand(command);
                }
                break;

            case "borrowed":
                if (args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    command = new FindBorrowed(proxy, clientID, visitorID);
                    this.addCommand(command);
                }
                break;

            case "buy":
                if (args.size() >= 4){

                    Long clientID = Long.parseLong(args.get(0));
                    int amount = Integer.parseInt(args.get(2));
                    ArrayList<Integer> ids = new ArrayList<Integer>();

                    for(int i = 3; i < args.size(); i++){
                        ids.add(Integer.parseInt(args.get(i)));
                    }
                    command = new PurchaseBook(proxy, clientID, amount, ids);
                    this.addCommand(command);
                }
                break;

            case "connect":
                if (args.size() == 2){
                    Long clientID = Long.parseLong(args.get(0));
                    command = new Connect(proxy, clientID);
                    this.addCommand(command);
                }
                break;

            case "create":
                if (args.size() == 6){
                    Long clientID = Long.parseLong(args.get(0));
                    String username = args.get(2);
                    String password = args.get(3);
                    String role = args.get(4);
                    Long visitorID = Long.parseLong(args.get(5));

                    command = new CreateAccount(proxy, clientID, username, password, role, visitorID);

                }
                break;

            case "datetime":
                    if (args.size() == 2) {
                        Long clientID = Long.parseLong(args.get(0));
                        command = new GetTime(proxy, clientID);
                        this.addCommand(command);
                    }
                break;

            case "depart":
                if (args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    command = new EndVisit(proxy, clientID, visitorID);

                    this.addCommand(command);
                }
                break;

            case "disconnect":
                if (args.size() == 2) {
                    Long clientID = Long.parseLong(args.get(0));
                    command = new Disconnect(proxy, clientID);
                    this.addCommand(command);
                }
                break;

            case "info":
                if (args.size() == 4){
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    command = new BookSearch(proxy, clientID, title, authors);
                    this.addCommand(command);
                }
                else if (args.size() == 5){
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    command = new BookSearch(proxy, clientID, title, authors, isbn);

                    this.addCommand(command);
                }
                else if(args.size() == 6){
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    String publisher = args.get(5);
                    command = new BookSearch(proxy, clientID, title, authors, isbn, publisher);

                    this.addCommand(command);
                }
                else if(args.size() == 7){
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    String publisher = args.get(5);
                    String sortOrder = args.get(6);
                    command = new BookSearch(proxy, clientID, title, authors, isbn, publisher, sortOrder);

                    this.addCommand(command);
                }
                break;

            case "login":
                if (args.size() == 4) {
                    Long clientID = Long.parseLong(args.get(0));
                    String username = args.get(2);
                    String password = args.get(3);

                    command = new Login(proxy, clientID, username, password);
                    this.addCommand(command);
                }
                break;

            case "logout":
                if (args.size() == 2) {
                    Long clientID = Long.parseLong(args.get(0));
                    command = new Logout(proxy, clientID);
                    this.addCommand(command);
                }
                break;

            case "pay":
                if (args.size() == 4)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    int amount = Integer.parseInt(args.get(3));
                    command = new PayFine(proxy, clientID, visitorID, amount);
                    this.addCommand(command);
                }
                break;

            case "register":
                if(args.size() == 6)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    String firstName = args.get(2);
                    String lastName = args.get(3);
                    String address = args.get(4);
                    String phoneNumber = args.get(5);

                    command = new RegisterVisitor(proxy, clientID, firstName, lastName, address, phoneNumber);

                    this.addCommand(command);
                }
                break;

            case "report":
                if(args.size() == 2)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    command = new GenerateReport(proxy, clientID);
                    this.addCommand(command);
                }
                else if(args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    int days = Integer.parseInt(args.get(2));
                    command = new GenerateReport(proxy, clientID, days);
                    this.addCommand(command);
                }
                break;

            case "return":
                if(args.size() >= 4)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    Long visitorID = Long.parseLong(args.get(2));
                    ArrayList<String> ids = new ArrayList<String>();

                    for(int i = 3; i < args.size(); i++){
                        ids.add(args.get(i));
                    }

                    command = new ReturnBook(proxy, clientID,visitorID, ids);
                    this.addCommand(command);
                }
                break;

            case "search":

                if(args.size() == 3)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    command = new BookStoreSearch(proxy, clientID, title);
                    this.addCommand(command);
                }
                else if (args.size() == 5)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    command = new BookStoreSearch(proxy, clientID, title, authors, isbn);
                    this.addCommand(command);
                }
                else if (args.size() == 6)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    String publisher = args.get(5);
                    command = new BookStoreSearch(proxy, clientID, title, authors, isbn, publisher);
                    this.addCommand(command);
                }
                else if(args.size() == 7)
                {
                    Long clientID = Long.parseLong(args.get(0));
                    String title = args.get(2);
                    ArrayList<String> authors = new ArrayList<String>(Arrays.asList(args.get(3).split(",")));
                    String isbn = args.get(4);
                    String publisher = args.get(5);
                    String sortOrder = args.get(6);
                    command = new BookStoreSearch(proxy, clientID, title, authors, isbn, publisher, sortOrder);

                    this.addCommand(command);
                }
                break;

            case "shutdown":
                if (args.size() == 2) {
                    Long clientID = Long.parseLong(args.get(0));

                    command = new Shutdown(proxy, clientID);
                    this.addCommand(command);
                }

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




