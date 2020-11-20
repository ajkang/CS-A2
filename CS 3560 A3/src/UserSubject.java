import java.util.ArrayList;
import java.util.List;

public abstract class UserSubject extends SysEntry{
	private List<Observer> observers;
	
	/**
	 * Constructor that initialized and passed ID into the parent super class 
	 * and initializes the array list 
	 * 
	 * @param user ID 
	 */
	public UserSubject(String ID) {
		super(ID);
		observers = new ArrayList<>();
	}
	
	/**
	 * Updates one passed observer
	 * 
	 * @param user updated and notified 
	 */
	public void notifyObserver(User user) { 
		user.update(this); 
	}
	
	/**
	 * Notifies all observers in user's list 
	 */
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update(this);
		}
	}
	
	/**
	 * Adds passed in observer into the observer array list 
	 * 
	 * @param observer that is added to observers
	 */
	public void attach(Observer observer) { 
		observers.add(observer); 
	}
	
	/**
	 * Removes passed in observer from the array list observers 
	 * 
	 * @param observer passed in is removed from observers 
	 */
	public void detatch(Observer observer) {
		observers.remove(observer); 
	}

}
