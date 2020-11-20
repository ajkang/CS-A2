import java.util.List;
/**
 * SysEntryVisitor visitor sub classes 
 * */
public class UserTotal implements SysEntryVisitor{
	/**
	 * Checks each of the items to get all of the users and groups within the tree
	 * Returns the total users in the entire tree 
	 * Used in the visit method
	 * 
	 * @param item is the start of the tree, so the root node 
	 * @return all users in tree
	 */
	public int countTotalUsers(SysEntry item) {
		List<SysEntry> items = ((UserGroup)item).getEntries();
		int users = 0;
		for(int i = 0; i < items.size(); i++) {
			SysEntry e = items.get(i);
			if(e instanceof UserGroup) { 
				users += countTotalUsers(e);
			}
			if(e instanceof User) { 
				users++;
			}
		}
		return users;
	}
	
	/**
	 * Uses the countTotalUsers method in order get and return the total users  
	 */
	@Override
	public int visit(SysEntry item) {
		int usersTotal = countTotalUsers(item);
		Admin.setVisitorOutput(usersTotal);
		return usersTotal;
	}
	
	
}
