package display;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loaders.Loader;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class GooserMainDisplay extends JFrame {

	Loader gooserLoader;
	GooserLoadDialogue gooserLoadDialog;
	
	private JPanel contentPane;
	public JPanel panelFunction;
	public JButton btnQuit;
	public JButton btnLoad;
	public JButton btnDraw;
	public JTextField textLevels;
	public JLabel lblLevels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GooserMainDisplay frame = new GooserMainDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GooserMainDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelFunction = new JPanel();
		panelFunction.setBounds(5, 5, 99, 566);
		contentPane.add(panelFunction);
		panelFunction.setLayout(null);
		
		btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				do_btnQuit_mouseReleased(e);
			}
		});
		btnQuit.setBounds(0, 532, 99, 23);
		panelFunction.add(btnQuit);
		
		btnLoad = new JButton("Load Data");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				do_btnLoad_mouseReleased(e);
			}
		});
		btnLoad.setBounds(0, 11, 99, 23);
		panelFunction.add(btnLoad);
		
		btnDraw = new JButton("Draw");
		btnDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				do_btnDraw_mouseReleased(e);
			}
		});
		btnDraw.setBounds(0, 108, 99, 23);
		panelFunction.add(btnDraw);
		
		textLevels = new JTextField();
		textLevels.setBounds(55, 142, 44, 20);
		panelFunction.add(textLevels);
		textLevels.setColumns(10);
		
		lblLevels = new JLabel("Levels");
		lblLevels.setBounds(10, 145, 40, 14);
		panelFunction.add(lblLevels);
	}

	protected void do_btnQuit_mouseReleased(MouseEvent e) {
		System.exit(0);
	}
	protected void do_btnLoad_mouseReleased(MouseEvent e) {
		gooserLoadDialog = new GooserLoadDialogue();
		gooserLoadDialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		gooserLoadDialog.setModal(true);
		gooserLoadDialog.setVisible(true);
		if(gooserLoadDialog.isOK)
		{
			GetLoader();
		}		
		gooserLoadDialog.dispose();
	}
	protected void do_btnDraw_mouseReleased(MouseEvent e) {
		
	}
	
	public void GetLoader()
	{
		gooserLoader = gooserLoadDialog.CreateLoader();
	}
	
}
