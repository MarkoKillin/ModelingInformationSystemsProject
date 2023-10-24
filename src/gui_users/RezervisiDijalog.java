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

import crud.SobaCrud;
import gui_start.LogInWindow;
import model.Gost;

public class RezervisiDijalog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Gost korisnik;
	private String startDate;
	private String endDate;
	private SobaCrud sc = new SobaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RezervisiDijalog dialog = new RezervisiDijalog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RezervisiDijalog() {
		setTitle("Prikaz sobe");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSoba = new JLabel("Soba:");
		lblSoba.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoba.setBounds(10, 20, 112, 25);
		contentPanel.add(lblSoba);
		
		JComboBox<String> cbRoom = new JComboBox<String>();
		List<String> listRooms = sc.allFreeRooms();
		for (String s : listRooms) {
			cbRoom.addItem(s);
		}
		cbRoom.setBounds(127, 20, 299, 25);
		contentPanel.add(cbRoom);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(127, 55, 299, 120);
		contentPanel.add(textArea);
		
		JLabel lblTipRezervacije = new JLabel("Tip rezervacije:");
		lblTipRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipRezervacije.setBounds(10, 185, 112, 25);
		contentPanel.add(lblTipRezervacije);
		
		JComboBox<String> cbType = new JComboBox<String>();
		List<String> listTypes = sc.allTypes();
		for (String s : listTypes) {
			cbType.addItem(s);
		}
		cbType.setBounds(127, 185, 299, 25);
		contentPanel.add(cbType);
		
		JLabel lblDetaljiOSobi = new JLabel("Detalji o sobi:");
		lblDetaljiOSobi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDetaljiOSobi.setBounds(10, 55, 112, 25);
		contentPanel.add(lblDetaljiOSobi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRezervisi = new JButton("Rezervisi");
			btnRezervisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(korisnik != null) {
						String room = (String) cbRoom.getSelectedItem();
						String type = (String) cbType.getSelectedItem();
						sc.reserve(room, korisnik, startDate, endDate, type);
						JOptionPane.showMessageDialog(contentPanel, "Uspesno ste rezervisali sobu", 
								"Uspesna rezervacija", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(contentPanel, "Niste ulogovani, molimo Vas da se ulogujete", 
								"Potreban nalog", JOptionPane.WARNING_MESSAGE);
						setVisible(false);
						LogInWindow window = new LogInWindow();
						window.frmLoginWindow.setVisible(true);
					}
				}
			});
			btnRezervisi.setActionCommand("OK");
			buttonPane.add(btnRezervisi);
			{
				JButton okButton = new JButton("Prikazi");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText(null);
						List<String> listDetails = sc.details((String)cbRoom.getSelectedItem());
						for (String s : listDetails) {
							textArea.append(s + "\n");
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
