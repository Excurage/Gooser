package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import loaders.CsvLoader;
import loaders.DatabaseLoader;
import loaders.Loader;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GooserLoadDialogue extends JDialog {

	public boolean isOK = false;
	
	private final JPanel contentPanel = new JPanel();
	public JTextField txtFileLocation;
	public JComboBox comboLoadSource;
	public JLabel lblLoadSource;
	public JLabel lblFileLocation;
	public JButton btnBrowse;
	public JLabel lblDriver;
	public JLabel lblUrl;
	public JLabel lblTable;
	public JTextField txtURL;
	public JTextField txtTable;
	public JLabel lblDriverValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GooserLoadDialogue dialog = new GooserLoadDialogue();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GooserLoadDialogue() {
		setBounds(100, 100, 288, 273);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			comboLoadSource = new JComboBox();
			comboLoadSource.addItem("CSV");
			comboLoadSource.addItem("Database");
			comboLoadSource.setSelectedIndex(0);
			comboLoadSource.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_comboLoadSource_actionPerformed(arg0);
				}
			});
			comboLoadSource.setBounds(95, 11, 106, 20);
			contentPanel.add(comboLoadSource);
		}
		{
			lblLoadSource = new JLabel("Load Source");
			lblLoadSource.setBounds(10, 14, 75, 14);
			contentPanel.add(lblLoadSource);
		}
		{
			lblFileLocation = new JLabel("File Location");
			lblFileLocation.setBounds(10, 39, 75, 14);
			contentPanel.add(lblFileLocation);
		}
		{
			txtFileLocation = new JTextField();
			txtFileLocation.setBounds(95, 36, 167, 20);
			contentPanel.add(txtFileLocation);
			txtFileLocation.setColumns(10);
		}
		{
			btnBrowse = new JButton("Browse");
			btnBrowse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					do_btnBrowse_mouseReleased(arg0);
				}
			});
			btnBrowse.setBounds(95, 62, 89, 23);
			contentPanel.add(btnBrowse);
		}
		{
			lblDriver = new JLabel("Driver");
			lblDriver.setBounds(10, 98, 46, 14);
			contentPanel.add(lblDriver);
		}
		{
			lblUrl = new JLabel("URL");
			lblUrl.setBounds(10, 123, 46, 14);
			contentPanel.add(lblUrl);
		}
		{
			lblTable = new JLabel("Table");
			lblTable.setBounds(10, 148, 46, 14);
			contentPanel.add(lblTable);
		}
		{
			txtURL = new JTextField();
			txtURL.setText("jdbc:mysql://localhost/test");
			txtURL.setBounds(95, 120, 167, 20);
			contentPanel.add(txtURL);
			txtURL.setColumns(10);
		}
		{
			txtTable = new JTextField();
			txtTable.setBounds(95, 145, 86, 20);
			contentPanel.add(txtTable);
			txtTable.setColumns(10);
		}
		
		lblDriverValue = new JLabel("org.gjt.mm.mysql.Driver");
		lblDriverValue.setBounds(95, 98, 167, 14);
		contentPanel.add(lblDriverValue);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						do_okButton_mouseReleased(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						do_cancelButton_mouseReleased(e);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		lblDriver.setVisible(false);
		lblUrl.setVisible(false);
		lblTable.setVisible(false);
		lblDriverValue.setVisible(false);
		txtURL.setVisible(false);
		txtTable.setVisible(false);
		
	}
	
	public Loader CreateLoader()
	{
		
		String sourceStr = comboLoadSource.getSelectedItem().toString();
		Loader newLoader;
		
		if(sourceStr == "Database")
		{
			newLoader = new DatabaseLoader(lblDriverValue.getText(),
					                       txtURL.getText(),
					                       "SELECT * FROM " + txtTable.getText());
		}
		else//(sourceStr == "CSV")
		{
			newLoader = new CsvLoader(txtFileLocation.getText());
		}
		
		return newLoader;
	}

	protected void do_okButton_mouseReleased(MouseEvent e) {
		isOK = false;
		String sourceStr = comboLoadSource.getSelectedItem().toString();
		if(sourceStr == "Database")
		{
			if(txtURL.getText() != "" &&
			   txtTable.getText() != "");
			{
				isOK = true;
			}
		}
		else if(sourceStr == "CSV")
		{
			if(txtFileLocation.getText() == "")
			{
				isOK = false;
			}
			else
			{
				File f = new File(txtFileLocation.getText());
				if(f.exists() && !f.isDirectory()) { 
				    isOK = true;
				    this.setVisible(false);
				}
			}
		}
		
	}
	protected void do_cancelButton_mouseReleased(MouseEvent e) {
		isOK = false;
		this.setVisible(false);
	}
	protected void do_comboLoadSource_actionPerformed(ActionEvent arg0) {
		String comboTxt = comboLoadSource.getSelectedItem().toString();
		if(comboTxt == "Database")
		{
			
			lblDriver.setVisible(true);
			lblUrl.setVisible(true);
			lblTable.setVisible(true);
			lblDriverValue.setVisible(true);
			txtURL.setVisible(true);
			txtTable.setVisible(true);
			
			txtFileLocation.setVisible(false);
			lblFileLocation.setVisible(false);
			btnBrowse.setVisible(false);
		}
		else if(comboTxt == "CSV")
		{
			txtFileLocation.setVisible(true);
			lblFileLocation.setVisible(true);
			btnBrowse.setVisible(true);
			
			lblDriver.setVisible(false);
			lblUrl.setVisible(false);
			lblTable.setVisible(false);
			lblDriverValue.setVisible(false);
			txtURL.setVisible(false);
			txtTable.setVisible(false);
		}
	}
	protected void do_btnBrowse_mouseReleased(MouseEvent arg0) {
		int retval = JFileChooser.CANCEL_OPTION;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter extFilter = new FileNameExtensionFilter("CSV Files", "csv");
		fileChooser.setFileFilter(extFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		retval = fileChooser.showOpenDialog(this);
		if(retval == JFileChooser.APPROVE_OPTION)
		{
			txtFileLocation.setText(fileChooser.getSelectedFile().getPath());
		}
	}
}
