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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import crud.MaterijalCrud;

public class PorucivanjeMaterijalaDijalog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private MaterijalCrud mc = new MaterijalCrud();
	private JComboBox<String> cbMaterijal;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PorucivanjeMaterijalaDijalog dialog = new PorucivanjeMaterijalaDijalog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PorucivanjeMaterijalaDijalog() {
		setTitle("Porucivanje Materijala");
		setBounds(100, 100, 450, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMaterijal = new JLabel("Materijal:");
			lblMaterijal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblMaterijal.setBounds(10, 30, 112, 25);
			contentPanel.add(lblMaterijal);
		}
		{
			cbMaterijal = new JComboBox<String>();
			List<String> listMaterijal = mc.allMaterial();
			for (String s : listMaterijal) {
				cbMaterijal.addItem(s);
			}
			cbMaterijal.setBounds(127, 30, 299, 25);
			contentPanel.add(cbMaterijal);
		}
		{
			JLabel lblKolicina = new JLabel("Kolicina:");
			lblKolicina.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblKolicina.setBounds(10, 65, 112, 25);
			contentPanel.add(lblKolicina);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(127, 65, 299, 25);
			contentPanel.add(textField);
		}
		{
			JLabel lblSpisak = new JLabel("Spisak:");
			lblSpisak.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSpisak.setBounds(10, 100, 112, 25);
			contentPanel.add(lblSpisak);
		}
		{
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(127, 100, 299, 120);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDodajMaterijal = new JButton("Dodaj Materijal");
				btnDodajMaterijal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String materijal = (String) cbMaterijal.getSelectedItem();
						String[] mat = materijal.split(" | ");
						String kolicina = textField.getText();
						textArea.append(mat[0] + " | " + kolicina + "\n");
					}
				});
				btnDodajMaterijal.setActionCommand("OK");
				buttonPane.add(btnDodajMaterijal);
			}
			{
				JButton okButton = new JButton("Poruci");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(mc.order(textArea.getText()))
							JOptionPane.showMessageDialog(contentPanel, "Uspesno ste porucili materijal", 
									"Uspesna porudzbina", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(contentPanel, "Neuspesna porudzbina materijala", 
									"Neuspesna porudzbina", JOptionPane.INFORMATION_MESSAGE);
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
