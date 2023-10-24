package gui_users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Gost;

public class RezervacijaSobeDijalog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfStartRes;
	private JTextField tfEndRes;
	private Gost korisnik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RezervacijaSobeDijalog dialog = new RezervacijaSobeDijalog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RezervacijaSobeDijalog() {
		setTitle("Rezervacija Sobe");
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblStartRes = new JLabel("Pocetak rezervacije:");
		lblStartRes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartRes.setBounds(10, 25, 147, 25);
		contentPanel.add(lblStartRes);
		
		tfStartRes = new JTextField();
		tfStartRes.setToolTipText("dd.mm.yyyy.");
		tfStartRes.setBounds(167, 25, 259, 25);
		contentPanel.add(tfStartRes);
		tfStartRes.setColumns(10);
		
		JLabel lblEndRes = new JLabel("Kraj rezervacije:");
		lblEndRes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEndRes.setBounds(10, 77, 150, 25);
		contentPanel.add(lblEndRes);
		
		tfEndRes = new JTextField();
		tfEndRes.setToolTipText("dd.mm.yyyy.");
		tfEndRes.setColumns(10);
		tfEndRes.setBounds(167, 77, 259, 25);
		contentPanel.add(tfEndRes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Pretrazi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(tfStartRes.getText().equals("") || tfEndRes.getText().equals(""))
							JOptionPane.showMessageDialog(RezervacijaSobeDijalog.this, "Unesite oba datuma", 
								"Neuspesna pretraga", JOptionPane.ERROR_MESSAGE);
						else {							
							RezervisiDijalog rd = new RezervisiDijalog();
							rd.setVisible(true);
							rd.setStartDate(tfStartRes.getText());
							rd.setEndDate(tfEndRes.getText());
							rd.setKorisnik(korisnik);
							setVisible(false);
						}
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

	public Gost getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Gost korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
