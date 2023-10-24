package gui_users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import crud.KvarCrud;
import crud.ObavestenjaCrud;
import crud.SobaCrud;

public class PrijavaKvaraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cbSobe;
	private SobaCrud sc = new SobaCrud();
	private KvarCrud kc = new KvarCrud();
	private boolean warrned = false;
	private ObavestenjaCrud oc = new ObavestenjaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrijavaKvaraDialog dialog = new PrijavaKvaraDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PrijavaKvaraDialog() {
		setTitle("Prijava Kvara");
		setBounds(100, 100, 400, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSoba = new JLabel("Soba:");
			lblSoba.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSoba.setBounds(10, 29, 90, 25);
			contentPanel.add(lblSoba);
		}
		{
			cbSobe = new JComboBox<String>();
			List<String> l = sc.allRooms();
			for (String s : l) {
				cbSobe.addItem(s);
			}
			cbSobe.setBounds(127, 29, 249, 25);
			contentPanel.add(cbSobe);
		}
		{
			JLabel lblOpis = new JLabel("Opis:");
			lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblOpis.setBounds(10, 74, 90, 25);
			contentPanel.add(lblOpis);
		}
		
		JTextArea taDescription = new JTextArea();
		taDescription.setBounds(127, 76, 249, 104);
		contentPanel.add(taDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Prijavi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String room = (String) cbSobe.getSelectedItem();
						String description = taDescription.getText();
						if(description.equals("") && !warrned) {
							JOptionPane.showMessageDialog(contentPanel, "Mozete dodati opis radi lakseg otklanjanja kvara", 
								"Nije dodat opis", JOptionPane.QUESTION_MESSAGE);
							warrned = true;
							return;
						}
						if(kc.addError(room, description)) 
							JOptionPane.showMessageDialog(contentPanel, "Uspesno ste prijavili kvar", 
									"Uspesna prijava", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(contentPanel, "Doslo je do greske pri prijavi kvara", 
									"Neuspesna prijava", JOptionPane.ERROR_MESSAGE);
						oc.addDomarObavestenje();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Odustani");
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
}
