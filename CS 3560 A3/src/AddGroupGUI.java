import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class AddGroupGUI extends JDialog {
	private JTextField groupIDInputText;
	private JTextField parentGroupInputText;
	private JButton okButton;
	private JButton cancelButton;
	private String chosenGroup;
	
	public static void main(String[] args) {
		//starts the GUI for the add group window
		try {
			AddGroupGUI dialog = new AddGroupGUI(); 
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This constructor is where all the GUI is created for the add group window 
	 * */
	public AddGroupGUI() {
		setFont(new Font("Hiragino Sans GB", Font.BOLD, 13));
		getContentPane().setBackground(new Color(176, 224, 230));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				groupIDInputText = null; //x button 
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setResizable(false);
		setTitle("Add Group");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		JLabel groupIDText = new JLabel("Group ID:");
		groupIDText.setBackground(new Color(224, 255, 255));
		groupIDText.setHorizontalAlignment(SwingConstants.CENTER);
		groupIDText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		groupIDText.setBounds(42, 37, 90, 17);
		getContentPane().add(groupIDText);
		
		JLabel parentGroupText = new JLabel("Parent Group:");
		parentGroupText.setBackground(new Color(224, 255, 255));
		parentGroupText.setHorizontalAlignment(SwingConstants.CENTER);
		parentGroupText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		parentGroupText.setBounds(26, 71, 106, 17);
		getContentPane().add(parentGroupText);
		
		groupIDInputText = new JTextField();
		groupIDInputText.setBackground(new Color(224, 255, 255));
		groupIDInputText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		groupIDInputText.setHorizontalAlignment(SwingConstants.LEFT);
		groupIDInputText.setColumns(8);
		groupIDInputText.setBounds(126, 35, 128, 20);
		getContentPane().add(groupIDInputText);
		
		parentGroupInputText = new JTextField();
		parentGroupInputText.setBackground(new Color(224, 255, 255));
		parentGroupInputText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		parentGroupInputText.setHorizontalAlignment(SwingConstants.LEFT);
		parentGroupInputText.setEditable(false);
		parentGroupInputText.setColumns(8);
		parentGroupInputText.setBounds(126, 69, 128, 20);
		getContentPane().add(parentGroupInputText);
		
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		//cancel button actions --> closing the window 
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupIDInputText = null; 
				dispose();
			}
		});
		cancelButton.setBounds(156, 115, 81, 36);
		getContentPane().add(cancelButton);
		
		okButton = new JButton("Ok");
		okButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(groupIDInputText.getText() == "") { 
					groupIDInputText = null; 
				}
				dispose();
			}
		});
		okButton.setBounds(71, 115, 75, 36);
		getContentPane().add(okButton);
		
		getRootPane().setDefaultButton(okButton);
	}
	/**
	 * Gets the ID of group from user
	 * 
	 * @return the ID of group 
	 */
	public String getID() { 
		if(this.groupIDInputText != null) {
			return this.groupIDInputText.getText(); 
		}else{
			return null;
		}
	}
	
	/**
	 * Sets the chosen group to the parent group text box field
	 * 
	 * @param group ID added to "parent" of the group
	 */
	public void setChosenGroup(String group) {
		this.chosenGroup = group;
		parentGroupInputText.setText(this.chosenGroup);
	}
}
