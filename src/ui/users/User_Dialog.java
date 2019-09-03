package ui.users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import core.User_;
import dao.User_DAO;
import ui.App;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class User_Dialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField usernameTextField;

	private JCheckBox adminCheckBox;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	
	private User_ previousUser;
	private App app;
	private User_DAO user_DAO;
	private boolean user_updateMode= false;
	private JLabel lblBachelor;
	private JCheckBox bachelorCheckBox;
	private JLabel lblContestant;
	private JCheckBox contestantCheckBox;
	
	/**
	 * Create the dialog.
	 */
	public User_Dialog(App app, User_DAO user_theDAO, User_ user_) {
		this(app, user_theDAO, true);
		previousUser = user_;
		
			setTitle("Update User");						
			populateGui(previousUser);
			
			// hide password fields
			hidePasswordFields();
	}
	
	/**
	 * Create the dialog.
	 */
	public User_Dialog(App user_app, User_DAO theUserDAO, boolean theUpdateMode) {
		
		this();
		
		app = user_app;
		user_DAO = theUserDAO;
		user_updateMode = theUpdateMode;
	}

	public User_Dialog() {
		setTitle("Register User");
		
		setBounds(100, 100, 450, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Full Name");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "4, 2, fill, default");
			nameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Username");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			usernameTextField = new JTextField();
			contentPanel.add(usernameTextField, "4, 4, fill, default");
			usernameTextField.setColumns(10);
		}
		{
			passwordLabel = new JLabel("Password");
			passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(passwordLabel, "2, 6, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 6, fill, default");
		}
		{
			confirmPasswordLabel = new JLabel("Confirm Password");
			confirmPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(confirmPasswordLabel, "2, 8, right, default");
		}
		{
			confirmPasswordField = new JPasswordField();
			contentPanel.add(confirmPasswordField, "4, 8, fill, default");
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Admin");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_3, "2, 10");
		}
		{
			adminCheckBox = new JCheckBox("");
			contentPanel.add(adminCheckBox, "4, 10");
		}
		{
			lblBachelor = new JLabel("Bachelor");
			contentPanel.add(lblBachelor, "2, 12, right, default");
		}
		{
			bachelorCheckBox = new JCheckBox("");
			contentPanel.add(bachelorCheckBox, "4, 12");
		}
		{
			lblContestant = new JLabel("Contestant");
			contentPanel.add(lblContestant, "2, 14, right, default");
		}
		{
			contestantCheckBox = new JCheckBox("");
			contentPanel.add(contestantCheckBox, "4, 14");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						user_save();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void populateGui(User_ user_) {

		nameTextField.setText(user_.getName());
		usernameTextField.setText(user_.getUsername());

		adminCheckBox.setSelected(user_.isAdmin());
	}

	private void hidePasswordFields() {
		
		passwordField.setVisible(false);
		confirmPasswordField.setVisible(false);
	
		passwordLabel.setVisible(false);
		confirmPasswordLabel.setVisible(false);
	}
	
	private void user_save() {

		String name = nameTextField.getText();
		String userName = usernameTextField.getText();
		boolean admin = adminCheckBox.isSelected();
		boolean bachelor = bachelorCheckBox.isSelected();
		boolean contestant = contestantCheckBox.isSelected();
				
		User_ user_ = null;
		
		if (user_updateMode) {
			user_ = previousUser;
			
			user_.setName(name);
			user_.setUsername(userName);
			user_.setAdmin(admin);
			user_.setBachelor(bachelor);
			user_.setContestant(contestant);
			
		} else {
			String password = new String(passwordField.getPassword());
			String confirmPassword = new String(confirmPasswordField.getPassword());

			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(this,
						"Passwords do not match.", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
			
			user_ = new User_(name, userName, password, admin, bachelor, contestant);
		}

		try {
			// save to the database
			if (user_updateMode) {
				user_DAO.user_update(user_);
				
				// check to see if we need to update GUI fields for current user
				if (app.user_getCurrentId() == user_.getId()) {
					app.setAdmin(user_.isAdmin());
					app.setBachelor(user_.isBachelor());
					app.setContestant(user_.isContestant());
				}
			} else {
				user_DAO.user_add(user_);
			}

			// close dialog
			setVisible(false);

			// show success message
			JOptionPane.showMessageDialog(app,
					"User saved succesfully.", "User Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(app,
					"Error saving user: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
