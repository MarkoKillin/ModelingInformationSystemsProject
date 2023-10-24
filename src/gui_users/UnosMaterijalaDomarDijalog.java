package gui_users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import crud.KvarCrud;
import crud.MaterijalCrud;
import crud.ObavestenjaCrud;

public class UnosMaterijalaDomarDijalog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfKolicina;
	private KvarCrud kc = new KvarCrud();
	private JTextField tfMaterijal;
	private List<String> materijali = new ArrayList<>();
	private MaterijalCrud mc = new MaterijalCrud();
	private ObavestenjaCrud oc = new ObavestenjaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UnosMaterijalaDomarDijalog dialog = new UnosMaterijalaDomarDijalog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UnosMaterijalaDomarDijalog() {
		setTitle("Unos Utrosenog Materijala Domar");
		setBounds(100, 100, 400, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Materijal:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 75, 90, 25);
		contentPanel.add(lblNewLabel);
		
		JLabel lblKolicina = new JLabel("Kolicina:");
		lblKolicina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKolicina.setBounds(10, 120, 90, 25);
		contentPanel.add(lblKolicina);
		
		tfKolicina = new JTextField();
		tfKolicina.setBounds(127, 120, 249, 25);
		contentPanel.add(tfKolicina);
		tfKolicina.setColumns(10);
		
		JLabel lblKvar = new JLabel("Kvar:");
		lblKvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKvar.setBounds(10, 30, 90, 25);
		contentPanel.add(lblKvar);
		
		JComboBox<String> cbErrors = new JComboBox<String>();
		List<String> lError = kc.allErrors();
		for (String s : lError) {
			cbErrors.addItem(s);
		}
		cbErrors.setBounds(127, 30, 249, 25);
		contentPanel.add(cbErrors);
		
		JCheckBox checkOtkljonjen = new JCheckBox("Otklonjen");
		checkOtkljonjen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkOtkljonjen.setBounds(10, 210, 93, 21);
		contentPanel.add(checkOtkljonjen);
		
		JLabel lblSpisak = new JLabel("Spisak:");
		lblSpisak.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpisak.setBounds(10, 165, 90, 25);
		contentPanel.add(lblSpisak);
		
		JTextArea taSpisak = new JTextArea();
		taSpisak.setEditable(false);
		taSpisak.setBounds(127, 165, 249, 117);
		contentPanel.add(taSpisak);
		
		tfMaterijal = new JTextField();
		tfMaterijal.setColumns(10);
		tfMaterijal.setBounds(127, 75, 249, 25);
		contentPanel.add(tfMaterijal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Unesi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mc.addMaterials(materijali);
						if(checkOtkljonjen.isSelected()) {
							if(kc.deleteError((String)cbErrors.getSelectedItem()))
								JOptionPane.showMessageDialog(contentPanel, "Uspesno ste obrisali kvar", 
										"Uspesno brisanje kvara", JOptionPane.INFORMATION_MESSAGE);
							else 
								JOptionPane.showMessageDialog(contentPanel, "Doslo je do greske pri brisanju kvara", 
										"Neuspesno brisanje kvara", JOptionPane.INFORMATION_MESSAGE);
						}
						JOptionPane.showMessageDialog(contentPanel, "Uspesno ste dodali materijal", 
								"Uspesno dodavanje", JOptionPane.INFORMATION_MESSAGE);
						oc.addMenadzerObavestenje();
					}
				});
				
				JButton btnDodajMaterijal = new JButton("Dodaj Materijal");
				btnDodajMaterijal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String m = tfMaterijal.getText();
						String k = tfKolicina.getText();
						String s = m + " | " + k;
						taSpisak.append(s + "\n");
						materijali.add(s);
						
					}
				});
				btnDodajMaterijal.setActionCommand("OK");
				buttonPane.add(btnDodajMaterijal);
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
