import UIS.PTUI;
import java.io.IOException;

/**
 *
 */
public class main
{
    /**
     *
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
