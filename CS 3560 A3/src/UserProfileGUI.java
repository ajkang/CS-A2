import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UserProfileGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private User user;
	@SuppressWarnings("rawtypes")
	private JList currentlyFollowingBox;
	@SuppressWarnings("rawtypes")
	private ListModel listFollowingModel = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private JList listFeed;
	@SuppressWarnings("rawtypes")
	private ListModel listNewsFeedModel = new DefaultListModel();
	private JPanel contentPane;
	private JButton followUserButton;
	private JButton tweetMessageButton;
	private JTextField userIDInput;
	
	//start application to show GUI 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfileGUI frame = new UserProfileGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Details fo GUI displayed when main runs 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserProfileGUI() {
		setType(Type.POPUP);
		setTitle("User Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane newFeedScrollPane = new JScrollPane();
		newFeedScrollPane.setBounds(29, 314, 382, 180);
		contentPane.add(newFeedScrollPane);
		
		JScrollPane currentlyFollowingScrollPane = new JScrollPane();
		currentlyFollowingScrollPane.setBounds(29, 81, 382, 125);
		contentPane.add(currentlyFollowingScrollPane);
		
		JLabel newsFeedText = new JLabel("News Feed : ＼（＾○＾）人（＾○＾）／");
		newsFeedText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		newsFeedText.setBounds(29, 289, 249, 24);
		contentPane.add(newsFeedText);
		
		JLabel userIDText = new JLabel("User ID:");
		userIDText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		userIDText.setBounds(44, 15, 52, 24);
		contentPane.add(userIDText);
		
		JLabel currentlyFollowingText = new JLabel("Curently Following : (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ");
		currentlyFollowingText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		currentlyFollowingText.setBounds(29, 56, 249, 24);
		contentPane.add(currentlyFollowingText);
		
		listFeed = new JList();
		
		listFeed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFeed.setBorder(null);
		newFeedScrollPane.setViewportView(listFeed);
		listFeed.setModel(listNewsFeedModel);
		
		followUserButton = new JButton("Follow User");
		followUserButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		followUserButton.addActionListener(new ActionListener() {
			//following user gets the ID to follow a valid user
			//have the user follow other user and update 
			public void actionPerformed(ActionEvent e) {
				FollowUserGUI followUser = new FollowUserGUI();
				followUser.setOwnUserID(user.getID());
				followUser.setModalityType(ModalityType.APPLICATION_MODAL);
				followUser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				followUser.setVisible(true);
				String getID = followUser.getUserID();
				if(!user.isFollowing(getID)) {
					user.followUser(getID);
					((DefaultListModel)listFollowingModel).addElement(getID);
				}
			}
		});
		followUserButton.setEnabled(false);
		followUserButton.setBounds(259, 11, 152, 34);
		contentPane.add(followUserButton);
		
		userIDInput = new JTextField();
		userIDInput.setEditable(false);
		userIDInput.setBounds(97, 11, 152, 34);
		contentPane.add(userIDInput);
		userIDInput.setColumns(10);
		
		tweetMessageButton = new JButton("Tweet Message");
		tweetMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TweetMessageGUI tweetGUI = new TweetMessageGUI();
				tweetGUI.setModalityType(ModalityType.APPLICATION_MODAL);
				tweetGUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				tweetGUI.setVisible(true);
				//get tweet message 
				String tweetInput = tweetGUI.getTweetMessage();
				//Display what tweet said from user
				((DefaultListModel)listNewsFeedModel).addElement((user.getID() + " : "+ tweetInput));
				//user post tweet 
				user.postTweet(tweetInput);
			}
		});
		tweetMessageButton.setEnabled(false);
		tweetMessageButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		tweetMessageButton.setBounds(88, 218, 257, 34);
		contentPane.add(tweetMessageButton);
		
		//List of followings set and created 
		currentlyFollowingBox = new JList();
		currentlyFollowingBox.setBounds(29, 83, 378, 110);
		contentPane.add(currentlyFollowingBox);
		currentlyFollowingBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentlyFollowingBox.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		currentlyFollowingBox.setBorder(null);
		currentlyFollowingBox.setModel(listFollowingModel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(224, 255, 255));
		separator.setBounds(29, 264, 382, 12);
		contentPane.add(separator);
	}
	
	/**
	 * Sets the user and creates the user profile for the GUI that is 
	 * passed into setUser in this class  
	 * 
	 * @param user 
	 */
	public UserProfileGUI(User user) {
		this();
		setUserProfile(user);	
	}
	
	/**
	 * Sets the user profile in the UserView class and in this class
	 * and updates the feed and following 
	 * 
	 * @param user to set up user in a given class 
	 */
	public void setUserProfile(User user) {
		this.user = user;
		setTitle("User Profile - " + user.getID());
		//Unlock buttons
		followUserButton.setEnabled(true);
		tweetMessageButton.setEnabled(true);
		userIDInput.setText(user.getID());
		currentlyFollowingBox = new JList(user.getFollowingsList().toArray());
		listFeed = new JList(user.getNewsFeed().toArray());
		user.setUserProfile(this);
	}
	
	/**
	 * New feed adds the message to the list of new feed 
	 * 
	 * @param message of tweet that is posted 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateNewsFeed(String message) { 
		((DefaultListModel)listNewsFeedModel).addElement(message); 
	}
}
