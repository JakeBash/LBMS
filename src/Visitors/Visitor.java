package Visitors;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * @author Kyler Freas (kmf5285)
 */
public class Visitor implements java.io.Serializable
{
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Integer id;
    private ArrayList<CheckOut> checkedOutBooks;
    private ArrayList<Fine> fines;
    private int balance;

    // Default constructor
    public Visitor(String firstName, String lastName, String address, String phoneNumber,
            ArrayList<CheckOut> checkedOutBooks, ArrayList<Fine> fines, int balance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        //this.id = UUID.randomUUID().toString();
        this.checkedOutBooks = checkedOutBooks;
        this.fines = fines;
        this.balance = balance;
    }

    public Integer getID() {
        return this.id;
    }

    public void setID(Integer id) {
        this.id = id;
    }
}
