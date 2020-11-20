import java.util.List;
/**
 * SysEntryVisitor visitor sub classes 
 * */
public class PositiveMessageTotal implements SysEntryVisitor{
	/**
	 * Checks each of the items to get all + messages within the tree
	 * Checks if the user has + messages and increments the counter
	 * If the item is a User --> get all + messages using isPositiveMessage method
	 * If the item is a UserGroup --> recursively search to find a user  
	 * Used in the visit method 
	 * 
	 * @param item of the root to iterate through 
	 * @return positive messages counted for in entire tree 
	 */
	public int countPositiveMessages(SysEntry item) {
		int positiveMessages = 0;
		List<SysEntry> items= ((UserGroup)item).getEntries();
		for(int i = 0; i < items.size(); i++) {
			//Get element
			SysEntry e = items.get(i);
			//Loop through elements messages if it is a user
			if(e instanceof User) {
				List<String> elementMessages = ((User)e).getOwnTweets();
				for(int j = 0; j < elementMessages.size(); j++) {
					if(isPositiveWord(elementMessages.get(j))) {
						positiveMessages += 1; 
					}else {
						positiveMessages += 0;
					}
				}
			}
			if(e instanceof UserGroup) {
				positiveMessages += countPositiveMessages(e);
			}
		}
		return positiveMessages;
	}
	
	/**
	 * Checks if a message contains these words to be used in countPositiveMessages
	 * 
	 * @param message the message to check
	 * @return true if it contains positive words, false if it doesn't
	 */
	public boolean isPositiveWord(String message) {
		if(message.contains("happy") || message.contains("nice") || message.contains("beautiful")
				|| message.contains("pretty") || message.contains("fun") ||message.contains("wonderful") 
				|| message.contains("smile") || message.contains("accomplish") || message.contains("spectacular")) { 
			return true; 
		}
		return false;
	}
	
	/**
	 * Uses the countPositiveMessage method in order get the total positive messages 
	 */
	@Override
	public int visit(SysEntry item) {
		int positiveMessageTotal = countPositiveMessages(item);
		Admin.setVisitorOutput(positiveMessageTotal);
		return positiveMessageTotal;
	}
}
