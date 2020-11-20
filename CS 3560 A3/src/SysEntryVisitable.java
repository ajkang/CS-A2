public interface SysEntryVisitable {
	/**
	 * Method accept used for visitor class to create accept method 
	 * 
	 * @param SysEntry objective with variable visitor passed
	 */
	public void accept(SysEntryVisitor visitor);
}
