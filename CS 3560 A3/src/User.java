import java.util.ArrayList;
import java.util.List;

public class User extends UserSubject implements Observer {	
	private UserProfileGUI userProfile;
	private List<String> followersList;
	private List<String> followingList;
	private List<String> ownTweets;
	private List<String> newsFeed;
	private String mostRecentMessage;
	private int tweetTotal;
	private UserGroup parentGroup;
	
	/**
	 * Overloading in order to satisfy and set each constraint within this class 
	 */
	public User(String ID) {
		super(ID);
		followersList = new ArrayList<>();
		followingList = new ArrayList<>();
		newsFeed = new ArrayList<>();
		ownTweets = new ArrayList<>();
		tweetTotal = 0;
	}
	
	public User(String ID, String parentID) {
		super(ID);
		this.parentGroup = Admin.getGroupID(parentID);
		followersList = new ArrayList<>();
		followingList = new ArrayList<>();
		newsFeed = new ArrayList<>();
		ownTweets = new ArrayList<>();
		tweetTotal = 0;
	}

	public User(String ID, UserGroup parentGroup) {
		super(ID);
		this.parentGroup = parentGroup;
		followersList = new ArrayList<>();
		followingList = new ArrayList<>();
		newsFeed = new ArrayList<>();
		ownTweets = new ArrayList<>();
		tweetTotal = 0;
	}
	
	/**
	 * The user object is created using the user ID passed 
	 * Use becomes an observer to be considered following user
	 * Only updates list of users
	 * 
	 * @param user ID to follow
	 */
	public void followUser(String userID) {
		UserSubject user = Admin.getUserID(userID);
		//Since the User class is also an observer, we can attach *this* to the user's observers
		user.attach(this);
		//Add the users ID to our followingList list
		followingList.add(userID);
		mostRecentMessage = null;
		//Notify the specific observer. Since mostRecentMessage is null, it will only update its followersList list
		notifyObserver((User)user);
		//When following a user, the following view needs to be updated
		followingList.add(userID);
		
	}
	
	/**
	 * Get the message and create a string with the given tweet
	 * Add the tweet to the news newsFeed and increase the tweet total
	 * Update the message as the newsts and do this for all other followersList
	 * 
	 * @param message that is displayed in the GUI / news newsFeed 
	 */
	public void postTweet(String msg) {
		String tweetString = String.format("%s: %s", this.getID(), msg);
		newsFeed.add(tweetString);
		ownTweets.add(msg);
		tweetTotal++;
		this.mostRecentMessage = tweetString;
		notifyObservers();
	}

	/**
	 * Update is used to find if that user needs to update a view on 
	 * someone who is following the user
	 * 
	 * @param updates the newsFeed based on suject's ID
	 */
	public void update(UserSubject us) {
		String subjectID = us.getID();
		
		if(!followersList.contains(subjectID)) {
			followersList.add(subjectID);
		}
		String newestMessage = ((User)us).getMostRecentMessage();
		if(newestMessage != null) {
			newsFeed.add(newestMessage);
			userProfile.updateNewsFeed(newestMessage); 
		}
	}
	
	/**
	 * Seting parent group in this class
	 * 
	 * @param parentGroup for user
	 */
	public void setParent(UserGroup parentGroup) { 
		this.parentGroup = parentGroup; 
	}
	
	/**
	 * Gets the user parent group.
	 * 
	 * @return the parent UserGroup
	 */
	public UserGroup getParentGroup() {
		return this.parentGroup; 
	}
	
	/**
	 * Setting up userProfile for the GUI 
	 * 
	 * @param profile is set in this class
	 */
	public void setUserProfile(UserProfileGUI userProfile) { 
		this.userProfile = userProfile; 
	}
	
	/**
	 * Gets the latest messages from this user
	 * 
	 * @return last message from user
	 */
	public String getMostRecentMessage() { 
		return this.mostRecentMessage; 
	}
	
	/**
	 * Get a list of this users tweets 
	 * Used for finding the total amount of messages
	 * 
	 * @return list of tweets
	 */
	public List<String> getOwnTweets(){ 
		return this.ownTweets; 
	}
	
	/**
	 * Gets message total from user
	 * 
	 * @return total tweets
	 */
	public int getTweetTotal() { 
		return this.tweetTotal; 
	}
	
	/**
	 * Gets the User's news newsFeed list
	 * 
	 * @return news newsFeed list of user 
	 */
	public List<String> getNewsFeed() { 
		return this.newsFeed; 
	}
	
	/**
	 * Gets user list of followingList
	 * 
	 * @return lists of followersList of this user
	 */
	public List<String> getFollowingsList(){ 
		return this.followingList; 
	}
	
	/**
	 * Checks if user is following another user of a given ID 
	 * 
	 * @param ID of user
	 * @return if user is following ID (true or false)
	 */
	public boolean isFollowing(String ID) { 
		return followingList.contains(ID); 
	}
}
