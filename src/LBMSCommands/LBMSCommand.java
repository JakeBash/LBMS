package LBMSCommands;

/**
 * Interface that gives Commands a uniform execution signature.
 *
 * @author Jake Bashaw
 * @author Nikolas Tilley
 */
public interface LBMSCommand
{
    /**
     * Executes the command on the library.
     */
    void execute();

    void undo();
}