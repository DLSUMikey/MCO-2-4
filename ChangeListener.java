/**
 * The ChangeListener interface is used to observe changes in a state and be notified when the state changes.
 * Classes that implement this interface can register themselves as listeners to be notified whenever a state change occurs.
 */
public interface ChangeListener {
    
    /**
     * This method is called when the state being observed has changed.
     * Implementing classes should define the appropriate actions to be taken in response to the state change.
     */
    void stateChanged();
}
