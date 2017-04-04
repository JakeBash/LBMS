import UIS.PTUI;
import java.io.IOException;

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
        PTUI client = new PTUI();
        while(true)
        {
            try
            {
                client.getCommand();
            }
            catch (IOException io)
            {
                System.out.println("Incorrect input.");
            }
        }
    }
}
