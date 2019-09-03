package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import core.Statement_;
import core.Table_;
import core.User_;
import dao.Statement_DAO;
import dao.Table_DAO;
import dao.User_DAO;
import ui.bachelor_.Bachelor_Dialog;
import ui.contestant_.Contestant_Dialog;
import ui.users.User_Dialog;
import ui.users.User_LoginDialog;
import util.User_PasswordUtils;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;

public class App extends JFrame {

	//global_variable	
	private JPanel contentPane;
	
	private JTabbedPane tabbedPane;
	
	private JPanel topPanel;
	private JLabel lblLoggedIn;
	private JLabel loggedInUserLabel;

	/**/
	private User_DAO user_DAO;
	private Statement_DAO statement_DAO;
	private Table_DAO table_DAO;
	
	private int user_id;
	private boolean user_admin;
	private boolean user_bachelor;
	private boolean user_contestant;
	
	//statement_tab
	public JPanel statement_Panel;
	private JPanel panel;
	private JLabel lblSqlStatement;
	private JPanel panel_1;
	private JButton statement_btnExecute;
	private JLabel statement_statusLabel;
	private JTextArea statement_textArea;
	private JSplitPane splitPane;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblSqlStatement_1;
	private JLabel lblResult;
	private JTextArea statement_resultTextArea;
	public JPanel table_Panel;
	private JPanel panel_4;
	private JButton table_btnExecute;
	private JPanel panel_5;
	private JTextArea table_textArea;
	private JScrollPane scrollPane;
	private JTable table_table;
	private JPanel tool_Panel;
	
	public JButton btnCreate;
	private JButton btnUpdate;
	public JButton btnDelete;
	
	private JButton btnChange;
	private JLabel lblSearch;
	private JTextField searchTextField;
	private JButton btnSearch;
	private JComboBox columnComboBox;
	private JPanel bachelor_Panel;
	private JPanel panel_8;
	private JScrollPane scrollPane_1;
	private JTable bachelor_table;
	private JButton btnRefresh;
	private JPanel contestant_Panel;
	private JPanel panel_9;
	private JButton btnRefresh_1;
	private JScrollPane scrollPane_2;
	private JTable contestant_table;
	private JPanel user_Panel;
	private JPanel panel_11;
	private JButton btnNewButton;
	private JScrollPane scrollPane_3;
	private JTable user_Table;
	private JTextField columnTextField;
	private JLabel lblColumn;
	private JLabel id_Label;
	private JLabel lblId;
	
	
	/**
	 * Launch the application.
	 */
	//main_method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					User_DAO user_DAO = new User_DAO();
					Statement_DAO statement_DAO = new Statement_DAO();
					Table_DAO table_DAO = new Table_DAO();
										
					// Get users
					List<User_> user_ = user_DAO.user_get(true, 0);
										
