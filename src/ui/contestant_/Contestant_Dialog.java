package ui.contestant_;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.App;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Contestant_Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name_textField;
	private JTextField age_textField;
	private JTextField occupation_textField;
	private JTextField hometown_textField;
	private JTextField elimWeek_textField;
	private JTextField season_textField;
	private JTextField code_textField;
	
	private App app;
	
	private boolean user_updateMode= false;
		
	private String name = "";
	private String age = "";
	private String occupation = "";
	private String hometown = "";
	private String elimWeek = "";		
	private String season = "";
	private String code = "";
	
	public Contestant_Dialog(App contestant_app, boolean theUpdateMode) {
		
		this();
		
		app = contestant_app;

		user_updateMode = theUpdateMode;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Contestant_Dialog dialog = new Contestant_Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Contestant_Dialog() {
		setTitle("Register Contestant");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			name_textField = new JTextField();
			GridBagConstraints gbc_name_textField = new GridBagConstraints();
			gbc_name_textField.insets = new Insets(0, 0, 5, 0);
			gbc_name_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_name_textField.gridx = 1;
			gbc_name_textField.gridy = 0;
			contentPanel.add(name_textField, gbc_name_textField);
			name_textField.setColumns(10);
		}
		{
			JLabel lblAge = new JLabel("Age");
			GridBagConstraints gbc_lblAge = new GridBagConstraints();
			gbc_lblAge.anchor = GridBagConstraints.EAST;
			gbc_lblAge.insets = new Insets(0, 0, 5, 5);
			gbc_lblAge.gridx = 0;
			gbc_lblAge.gridy = 1;
			contentPanel.add(lblAge, gbc_lblAge);
		}
		{
			age_textField = new JTextField();
			GridBagConstraints gbc_age_textField = new GridBagConstraints();
			gbc_age_textField.insets = new Insets(0, 0, 5, 0);
			gbc_age_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_age_textField.gridx = 1;
			gbc_age_textField.gridy = 1;
			contentPanel.add(age_textField, gbc_age_textField);
			age_textField.setColumns(10);
		}
		{
			JLabel lblOccupation = new JLabel("Occupation");
			GridBagConstraints gbc_lblOccupation = new GridBagConstraints();
			gbc_lblOccupation.anchor = GridBagConstraints.EAST;
			gbc_lblOccupation.insets = new Insets(0, 0, 5, 5);
			gbc_lblOccupation.gridx = 0;
			gbc_lblOccupation.gridy = 2;
			contentPanel.add(lblOccupation, gbc_lblOccupation);
		}
		{
			occupation_textField = new JTextField();
			GridBagConstraints gbc_occupation_textField = new GridBagConstraints();
			gbc_occupation_textField.insets = new Insets(0, 0, 5, 0);
			gbc_occupation_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_occupation_textField.gridx = 1;
			gbc_occupation_textField.gridy = 2;
			contentPanel.add(occupation_textField, gbc_occupation_textField);
			occupation_textField.setColumns(10);
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
			JLabel lblEliminated = new JLabel("Eliminated Week");
			GridBagConstraints gbc_lblEliminated = new GridBagConstraints();
			gbc_lblEliminated.anchor = GridBagConstraints.EAST;
			gbc_lblEliminated.insets = new Insets(0, 0, 5, 5);
			gbc_lblEliminated.gridx = 0;
			gbc_lblEliminated.gridy = 4;
			contentPanel.add(lblEliminated, gbc_lblEliminated);
		}
		{
			elimWeek_textField = new JTextField();
			GridBagConstraints gbc_elimWeek_textField = new GridBagConstraints();
			gbc_elimWeek_textField.insets = new Insets(0, 0, 5, 0);
			gbc_elimWeek_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_elimWeek_textField.gridx = 1;
			gbc_elimWeek_textField.gridy = 4;
			contentPanel.add(elimWeek_textField, gbc_elimWeek_textField);
			elimWeek_textField.setColumns(10);
		}
		{
			JLabel lblSeason = new JLabel("Season");
			GridBagConstraints gbc_lblSeason = new GridBagConstraints();
			gbc_lblSeason.anchor = GridBagConstraints.EAST;
			gbc_lblSeason.insets = new Insets(0, 0, 5, 5);
			gbc_lblSeason.gridx = 0;
			gbc_lblSeason.gridy = 5;
			contentPanel.add(lblSeason, gbc_lblSeason);
		}
		{
			season_textField = new JTextField();
			GridBagConstraints gbc_season_textField = new GridBagConstraints();
			gbc_season_textField.insets = new Insets(0, 0, 5, 0);
			gbc_season_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_season_textField.gridx = 1;
			gbc_season_textField.gridy = 5;
			contentPanel.add(season_textField, gbc_season_textField);
			season_textField.setColumns(10);
		}
		{
			JLabel lblBachelorCode = new JLabel("Bachelor Code");
			GridBagConstraints gbc_lblBachelorCode = new GridBagConstraints();
			gbc_lblBachelorCode.anchor = GridBagConstraints.EAST;
			gbc_lblBachelorCode.insets = new Insets(0, 0, 0, 5);
			gbc_lblBachelorCode.gridx = 0;
			gbc_lblBachelorCode.gridy = 6;
			contentPanel.add(lblBachelorCode, gbc_lblBachelorCode);
		}
		{
			code_textField = new JTextField();
			GridBagConstraints gbc_code_textField = new GridBagConstraints();
			gbc_code_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_code_textField.gridx = 1;
			gbc_code_textField.gridy = 6;
			contentPanel.add(code_textField, gbc_code_textField);
			code_textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						contestant_save();
						app.contestant_add(name, age, occupation, hometown, elimWeek, season, app.getUserId(), code);
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
	
	private void contestant_save() {
		
		name = name_textField.getText();
		age = age_textField.getText();
		occupation = occupation_textField.getText();
		hometown = hometown_textField.getText();
		elimWeek = elimWeek_textField.getText();		
		season = season_textField.getText();
		code = code_textField.getText();
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAge()
	{
		return age;
	}
	
	public String getOccupation()
	{
		return occupation;
	}
	
	public String getHometown()
	{
		return hometown;
	}
	
	public String getElimWeek()
	{
		return elimWeek;
	}
	
	public String getSeason()
	{
		return season;
	}
	
	public String getCode()
	{
		return code;
	}

}
