

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VisitorButtonsGUI extends JDialog {
	private JButton okButton;
	private JTextArea textFieldVisitor;
	
	//starts the application and shows GUI 
	static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisitorButtonsGUI dialog = new VisitorButtonsGUI();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//displays the GUI based on main method 
	public VisitorButtonsGUI() {
		getContentPane().setBackground(new Color(176, 224, 230));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(null);
		
		textFieldVisitor = new JTextArea();
		textFieldVisitor.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		textFieldVisitor.setBackground(new Color(224, 255, 255));
		textFieldVisitor.setLineWrap(true);
		textFieldVisitor.setWrapStyleWord(true);
		textFieldVisitor.setEditable(false);
		textFieldVisitor.setBounds(33, 11, 216, 50);
		textFieldVisitor.setText("The");
		getContentPane().add(textFieldVisitor);
		
		okButton = new JButton("Ok");
		okButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setBounds(111, 72, 73, 23);
		getContentPane().add(okButton);
	}
	
	/**
	 * Uses passed in text to set the text in the box 
	 * 
	 * @param text display 
	 */
	public VisitorButtonsGUI(String txt) {
		this();
		textFieldVisitor.validate();
		setMainText(txt);
	}
	
	/**
	 * Sets the text based on the visitor class
	 * 
	 * @param text inside text box
	 */
	public void setMainText(String txt) { textFieldVisitor.setText(txt); }	
}