					// Show login dialog
					User_LoginDialog dialog = new User_LoginDialog();
					dialog.user_populate(user_);
					dialog.user_setDAO(user_DAO);
					dialog.statement_setDAO(statement_DAO);
					dialog.table_setDAO(table_DAO);
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

//App_Admin
	public App(int user_theId, boolean user_theAdmin, boolean user_theBachelor, boolean user_theContestant,
			User_DAO user_theDAO, 
			Statement_DAO statement_theDAO, Table_DAO table_theDAO) {
		setTitle("The Bachelor v1.0");
		
		user_DAO = user_theDAO;
		statement_DAO = statement_theDAO;
		table_DAO = table_theDAO;
		
		user_id = user_theId;
		user_admin= user_theAdmin;
		user_bachelor= user_theBachelor;
		user_contestant= user_theContestant;
		
		//border_panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//add-on
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//top_panel
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		lblLoggedIn = new JLabel("Logged In:");
		
		loggedInUserLabel = new JLabel("New label");
		topPanel.setLayout(new MigLayout("", "[49px][45px][45px][][][][][][][][][][][][][][][]", "[13px]"));
		topPanel.add(lblLoggedIn, "cell 0 0,alignx left,aligny top");
		topPanel.add(loggedInUserLabel, "cell 1 0,alignx left,aligny top");
		
		lblId = new JLabel("User ID:");
		topPanel.add(lblId, "cell 9 0");
		
		id_Label = new JLabel("New label");
		topPanel.add(id_Label, "cell 10 0,alignx left,aligny top");
		
		user_Panel = new JPanel();
		tabbedPane.addTab("Users", null, user_Panel, null);
		user_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_11 = new JPanel();
		user_Panel.add(panel_11, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_refresh();
				
			}
		});
		panel_11.add(btnNewButton);
		
		btnChange = new JButton("Encrypt Password");
		panel_11.add(btnChange);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encryptPassword();
			}
		});
		
		scrollPane_3 = new JScrollPane();
		user_Panel.add(scrollPane_3, BorderLayout.CENTER);
		
		user_Table = new JTable();
		scrollPane_3.setViewportView(user_Table);
		
		bachelor_Panel = new JPanel();
		tabbedPane.addTab("Bachelors", null, bachelor_Panel, null);
		bachelor_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_8 = new JPanel();
		bachelor_Panel.add(panel_8, BorderLayout.SOUTH);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bachelor_refresh();
			}
		});
		panel_8.add(btnRefresh);
		
		scrollPane_1 = new JScrollPane();
		bachelor_Panel.add(scrollPane_1, BorderLayout.CENTER);
		
		bachelor_table = new JTable();
		scrollPane_1.setViewportView(bachelor_table);
		
		contestant_Panel = new JPanel();
		tabbedPane.addTab("Contestants", null, contestant_Panel, null);
		contestant_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_9 = new JPanel();
		contestant_Panel.add(panel_9, BorderLayout.SOUTH);
		
		btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contestant_refresh();
			}
		});
		panel_9.add(btnRefresh_1);
		
		scrollPane_2 = new JScrollPane();
		contestant_Panel.add(scrollPane_2, BorderLayout.CENTER);
		
		contestant_table = new JTable();
		scrollPane_2.setViewportView(contestant_table);

		
	//statement_panel	
		
		statement_Panel = new JPanel();
		tabbedPane.addTab("Statement_", null, statement_Panel, null);
		statement_Panel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		statement_Panel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		lblSqlStatement = new JLabel("SQL Statement status: ");
		panel.add(lblSqlStatement);
		
		statement_statusLabel = new JLabel("New label");
		panel.add(statement_statusLabel);
		
		panel_1 = new JPanel();
		statement_Panel.add(panel_1, BorderLayout.SOUTH);
		
		statement_btnExecute = new JButton("Execute");
		statement_btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				Statement_ statement_temp = new Statement_(statement_textArea.getText());
				App app = new App(user_id, user_admin, user_bachelor, user_contestant,
						user_DAO, statement_DAO, table_DAO);
				
				String result; 
				
				statement_statusLabel.setText(statement_textArea.getText());
				
				try {
					
					String statement_ = statement_temp.statement_get();
					
					result = statement_DAO.statement_inject(statement_);
					
					statement_resultTextArea.setText(result);

					// show success message
					JOptionPane.showMessageDialog(app,
							"Statement_ excecuted succesfully.", "Statement_ Executed",
							JOptionPane.INFORMATION_MESSAGE);
					statement_statusLabel.setText("Statement_ excecuted succesfully.");
		
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(app,
							"Error excecuting statement_: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					statement_statusLabel.setText("Error excecuting statement_: " + exc.getMessage());
					
				}
				
			}
		});
		panel_1.add(statement_btnExecute);		
		
	//table_tab	
		splitPane = new JSplitPane();
		statement_Panel.add(splitPane, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		splitPane.setLeftComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblSqlStatement_1 = new JLabel("SQL Statement:                                                                                                               ");
		panel_2.add(lblSqlStatement_1, BorderLayout.NORTH);
		
		statement_textArea = new JTextArea();
		panel_2.add(statement_textArea, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		splitPane.setRightComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblResult = new JLabel("Result: ");
		panel_3.add(lblResult, BorderLayout.NORTH);
		
		statement_resultTextArea = new JTextArea();
		panel_3.add(statement_resultTextArea, BorderLayout.CENTER);
		
		table_Panel = new JPanel();
		tabbedPane.addTab("Table_", null, table_Panel, null);
		table_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		table_Panel.add(panel_4, BorderLayout.SOUTH);
		
		table_btnExecute = new JButton("Execute");
		table_btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Table_ table_temp = new Table_(table_textArea.getText());
				
				try {
					
					String table_ = table_temp.table_get();
					
					ResultSet result = table_DAO.table_inject(table_);
					
					resultSetToTableModel(result, table_table);
					
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
				}
								
			}
		});
		panel_4.add(table_btnExecute);
		
		panel_5 = new JPanel();
		table_Panel.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		table_textArea = new JTextArea();
		table_textArea.setText("Insert SELECT Statement here");
		panel_5.add(table_textArea);
		
		scrollPane = new JScrollPane();
		table_Panel.add(scrollPane, BorderLayout.CENTER);
		
		table_table = new JTable();
		scrollPane.setViewportView(table_table);
		
		tool_Panel = new JPanel();
		contentPane.add(tool_Panel, BorderLayout.SOUTH);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInput();
			}
		});
		tool_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		tool_Panel.add(btnSearch);
		
		lblColumn = new JLabel("column:");
		tool_Panel.add(lblColumn);
		
		columnTextField = new JTextField();
		tool_Panel.add(columnTextField);
		columnTextField.setColumns(10);
		
		lblSearch = new JLabel("by ");
		tool_Panel.add(lblSearch);
				
		searchTextField = new JTextField();
		tool_Panel.add(searchTextField);
		searchTextField.setColumns(10);
		tool_Panel.add(btnCreate);
		tool_Panel.add(btnUpdate);
		tool_Panel.add(btnDelete);
		

	}

	
	public void setLoggedInUserName(String name, String id) {
		loggedInUserLabel.setText(name);
		id_Label.setText(""+id);
	}
	
	public int user_getCurrentId() {
		return user_id;
	}
	
	public void setAdmin(boolean theFlag) {
		user_admin = theFlag;
		btnCreate.setEnabled(theFlag);		
	}
	
	public void setBachelor(boolean theFlag) {
		user_admin = theFlag;
		btnCreate.setEnabled(theFlag);		
	}
	
	public void setContestant(boolean theFlag) {
		user_admin = theFlag;
		btnCreate.setEnabled(theFlag);		
	}
	
	
	public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{

        DefaultTableModel tableModel = new DefaultTableModel();

        ResultSetMetaData metaData = rs.getMetaData();

        int columnCount = metaData.getColumnCount();

        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        Object[] row = new Object[columnCount];
        
        while (rs.next()){
           
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }
	
	private void addInput() {
		
		if(user_Panel.isShowing()) {			
			User_Dialog user_Dialog = new User_Dialog(App.this, user_DAO, false);			
			user_Dialog.setVisible(true);		
		}
		
		if(bachelor_Panel.isShowing()) {
			Bachelor_Dialog bachelor_Dialog = new Bachelor_Dialog(App.this, false);
			bachelor_Dialog.setVisible(true);
		}

		if(contestant_Panel.isShowing()) {		
			Contestant_Dialog contestant_Dialog = new Contestant_Dialog(App.this, false);
			contestant_Dialog.setVisible(true);		
		}
	}
	
	protected void update() {
		if(user_Panel.isShowing()) {
			int row = user_Table.getSelectedRow();
			int column = user_Table.getSelectedColumn();
			String columnName = user_Table.getColumnName(column);

			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an user_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int userId = (int) user_Table.getValueAt(row, 0);
			String cellValue = ""+(String)user_Table.getValueAt(row, column);
			
			String sql = "UPDATE `bachelor_3`.`user_` SET `"+columnName+"` = '"+cellValue+"' "
					+ "WHERE (`user_id` = '"+userId+"')";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				String table_ = table_temp.table_get();
				ResultSet result = table_DAO.table_injectExecute(table_);
				resultSetToTableModel(result, user_Table);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(bachelor_Panel.isShowing()) {
			
			// get the selected item
			int row = bachelor_table.getSelectedRow();
			int column = bachelor_table.getSelectedColumn();
			String columnName = bachelor_table.getColumnName(column);

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an user_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int bachelorId = (int) bachelor_table.getValueAt(row, 0);
			String temp = ""+(String)bachelor_table.getValueAt(row, column);
			
			/**/
			
			String sql = "UPDATE `bachelor_3`.`bachelor_` SET `"+columnName+"` = '"+temp+"' WHERE (`bachelor_id` = '"+bachelorId+"')";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, bachelor_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(contestant_Panel.isShowing()) {
			
			// get the selected item
			int row = contestant_table.getSelectedRow();
			int column = contestant_table.getSelectedColumn();
			String columnName = contestant_table.getColumnName(column);

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an user_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int contestant_Id = (int) contestant_table.getValueAt(row, 0);
			String temp = ""+(String)contestant_table.getValueAt(row, column);
			
			/**/
			
			String sql = "UPDATE `bachelor_3`.`contestant_` SET `"+columnName+"` = '"+temp+"' WHERE (`contestant_id` = '"+contestant_Id+"')";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, contestant_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
			
	}
	
	protected void delete() {
				
		if(user_Panel.isShowing()) {
			
			// get the selected item
			int row = user_Table.getSelectedRow();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an user_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int userId = (int) user_Table.getValueAt(row, 0);
			
			/**/
			
			String sql = "DELETE FROM `bachelor_3`.`user_` WHERE (`user_id` = '" + userId + "');" ;
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, user_Table);
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(bachelor_Panel.isShowing()) {
			
			// get the selected item
			int row = bachelor_table.getSelectedRow();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an bachelor_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int bachelor_Id = (int) bachelor_table.getValueAt(row, 0);
			
			/**/
			
			String sql = "DELETE FROM `bachelor_3`.`bachelor_` WHERE (`bachelor_id` = '" + bachelor_Id + "');" ;
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, bachelor_table);
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(contestant_Panel.isShowing()) {
			
			// get the selected item
			int row = contestant_table.getSelectedRow();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an contestant_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int contestant_Id = (int) contestant_table.getValueAt(row, 0);
			
			/**/
			
			String sql = "DELETE FROM `bachelor_3`.`contestant_` WHERE (`contestant_id` = '" + contestant_Id + "');" ;
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, contestant_table);
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
	}
	
	protected void encryptPassword() {
		
		if(user_Panel.isShowing()) {
			
			btnChange.setVisible(true);
			
			// get the selected item
			int row = user_Table.getSelectedRow();
			int column = user_Table.getSelectedColumn();

			// make sure a row is selected
			if (row < 0) {
				JOptionPane.showMessageDialog(App.this, "You must select an user_", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
						
			int userId = (int) user_Table.getValueAt(row, 0);
			
			String temp = ""+(String)user_Table.getValueAt(row, column);
			
			String encrypted = User_PasswordUtils.encryptPassword(temp);
			
			/**/
			
			String sql = "UPDATE `bachelor_3`.`user_` SET `user_password` = '"+encrypted+"' WHERE (`user_id` = '"+userId+"');";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_injectExecute(table_);
				
				resultSetToTableModel(result, user_Table);
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		
	}
	
	protected void search() {
		
		String colName = columnTextField.getText();
		String search = searchTextField.getText();
		
		if(user_Panel.isShowing()) {
			
			String sql = "SELECT * FROM bachelor_3.user_ WHERE user_"+colName+" LIKE '"+search+"%';";
			
			/*String sql = "SELECT user_name AS 'Name', user_userName AS 'Username', user_password AS 'Password' "
					+ "FROM bachelor_3.user_ WHERE user_"+colName+" LIKE '"+search+"%';";*/
			
			Table_ table_temp = new Table_(sql);

			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, user_Table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(bachelor_Panel.isShowing()) {
			
			String sql = "SELECT bachelor_code AS 'Code', bachelor_name AS 'Name', bachelor_age AS 'Age',"
					+ " bachelor_hometown AS 'Hometown', bachelor_season AS 'Season'"
					+ " FROM bachelor_3.bachelor_ WHERE bachelor_"+colName+" LIKE '"+search+"%';";
			
			Table_ table_temp = new Table_(sql);

			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, bachelor_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
		if(contestant_Panel.isShowing()) {
			
			String sql = "SELECT contestant_name AS 'Name', contestant_age AS 'Age', "
					+ "contestant_occupation AS 'Occupation', contestant_hometown AS 'Hometown', "
					+ "contestant_elimWeek AS 'elimWeek', contestant_season AS 'Season', "
					+ "bachelor_bachelorCode AS 'Code' "
					+ "FROM bachelor_3.contestant_ WHERE contestant_"+colName+" LIKE '"+search+"%';";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, contestant_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
	};
	
	public boolean intToBool (int x)
	{
		if ( x != 0 )
			return true;
		else
			return false;
	}
	
	public void user_refresh() {
		
		if(user_admin) {
			
			/*String sql = "SELECT user_name, user_userName, "
					+ "user_password FROM bachelor_3.user_;";*/
			
			String sql = "SELECT * FROM bachelor_3.user_;";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, user_Table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}else if(user_contestant | user_bachelor ) {
			
			/*String sql = "SELECT user_name, user_userName, "
					+ "user_password FROM bachelor_3.user_ WHERE user_id = "+getUserId()+"";*/
			
			String sql = "SELECT * FROM bachelor_3.user_ WHERE user_id = "+getUserId()+"";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, user_Table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		}
		
	}
	
	public void bachelor_refresh() {
		
		if(user_admin) {
			
			/*String sql = "SELECT bachelor_code, bachelor_name, "
					+ "bachelor_age, bachelor_hometown, bachelor_season "
					+ "FROM bachelor_3.bachelor_;" ;*/
			
			String sql = "SELECT * FROM bachelor_3.bachelor_;" ;
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, bachelor_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		} else if (user_bachelor) {
			
			/*String sql = "SELECT bachelor_code, bachelor_name, "
					+ "bachelor_age, bachelor_hometown, bachelor_season "
					+ "FROM bachelor_3.bachelor_ WHERE user_userId = "+getUserId()+";" ;*/
			
			String sql = "SELECT * FROM bachelor_3.bachelor_ WHERE user_userId = "+getUserId()+";" ;
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, bachelor_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		} 
		
	}
	
	public void contestant_refresh() {
		
		if(user_admin) {
			
			/*String sql = "SELECT contestant_name, contestant_age, "
					+ "contestant_occupation, contestant_hometown, "
					+ "contestant_elimWeek, contestant_season, "
					+ "bachelor_bachelorCode "
					+ "FROM bachelor_3.contestant_; ";*/
			
			String sql = "SELECT * FROM bachelor_3.contestant_;";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, contestant_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		} else if (user_bachelor) {
			
			/*String sql = "SELECT contestant_name, contestant_age, "
					+ "contestant_occupation, contestant_hometown, "
					+ "contestant_elimWeek, contestant_season, "
					+ "bachelor_bachelorCode "
					+ "FROM bachelor_3.contestant_ WHERE bachelor_bachelorCode = "
					+ "(SELECT bachelor_code FROM bachelor_3.bachelor_ WHERE user_userId = "+getUserId()+");";*/
			
			String sql = "SELECT * FROM bachelor_3.contestant_ WHERE bachelor_bachelorCode = "
					+ "(SELECT bachelor_code FROM bachelor_3.bachelor_ WHERE user_userId = "+getUserId()+");";
			
			Table_ table_temp = new Table_(sql);

			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, contestant_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
			
		} else if (user_contestant) {
			
			/*String sql = "SELECT contestant_name, contestant_age, "
					+ "contestant_occupation, contestant_hometown, "
					+ "contestant_elimWeek, contestant_season, "
					+ "bachelor_bachelorCode "
					+ "FROM bachelor_3.contestant_ WHERE user_userId = '"+getUserId()+"'; ";*/
			
			String sql = "SELECT * FROM bachelor_3.contestant_ WHERE user_userId = '"+getUserId()+"'; ";
			
			Table_ table_temp = new Table_(sql);
			
			try {
				
				String table_ = table_temp.table_get();
				
				ResultSet result = table_DAO.table_inject(table_);
				
				resultSetToTableModel(result, contestant_table);
				
				
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
			}
		}
			
	}
	
	public void bachelor_add(String code, String name, String age, String hometown, String season, String id) {
		
		String sql = "INSERT INTO `bachelor_3`.`bachelor_` (`bachelor_code`, `bachelor_name`, `bachelor_age`, "
				+ "`bachelor_hometown`, `bachelor_season`, `user_userId`) VALUES ('"+code+"', '"+name+"', "
						+ "'"+age+"', '"+hometown+"', '"+season+"', '"+id+"');";
		
		Table_ table_temp = new Table_(sql);

		try {
			
			String table_ = table_temp.table_get();
			
			ResultSet result = table_DAO.table_injectExecute(table_);
			
			resultSetToTableModel(result, bachelor_table);
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
		}
		
	}
	
	public void contestant_add(String Name, String Age, String Occupation, String Hometown, String ElimWeek,
			String Season, String UserId, String BachelorCode) {
		
		String sql = "INSERT INTO `bachelor_3`.`contestant_` (`contestant_name`, `contestant_age`, "
				+ "`contestant_occupation`, `contestant_hometown`, `contestant_elimWeek`, `contestant_season`, "
				+ "`user_userId`, `bachelor_bachelorCode`) VALUES ('"+Name+"', '"+Age+"', '"+Occupation+"',"
						+ " '"+Hometown+"', '"+ElimWeek+"', '"+Season+"', '"+UserId+"', '"+BachelorCode+"');";
		
		Table_ table_temp = new Table_(sql);

		try {
			
			String table_ = table_temp.table_get();
			
			ResultSet result = table_DAO.table_injectExecute(table_);
			
			resultSetToTableModel(result, contestant_table);
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(App.this, "Please Refresh Table", "Ok", JOptionPane.ERROR_MESSAGE); 
		}
		
	}
	
	public String getUserId () {
		return id_Label.getText();
	}
	
	public JPanel getStatementPanel () {
		return statement_Panel;
	}
	
	public JPanel getTablePanel () {
		return table_Panel;
	}
	
	public JButton getCreateButton () {
		return btnCreate;
	}
	
	public JButton getDeleteButton() {
		return btnDelete;
	}

}
