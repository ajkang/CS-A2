import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AdminPanelGUI {
	private JFrame mainAppWindow;
	private JTree treeDisplay;
	private JTextField userInputText;
	private JTextField groupInputText;
	private JButton addUserButton;
	private JButton addGroupButton;
	private JButton userViewButton;
	
	private DefaultMutableTreeNode chosenNode;
	private final DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode(new User("empty"));

	public AdminPanelGUI() {
		//open the window for admin panel 
		initialize();
		mainAppWindow.setVisible(true);
	}
	
	//Initialize to show GUI elements of AdminPanelGUI
	private void initialize() {
		mainAppWindow = new JFrame();
		mainAppWindow.getContentPane().setBackground(new Color(176, 224, 230));
		mainAppWindow.setResizable(false);
		mainAppWindow.setTitle("MiniTwitter Admin");
		mainAppWindow.setBounds(100, 100, 579, 396);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		
		JScrollPane treeViewPanel = new JScrollPane();
		treeViewPanel.setBounds(10, 11, 156, 346);
		mainAppWindow.getContentPane().add(treeViewPanel);
		
		JLabel userIDText = new JLabel("User ID :");
		userIDText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		userIDText.setBounds(235, 103, 55, 27);
		mainAppWindow.getContentPane().add(userIDText);
		
		JLabel groupIDText = new JLabel("Group ID :");
		groupIDText.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		groupIDText.setBounds(225, 140, 65, 27);
		mainAppWindow.getContentPane().add(groupIDText);	
		
		JLabel welcomeText = new JLabel("Welcome to Mini Twitter! (✿◠ ‿ ◠)");
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
		welcomeText.setFont(new Font("Hiragino Maru Gothic ProN", Font.PLAIN, 19));
		welcomeText.setBounds(214, 11, 306, 58);
		mainAppWindow.getContentPane().add(welcomeText);
				
		treeDisplay = new JTree();
		treeDisplay.setBackground(new Color(224, 255, 255));
		treeDisplay.setFont(new Font("Hiragino Sans GB", Font.BOLD, 12));
		treeDisplay.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				//Lets you use the buttons when user or group is chosen in tree 
				addUserButton.setEnabled(true);
				addGroupButton.setEnabled(true);
				
				//Tree node clicked on 
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        treeDisplay.getLastSelectedPathComponent();
				//Set the clicked on node 
				chosenNode = node;
				try {
					//Get the node: user or group object 
					String nodeName = node.getUserObject().toString() == null ? "" : node.getUserObject().toString();
					//The users are leafs based on composite pattern 
					if(node.isLeaf()){
						if(!nodeName.equals("empty")) {
							userViewButton.setEnabled(true);
						} else {
							userViewButton.setEnabled(false);
						}
						//Get the parent of the node object 
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
						//Get the parent ID 
						String nodeParentName = (String)parent.getUserObject().toString();
						userInputText.setText(nodeName);
						groupInputText.setText(nodeParentName);
					} else {
						//UserGroup 
						userViewButton.setEnabled(false);
						userInputText.setText(null);
						groupInputText.setText(nodeName);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		treeDisplay.setExpandsSelectedPaths(true);
		treeDisplay.setShowsRootHandles(true);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(Admin.getRootEntry());
		treeDisplay.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(root) {
				{
					add(new DefaultMutableTreeNode(emptyNode));
				}
			}
		));
		
		treeViewPanel.setViewportView(treeDisplay);
		
		userInputText = new JTextField();
		userInputText.setBackground(new Color(224, 255, 255));
		userInputText.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 12));
		userInputText.setEditable(false);
		userInputText.setBounds(302, 98, 101, 29);
		mainAppWindow.getContentPane().add(userInputText);
		userInputText.setColumns(10);
		
		addUserButton = new JButton("Add User");
		addUserButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		//Initially set to disabled until a tree element is selected
		addUserButton.setEnabled(false);
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Add User
					AddUserGUI userDialog = new AddUserGUI();
					userDialog.setSelectedGroup(groupInputText.getText());
					userDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					userDialog.setVisible(true);
					
					//Get inputed ID, show model of tree
					//where to input a user, remove extra node if a folder 
					//insert a node, get parent of userGroup
					String inputID = userDialog.getID();
					if(inputID != null && Admin.getUserID(inputID) == null) {
						DefaultTreeModel model = (DefaultTreeModel) treeDisplay.getModel();
						DefaultMutableTreeNode insertionNode = chosenNode.isLeaf() ? (DefaultMutableTreeNode)chosenNode.getParent() : chosenNode;
						if(insertionNode.getChildCount() == 1) {
							DefaultMutableTreeNode child = (DefaultMutableTreeNode) insertionNode.getChildAt(0);
							if(child.getUserObject().toString().equals("empty")) {
								model.removeNodeFromParent(child);
							}
						}
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
						UserGroup parentGroup = Admin.getGroupID(insertionNode.getUserObject().toString());
						User newUser = new User(inputID, parentGroup);
						parentGroup.addUser(newUser);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		addUserButton.setBounds(404, 98, 99, 29);
		mainAppWindow.getContentPane().add(addUserButton);
		
		groupInputText = new JTextField();
		groupInputText.setBackground(new Color(224, 255, 255));
		groupInputText.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 12));
		groupInputText.setEditable(false);
		groupInputText.setColumns(10);
		groupInputText.setBounds(302, 138, 101, 29);
		mainAppWindow.getContentPane().add(groupInputText);
		
		addGroupButton = new JButton("Add Group");
		addGroupButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		addGroupButton.setEnabled(false);
		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 *  Get inputed ID, show model of tree
					 *  where to input a user
					 *  remove extra node when the folder 
					 *  insert a node, get parent of userGroup
					 */
					AddGroupGUI groupText = new AddGroupGUI();
					groupText.setChosenGroup(groupInputText.getText());
					groupText.setModalityType(ModalityType.APPLICATION_MODAL);
					groupText.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					groupText.setVisible(true);
					String inputID = groupText.getID();
					if(inputID != null && Admin.getGroupID(inputID) == null) {
						DefaultTreeModel model = (DefaultTreeModel) treeDisplay.getModel();
						DefaultMutableTreeNode insertionNode = chosenNode.isLeaf() ? (DefaultMutableTreeNode)chosenNode.getParent() : chosenNode;
						if(insertionNode.getChildCount() == 1) {
							DefaultMutableTreeNode child = (DefaultMutableTreeNode) insertionNode.getChildAt(0);
							if(child.getUserObject().toString().equals("empty")) {
								model.removeNodeFromParent(child);
							}
						}
						/*
						 * insert a node
						 * make new inserted node to recent one added
						 * created an empty node inside the folder 
						 */
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
						DefaultMutableTreeNode insertionNodeNext = insertionNode.getLastLeaf();
						//Insert empty node as a placeholder so it appears as a folder in the tree view
						DefaultMutableTreeNode emptyCopy = new DefaultMutableTreeNode(new User("empty"));
						model.insertNodeInto(emptyCopy, insertionNodeNext, insertionNodeNext.getChildCount());
						
						/*
						 * Find the parent group 
						 * Make and add a new group based on the inputID and parent group
						 */
						UserGroup parentGroup = Admin.getGroupID(insertionNode.getUserObject().toString());
						UserGroup newGroup = new UserGroup(inputID, parentGroup);
						parentGroup.addUserGroup(newGroup);
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		addGroupButton.setBounds(404, 138, 99, 29);
		mainAppWindow.getContentPane().add(addGroupButton);

		//user profile 
		userViewButton = new JButton("Open User Profile");
		userViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the user selected to open profile
				String userSelectedID = chosenNode.getUserObject().toString();
				User userSet = Admin.getUserID(userSelectedID);
				
				//new window
				UserProfileGUI newProfileWindow = new UserProfileGUI();
				newProfileWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				newProfileWindow.setUserProfile(userSet);
				newProfileWindow.setVisible(true);
			}
		});
		userViewButton.setEnabled(false);
		userViewButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		userViewButton.setBounds(249, 179, 248, 29);
		mainAppWindow.getContentPane().add(userViewButton);
		
		//user total button and actions 
		JButton userTotalButton = new JButton("Show User Total");
		userTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//find the user total through total user method 
				Admin.getRootEntry().accept(new UserTotal());
				int totalUsers = Admin.getVisitorOutput();
				//string used to display total in window
				String userTotalString = String.format("There are %d users.", totalUsers);
				//make new GUI for the Total Users 
				VisitorButtonsGUI visitorMessage = new VisitorButtonsGUI(userTotalString);
				visitorMessage.setTitle("Total Users");
				visitorMessage.setVisible(true);
			}
		});
		userTotalButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		userTotalButton.setBounds(185, 257, 188, 29);
		mainAppWindow.getContentPane().add(userTotalButton);
		
		//group total button and actions 
		JButton groupTotalButton = new JButton("Show Group Total");
		groupTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.getRootEntry().accept(new GroupTotal());
				//find the group total through total group method 
				int groupTotal = Admin.getVisitorOutput();
				//string passed in to display total
				String groupTotalString = String.format("There are %d groups", groupTotal);
				VisitorButtonsGUI visitorMessage = new VisitorButtonsGUI(groupTotalString);
				visitorMessage.setTitle("Total Groups");
				visitorMessage.setVisible(true);
			}
		});
		groupTotalButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		groupTotalButton.setBounds(383, 257, 188, 29);
		mainAppWindow.getContentPane().add(groupTotalButton);
		
		//message total button and actions 
		JButton totalMessagesButton = new JButton("Show Total Messages");
		totalMessagesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//find the message total through total message method 
				Admin.getRootEntry().accept(new MessageTotal());
				int messageTotal = Admin.getVisitorOutput();
				//Format string to contain amount of users.
				String messageTotalString = String.format("There are %d messages.", messageTotal);
				VisitorButtonsGUI visitorMessage = new VisitorButtonsGUI(messageTotalString);
				visitorMessage.setTitle("Total Messages");
				visitorMessage.setVisible(true);
			}
		});
		totalMessagesButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		totalMessagesButton.setBounds(185, 298, 188, 29);
		mainAppWindow.getContentPane().add(totalMessagesButton);
		
		//positive message total button and actions 
		JButton positivePercentButton = new JButton("Show Positive %");
		positivePercentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//find the + message total through total + message method 
				Admin.getRootEntry().accept(new MessageTotal());
				int posMessageTotal = Admin.getVisitorOutput();
				Admin.getRootEntry().accept(new PositiveMessageTotal());
				int totalPositiveMessages = Admin.getVisitorOutput();
				
				double positiveMessagePercent = 0.0;
				if(posMessageTotal != 0) {
					positiveMessagePercent = ((totalPositiveMessages + 0.0) / (posMessageTotal + 0.0)) * 100;
				} else {
					positiveMessagePercent = 0;
				}
				//string display for the GUI 
				String posMessageString = String.format("%.2f percent are positive messages.", positiveMessagePercent);
				//New dialog to show information
				VisitorButtonsGUI visitorMessage = new VisitorButtonsGUI(posMessageString);
				visitorMessage.setTitle("Positive Message Percent");
				visitorMessage.setVisible(true);
			}
		});
		positivePercentButton.setFont(new Font("Hiragino Sans", Font.BOLD, 12));
		positivePercentButton.setBounds(385, 298, 188, 29);
		mainAppWindow.getContentPane().add(positivePercentButton);

		JSeparator lineSeparator = new JSeparator();
		lineSeparator.setForeground(new Color(224, 255, 255));
		lineSeparator.setBounds(176, 220, 397, 12);
		mainAppWindow.getContentPane().add(lineSeparator);
		
	}
}
