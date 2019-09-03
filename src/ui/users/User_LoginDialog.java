package ui.users;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import core.User_;
import dao.Statement_DAO;
import dao.Table_DAO;
import dao.User_DAO;
import ui.App;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class User_LoginDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JComboBox user_ComboBox;
	
	private User_DAO user_DAO;
	private Statement_DAO statement_DAO;
	private Table_DAO table_DAO;
	
	
	
	public void user_setDAO(User_DAO user_theDAO) {
		user_DAO = user_theDAO;
	}
	

	public void statement_setDAO(Statement_DAO statement_theDAO) {
		statement_DAO = statement_theDAO;
	}

	public void table_setDAO(Table_DAO table_theDAO) {
		table_DAO = table_theDAO;
	}
	
	public void user_populate(List<User_> user_) {
		user_ComboBox.setModel(new DefaultComboBoxModel(user_.toArray(new User_[0])));
	}
	
	public static void main(String[] args) {
		try {
			User_LoginDialog dialog = new User_LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User_LoginDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("User Login");
		setBounds(100, 100, 450, 168);
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
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblUser = new JLabel("User");
			contentPanel.add(lblUser, "2, 2, right, default");
		}
		{
			user_ComboBox = new JComboBox();
			contentPanel.add(user_ComboBox, "4, 2, fill, default");
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword, "2, 4, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 4, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						user_performLogin();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void user_performLogin() {
		
		try {
						
			// get the user id
			if (user_ComboBox.getSelectedIndex() == -1) {						
				JOptionPane.showMessageDialog(User_LoginDialog.this, "You must select a user_.", "Error", JOptionPane.ERROR_MESSAGE);				
				return;
			}

			User_ user_ = (User_) user_ComboBox.getSelectedItem();
			int user_id = user_.getId();
			boolean user_admin = user_.isAdmin();
			boolean user_bachelor = user_.isBachelor();
			boolean user_contestant = user_.isContestant();
			
			// get the password
			String plainTextPassword = new String(passwordField.getPassword());
			user_.setPassword(plainTextPassword);

			// check the user's password against the encrypted version in the database
			boolean isValidPassword = user_DAO.authenticate(user_);

			if (isValidPassword) {
				// hide the login window
				setVisible(false);

				// now show the main app window
				if (user_admin) {
					
					App frame = new App(user_id, user_admin,  false, false, user_DAO, statement_DAO, table_DAO);
					start(frame, user_);
										
				} else if(user_bachelor) {
					
					App frame = new App(user_id, false, user_bachelor, false, user_DAO, statement_DAO, table_DAO);
					start(frame, user_);
					
					setComponentVisibility(frame.getStatementPanel(), JPanel.class, false);
					setComponentVisibility(frame.getTablePanel(), JPanel.class, false);
					
				} else if(user_contestant) {
					
					App frame = new App(user_id, false, false, user_contestant,user_DAO, statement_DAO, table_DAO);
					start(frame, user_);
					
					setComponentVisibility(frame.getStatementPanel(), JPanel.class, false);
					setComponentVisibility(frame.getTablePanel(), JPanel.class, false);

					frame.getCreateButton().setVisible(false);
					frame.getDeleteButton().setVisible(false);
				}
				
			}
			else {
				// show error message
				JOptionPane.showMessageDialog(this, "Invalid login", "Invalid Login",
						JOptionPane.ERROR_MESSAGE);

				return;			
			}
			
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error during login", "Error",
					JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	private void start(App frame, User_ user_) {
		frame.setLoggedInUserName(user_.getName(), ""+user_.getId());
		frame.setVisible(true);
		frame.user_refresh();
		frame.bachelor_refresh();
		frame.contestant_refresh();
	}

	static void setComponentVisibility(Container container,
	        Class<? extends Component> componentClass, boolean visible) {
	    for (Component c : container.getComponents()) {
	        if (componentClass.isAssignableFrom(c.getClass())) {
	            c.setVisible(visible);
	        } else if (c instanceof Container) {
	            setComponentVisibility((Container)c, componentClass, visible);
	        }
	    }
	}
}
