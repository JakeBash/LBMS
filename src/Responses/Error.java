package Responses;

import Responses.Response;

public class LibraryError implements Response{

    private Object obj;

    private LBMSCommand command;

    private String action;


    public LibraryError(cmd,object,act) {
        this.command = cmd;
        this.obj = object;
        this.action = act;
    }

    public toString() {
        return "ERROR: Unsuccessful " + this.action + " of " + this.obj.toString();
    }
}