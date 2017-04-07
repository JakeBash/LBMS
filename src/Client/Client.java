package Client;

import Visitors.Visitor;

/**
 * @author Nikolas Tilley
 */
public class Client {

    private long clientId;
    private Visitor visitor;
    // Command Parser no
    // Proxy no
    // Library yee

    public Client( long clientID, Visitor visitor)
    {
        this.clientId = clientID;
        this.visitor = visitor;
    }


    // -------------------- Getters and setters -------------------- //

    public long getClientId()
    {
        return clientId;
    }
    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }
}
