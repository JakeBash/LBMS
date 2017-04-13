package LBMSCommands;

import Library.Library;
import LibraryProtectionProxy.LibrarySubject;

import java.util.ArrayList;

/**
 * Advances the library forward in Time. The number of days can be between 0 and 7, and the number of hours can be
 * between 0 and 23.
 *
 * clientID,advance,number-of-days[,number-of-hours];
 * 
 * @author Nikolas Tilley
 */
public class AdvanceTime implements LBMSCommand
{
    private Library library;

    private LibrarySubject proxy;
    private Long clientID;
    private int days;
    private int hours;

    /**
     * Constructor for AdvanceTime, used when only a day amount is supplied.
     *
     * @param library - The library that the advancement of time is being applied to.
     * @param days - The amount of days that is to be advanced.
     */
    public AdvanceTime(Library library, int days)
    {
        this.library = library;
        this.days = days;
        this.hours = 0;
    }

    /**
     * Constructor for AdvanceTime, used when a day and hour amount are supplied.
     *
     * @param library - The library that the advancement of time is being applied to.
     * @param days - The amount of days that is to be advanced.
     * @param hours - The amount of hours that is to advanced.
     */
    public AdvanceTime(Library library, int days, int hours)
    {
        this.library = library;
        this.days = days;
        this.hours = hours;
    }



    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////

    /**
     * Constructor for AdvanceTime, used when a day and hour amount are supplied.
     *
     * @param proxy - The proxy library that the advancement of time is being applied to.
     * @param clientID - The ID of the Client making the AdvanceTime Command.
     * @param days - The amount of days that is to be advanced.
     *
     */
    public AdvanceTime(LibrarySubject proxy, Long clientID, int days)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.days = days;
        this.hours = 0;
    }

    /**
     * Constructor for AdvanceTime, used when a day and hour amount are supplied.
     *
     * @param proxy - The proxy library that the advancement of time is being applied to.
     * @param clientID - The ID of the Client making the AdvanceTime Command.
     * @param days - The amount of days that is to be advanced.
     * @param hours - The amount of hours that is to advanced.
     */
    public AdvanceTime(LibrarySubject proxy, Long clientID, int days, int hours)
    {
        this.proxy = proxy;
        this.clientID = clientID;
        this.days = days;
        this.hours = hours;
    }

    ////////////////////////////// NEW R2 COMMAND FORMAT //////////////////////////////




    /**
     * Executes the AdvanceTime command on the library.
     */
    public void execute()
    {
        proxy.advanceTime(clientID, days, hours);
    }

    /**
     * If the Command is undoable as per the requirements, then implement behavior to undo
     */
    public void undo()
    {

    }

}
