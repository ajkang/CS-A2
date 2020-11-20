import java.util.List;
/**
 * SysEntryVisitor visitor sub classes 
 * */
public class GroupTotal implements SysEntryVisitor{
	/**
	 * Checks each of the items to get all of the groups within the tree
	 * Used in the visit method 
	 * 
	 * @param start of the tree or root group to iterate through all items of the tree
	 * @return number of groups
	 */
	public int countTotalGroups(SysEntry item) {
		List<SysEntry> items = ((UserGroup)item).getEntries();
		int groupsTotal = 0;
		for(int i = 0; i < items.size(); i++) {
			SysEntry e = items.get(i);
			if(e instanceof UserGroup) {
				groupsTotal++;
				groupsTotal += countTotalGroups(e);
			}
		}
		return groupsTotal;
	}
	
	/**
	 * Uses the countGroups method in order get the total without the root
	 * Add +1 to get the root node and total of all groups in the tree 
	 */
	@Override
	public int visit(SysEntry item) {
		int totalGroups = countTotalGroups(item);
		Admin.setVisitorOutput(totalGroups + 1);
		return totalGroups + 1; 
	}
}
