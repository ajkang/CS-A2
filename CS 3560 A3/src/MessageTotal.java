import java.util.List;
/**
 * SysEntryVisitor visitor sub classes 
 * */
public class MessageTotal implements SysEntryVisitor{
	/**
	 * Uses the countTotalMessages method in order get/return the total messages 
	 */
	@Override
	public int visit(SysEntry item) {
		int totalMessages = countTotalMessages(item);
		Admin.setVisitorOutput(totalMessages);
		return totalMessages;
	}
	
	
	/**
	 * Checks each of the items to get all of the messages within the tree
	 * Checks if the user has messages and increments the counter
	 * If the item is a User --> get all the messages
	 * If the item is a UserGroup --> recursively search to find a message 
	 * Used in the visit method 
	 * 
	 * @param start of the tree or root group to iterate through all items of the tree
	 * @return number of messages from all users
	 */
	public int countTotalMessages(SysEntry item) {
		List<SysEntry> items = ((UserGroup)item).getEntries();
		//Initialize counter to 0
		int messageCount = 0;
		//Loop through entries
		for(int i = 0; i < items.size(); i++) {
			//Get element
			SysEntry e = items.get(i);
			if(e instanceof User) {
				int tweetCount = ((User)e).getTweetTotal();
				messageCount += tweetCount;
			}
			if(e instanceof UserGroup) {
				messageCount += countTotalMessages(e);
			}
		}
		return messageCount;
	}
}
