package Client;

/**
 * @author Nikolas Tilley
 */
public class Client {

    private long clientId;
    // Visitor
    // Command Parser
    // Proxy
    // Library ?

    public Client( long clientID )
    {
        this.clientId = clientID;
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
