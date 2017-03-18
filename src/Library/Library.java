package Library;
import Visitors.VisitorStorage;
import Visitors.Visitor;

/**
 * Created by JakeDesktop on 3/13/2017.
 *
 * @author Kyler Freas
 */
public class Library
{
    private VisitorStorage visitorStorage;

    public Library()
    {
        // TODO: Add book storage, catalog, purchases, state
        // Initialize visitor storage from file
        this.visitorStorage = VisitorStorage.deserialize();
    }

    // Register a new visitor in the library.
    // Response is the newly registered visitor.
    public Visitor registerVisitor(String firstName, String lastName, String address, String phoneNumber) {
        return this.visitorStorage.registerVisitor(firstName, lastName, address, phoneNumber);
    }

    // Returns a registered visitor with the given ID
    public Visitor getVisitor(Integer visitorID) {
        return this.visitorStorage.getVisitor(visitorID);
    }

    // Begin a visit in the library
    public void beginVisit(Integer visitorID) {
        this.visitorStorage.startVisit(visitorID);
    }

    // Ends a visit
    public void endVisit(Integer visitorID) {
        this.visitorStorage.endVisit(visitorID);
    }

    // Shut down the system, persisting all data created.
    // TODO: Serialize all other entities to be persisted
    public void shutdown() {
        this.visitorStorage.serialize();
    }

    // Generates a statistical report of the library
    // TODO: Add in the rest of the report data needed
    public String generateReport() {
        return this.visitorStorage.generateReport();
    }

    // TODO: Add remaining commands
}
