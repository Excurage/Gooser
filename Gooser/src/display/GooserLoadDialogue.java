package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loaders.CsvLoader;
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
			btnBrowse.setBounds(95, 62, 89, 23);
			contentPanel.add(btnBrowse);
		}
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
	}
	
	public Loader CreateLoader()
	{
		return new CsvLoader("test");
	}

	protected void do_okButton_mouseReleased(MouseEvent e) {
		isOK = false;
		String sourceStr = comboLoadSource.getSelectedItem().toString();
		if(sourceStr == "Database")
		{
			
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
			txtFileLocation.setVisible(false);
			lblFileLocation.setVisible(false);
			btnBrowse.setVisible(false);
		}
		else if(comboTxt == "CSV")
		{
			txtFileLocation.setVisible(true);
			lblFileLocation.setVisible(true);
			btnBrowse.setVisible(true);
		}
	}
}
