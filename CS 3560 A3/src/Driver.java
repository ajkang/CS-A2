

import java.awt.EventQueue;

public class Driver {

	/*
	 * Starts with checking if AdminController is an instance and opens 
	 * a new window for the 1st window
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin.getInstance();
					new AdminPanelGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
