import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FollowUserGUI extends JDialog {
	private static final long serialVersionUID = 535905014821001274L;
	private JTextField userIDInput;
	private JButton okButton;
	private JButton cancelButton;
	
	private String ownUserID;
	
	//starts the application
	public static void main(String[] args) {
		try {
			FollowUserGUI dialog = new FollowUserGUI();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//shows the details of the GUI 
	public FollowUserGUI() {
		getContentPane().setBackground(new Color(176, 224, 230));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				userIDInput = null;
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setTitle("Follow User");
		setType(Type.POPUP);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		JLabel userIDText = new JLabel("Enter User ID:");
		userIDText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		userIDText.setBounds(43, 22, 97, 28);
		getContentPane().add(userIDText);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		//prevents empty acceptance of input and close window
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userIDInput = null;
				dispose();
			}
		});
		cancelButton.setBounds(139, 116, 89, 23);
		getContentPane().add(cancelButton);
		
		okButton = new JButton("Ok");
		okButton.setForeground(new Color(0, 0, 0));
		okButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		okButton.addActionListener(new ActionListener() {
			//prevent empty acceptance of input and close window
			public void actionPerformed(ActionEvent e) {
				if(userIDInput.getText() == "") { 
					userIDInput = null; 
				}
				dispose();
			}
		});
		okButton.setEnabled(false);
		okButton.setBounds(61, 116, 68, 23);
		getContentPane().add(okButton);
		
		userIDInput = new JTextField();
		userIDInput.setBackground(new Color(224, 255, 255));
		userIDInput.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		userIDInput.setColumns(10);
		userIDInput.setBounds(43, 52, 185, 37);
		userIDInput.getDocument().addDocumentListener(new DocumentListener() {
			//code in order to accept a valid ID when ok button is pressed
			public void insertUpdate(DocumentEvent e) {
				okButton.setEnabled(Admin.getUserID(userIDInput.getText()) != null && !userIDInput.getText().equals(ownUserID));
			}
			public void removeUpdate(DocumentEvent e) {
				okButton.setEnabled(Admin.getUserID(userIDInput.getText()) != null && !userIDInput.getText().equals(ownUserID));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		getContentPane().add(userIDInput);
	}
	
	/**
	 * Get user ID for follow if it exist or null 
	 * 
	 * @return user ID 
	 */
	public String getUserID() {
		if(this.userIDInput != null) {
			return this.userIDInput.getText(); 
		}else {
			return null; 
		}
	}
	
	/**
	 * Set the user ID that to the current user to 
	 * userID is the current user that's trying to follow 
	 * 
	 * @param userID the ID of the user who will be following another
	 */
	public void setOwnUserID(String ownUserID) {
		this.ownUserID = ownUserID;
	}
}
