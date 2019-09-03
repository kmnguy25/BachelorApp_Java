package ui.bachelor_;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Table_;
import core.User_;
import dao.User_DAO;
import ui.App;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bachelor_Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField code_textField;
	private JTextField name_textField;
	private JTextField age_textField;
	private JTextField hometown_textField;
	private JTextField season_textField;
	
	private App app;

	private boolean user_updateMode= false;
	
	private String code = "";
	private String name = "";
	private String age = "";
	private String hometown = "";
	private String season = "";
	
	/**
	 * Create the dialog.
	 */
	
	public Bachelor_Dialog(App bachelor_app, boolean theUpdateMode) {
		
		this();
		
		app = bachelor_app;
		user_updateMode = theUpdateMode;
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Bachelor_Dialog dialog = new Bachelor_Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Bachelor_Dialog() {
	
		setTitle("Register Bachelor");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Code");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			code_textField = new JTextField();
			GridBagConstraints gbc_code_textField = new GridBagConstraints();
			gbc_code_textField.insets = new Insets(0, 0, 5, 0);
			gbc_code_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_code_textField.gridx = 1;
			gbc_code_textField.gridy = 0;
			contentPanel.add(code_textField, gbc_code_textField);
			code_textField.setColumns(10);
		}
		{
			JLabel lblName_1 = new JLabel("Name");
			GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
			gbc_lblName_1.anchor = GridBagConstraints.EAST;
			gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblName_1.gridx = 0;
			gbc_lblName_1.gridy = 1;
			contentPanel.add(lblName_1, gbc_lblName_1);
		}
		{
			name_textField = new JTextField();
			GridBagConstraints gbc_name_textField = new GridBagConstraints();
			gbc_name_textField.insets = new Insets(0, 0, 5, 0);
			gbc_name_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_name_textField.gridx = 1;
			gbc_name_textField.gridy = 1;
			contentPanel.add(name_textField, gbc_name_textField);
			name_textField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Age");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			age_textField = new JTextField();
			GridBagConstraints gbc_age_textField = new GridBagConstraints();
			gbc_age_textField.insets = new Insets(0, 0, 5, 0);
			gbc_age_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_age_textField.gridx = 1;
			gbc_age_textField.gridy = 2;
			contentPanel.add(age_textField, gbc_age_textField);
			age_textField.setColumns(10);
		}
		{
			JLabel lblHometown = new JLabel("Hometown");
			GridBagConstraints gbc_lblHometown = new GridBagConstraints();
			gbc_lblHometown.anchor = GridBagConstraints.EAST;
			gbc_lblHometown.insets = new Insets(0, 0, 5, 5);
			gbc_lblHometown.gridx = 0;
			gbc_lblHometown.gridy = 3;
			contentPanel.add(lblHometown, gbc_lblHometown);
		}
		{
			hometown_textField = new JTextField();
			GridBagConstraints gbc_hometown_textField = new GridBagConstraints();
			gbc_hometown_textField.insets = new Insets(0, 0, 5, 0);
			gbc_hometown_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_hometown_textField.gridx = 1;
			gbc_hometown_textField.gridy = 3;
			contentPanel.add(hometown_textField, gbc_hometown_textField);
			hometown_textField.setColumns(10);
		}
		{
			JLabel lblSeason = new JLabel("Season");
			GridBagConstraints gbc_lblSeason = new GridBagConstraints();
			gbc_lblSeason.anchor = GridBagConstraints.EAST;
			gbc_lblSeason.insets = new Insets(0, 0, 0, 5);
			gbc_lblSeason.gridx = 0;
			gbc_lblSeason.gridy = 4;
			contentPanel.add(lblSeason, gbc_lblSeason);
		}
		{
			season_textField = new JTextField();
			GridBagConstraints gbc_season_textField = new GridBagConstraints();
			gbc_season_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_season_textField.gridx = 1;
			gbc_season_textField.gridy = 4;
			contentPanel.add(season_textField, gbc_season_textField);
			season_textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						bachelor_save();
						app.bachelor_add(code, name, age, hometown, season, app.getUserId());
						setVisible(false);
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
	
	private void bachelor_save() {

		code = code_textField.getText();
		name = name_textField.getText();
		age = age_textField.getText();
		hometown = hometown_textField.getText();
		season = season_textField.getText();				
	}
	
	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}
	
	public String getAge()
	{
		return age;
	}
	
	public String getHometown()
	{
		return hometown;
	}
	
	public String getSeason()
	{
		return season;
	}
}
