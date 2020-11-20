import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TweetMessageGUI extends JDialog {

	private static final long serialVersionUID = -8603177532087959881L;
	private JButton postTweetButton;
	private JButton cancelButton;
	private JTextArea tweetMessageTextBox;;

	//starts the application window for tweets 
	public static void main(String[] args) {
		try {
			TweetMessageGUI dialog = new TweetMessageGUI();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//GUI details are created when main runs 
	public TweetMessageGUI() {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		
		setModal(true);
		setType(Type.POPUP);
		setTitle("Tweet ");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(null);
		{
			tweetMessageTextBox = new JTextArea();
			tweetMessageTextBox.setBackground(new Color(224, 255, 255));
			tweetMessageTextBox.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			tweetMessageTextBox.setWrapStyleWord(true);
			tweetMessageTextBox.setLineWrap(true);
			tweetMessageTextBox.setBounds(41, 33, 356, 159);
			getContentPane().add(tweetMessageTextBox);
		}
		{
			JLabel tweetMessageText = new JLabel("Tweet Message:");
			tweetMessageText.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			tweetMessageText.setBounds(40, 11, 116, 19);
			getContentPane().add(tweetMessageText);
		}
		//close window closes the application
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				tweetMessageTextBox = null;
			}
		});
		{
			cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Cancel button clicked. Invalide message so a new tweet isn't posted
					tweetMessageTextBox = null;
					dispose();
				}
			});
			cancelButton.setBounds(237, 211, 104, 40);
			getContentPane().add(cancelButton);
		}
		{
			postTweetButton = new JButton("Post Tweet");
			postTweetButton.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
			postTweetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Post button clicked. Check if message is empty. If so, invalidate it so a new message isn't posted
					if(tweetMessageTextBox.getText().equals("")) { tweetMessageTextBox = null; }
					dispose();
				}
			});
			postTweetButton.setBounds(99, 211, 104, 40);
			getContentPane().add(postTweetButton);
		}
		getRootPane().setDefaultButton(postTweetButton);
	}
	
	/**
	 * Get's the message that that we prompted the user for.
	 * 
	 * @return the message we prompted for
	 */
	public String getTweetMessage() {
		return tweetMessageTextBox.getText(); 
	}
}
