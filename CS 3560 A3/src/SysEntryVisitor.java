/**
 * The sub classes that use the visitor design pattern use this method by overriding this one 
 */
public interface SysEntryVisitor {
	/**
	 * Uses passed in item of type SysEntry to "visit"
	 * 
	 * @param item in the tree 
	 * @return int returned based on item 
	 */
	public int visit(SysEntry item);
}
