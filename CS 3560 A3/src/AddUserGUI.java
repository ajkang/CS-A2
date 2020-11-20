import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddUserGUI extends JDialog {
	
	private JTextField userIDInputText;
	private JTextField groupInputText;
	
	private JButton cancelButton;
	private JButton okButton;
	
	private String chosenGroup;

	public static void main(String[] args) {
		try {
			AddUserGUI dialog = new AddUserGUI();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//display the GUI 
	public AddUserGUI() {
		getContentPane().setBackground(new Color(176, 224, 230));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//"X" button clicked. Invalidate input so a new user isn't added
				userIDInputText = null;
			}
		});
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Add User ");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		{
			JLabel userIDText = new JLabel("User ID:");
			userIDText.setBounds(29, 30, 81, 17);
			userIDText.setHorizontalAlignment(SwingConstants.CENTER);
			userIDText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			getContentPane().add(userIDText);
		}
		{
			userIDInputText = new JTextField();
			userIDInputText.setBackground(new Color(224, 255, 255));
			userIDInputText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			userIDInputText.setBounds(116, 29, 130, 20);
			userIDInputText.setHorizontalAlignment(SwingConstants.LEFT);
			getContentPane().add(userIDInputText);
			userIDInputText.setColumns(8);
		}
		{
			JLabel groupIDText = new JLabel("Group ID:");
			groupIDText.setBounds(39, 63, 71, 17);
			groupIDText.setHorizontalAlignment(SwingConstants.CENTER);
			groupIDText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			getContentPane().add(groupIDText);
		}
		
		{
			okButton = new JButton("Ok");
			okButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(userIDInputText.getText() == "") 
					{ 
						userIDInputText = null; 
					}
					dispose();
				}
			});
			okButton.setBounds(73, 112, 67, 36);
			getContentPane().add(okButton);
		}
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		//cancel button closes application window
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userIDInputText = null;
				dispose();
			}
		});
		cancelButton.setBounds(150, 112, 81, 36);
		getContentPane().add(cancelButton);
		
		{
			groupInputText = new JTextField();
			groupInputText.setBackground(new Color(224, 255, 255));
			groupInputText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			groupInputText.setEditable(false);
			groupInputText.setHorizontalAlignment(SwingConstants.LEFT);
			groupInputText.setColumns(8);
			groupInputText.setBounds(116, 63, 130, 20);
			
			getContentPane().add(groupInputText);
		}		
		getRootPane().setDefaultButton(okButton);
	}
	
	/**
	 * Set's the selected group to display in the second text box
	 * 
	 * @param group the ID of the group we are adding this user to
	 */
	public void setSelectedGroup(String group) {
		this.chosenGroup = group;
		groupInputText.setText(this.chosenGroup);
	}
	
	/**
	 * Gets the ID that we prompted the user for.
	 * 
	 * @return the ID we prompted for
	 */
	public String getID() { 
		if(this.userIDInputText != null) {
			return this.userIDInputText.getText(); 
		}else {
			return null; 
		}
	}
}
