
package Responses;

import LBMSCommands.*;

public class LibrarySuccess {

    private Object obj;

    private LBMSCommand command;

    private String action;

    public LibrarySuccess(cmd,object,act) {
        this.command = cmd;
        this.obj = object;
        this.action = act;

    }

    /**
     * This function returns the response of a successful LBMS command
     * An example is LibrarySuccess(Arrive,Visitor,"arrival")
     * This object will then return "Successful arrival of Visitor"
     * @return String
     */
    public String toString() {
        return "Successful " + this.action + " of " + this.obj.toString();
    }
}