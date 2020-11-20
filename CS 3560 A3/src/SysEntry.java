/*
 * This class is used to create the hierarchy for the Visitor pattern  
 */

public abstract class SysEntry implements SysEntryVisitable{
	private String ID;
	
	/**
	 * Creates SysEntry object with a passed in ID (user or group)
	 * 
	 * @param ID is set to ID in this class as a private string 
	 */
	public SysEntry(String ID) { 
		this.ID = ID; 
	}
	
	/**
	 * Send the visitor's visit method the current object    
	 * 
	 * @param visitor of SysEntryVisitor type 
	 */
	public void accept(SysEntryVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Returns a string copy of the ID
	 * 
	 * @return ID as a string 
	 */
	public String getID() {
		//Don't return a direct reference to ID
		return String.copyValueOf(ID.toCharArray());
	}
}
