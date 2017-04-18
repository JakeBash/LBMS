import UIS.GUI;
import UIS.PTUI;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jake Bashaw
 * @author Kyle Kaniecki
 * @author Nikolas Tilley
 */
public class main
{
    /**
     * Main method for entire application. Accepts keyboard input from the user.
     */
    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);

        System.out.println(
                "--------- Welcome to LMBS Release 2 ----------\n" +
                "Some quick things you should know before you start to make\n" +
                "using our implementation easier.\n\n" +
                "1. You should not type in clientIDs, they are automatically assigned\n" +
                "and included in client requests.\n\n" +
                "2. The first thing you must do to interact with the Library is to run\n" +
                "the \"connect;\" command. You are not initially connected to the library.\n\n" +
                "3. After you connect, you will have to log in. The default employee user is\n" +
                        "username: admin\n" +
                        "password: admin\n"
        );

        System.out.println("Enter 1 to launch the GUI\n" +
                            "Enter 2 to launch the PTUI\n" +
                            "Enter 3 to quit");


        System.out.print("Launch option: ");
        String option = in.next();


        if (option.equals("1")) {

            //Create and set up the window.
            JFrame frame = new JFrame("LBMS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Add contents to the window.
            frame.add(new GUI());

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }
        else if (option.equals("2"))
        {
            PTUI client = new PTUI();
            while (true) {
                try {
                    client.getCommand();
                } catch (IOException io) {
                    System.out.println("Incorrect input.");
                }
            }

        }
        else if (option.equals("3"))
        {
            System.exit(0);
        }
    }
}
