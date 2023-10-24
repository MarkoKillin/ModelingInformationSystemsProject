package gui_users;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.ObavestenjaCrud;
import model.Gost;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenadzerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gost korisnik;
	private ObavestenjaCrud oc = new ObavestenjaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenadzerWindow frame = new MenadzerWindow();
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
	public MenadzerWindow() {
		setTitle("Menadzer Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRoomReservation = new JButton("Rezervacija Sobe");
		btnRoomReservation.setBackground(new Color(255, 255, 255));
		btnRoomReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RezervacijaSobeDijalog rsd = new RezervacijaSobeDijalog();
				rsd.setVisible(true);
				rsd.setKorisnik(korisnik);
				setVisible(false);
			}
		});
		btnRoomReservation.setBounds(93, 35, 200, 25);
		contentPane.add(btnRoomReservation);
		
		JButton btnUsedMaterial = new JButton("Pregled Utrosenog Materijala");
		if(oc.menadzerObavestenje())
			btnUsedMaterial.setBackground(new Color(255, 0, 0));
		else
			btnUsedMaterial.setBackground(new Color(255, 255, 255));
		btnUsedMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PregledUtrosenogMaterijalaDijalog pumd = new PregledUtrosenogMaterijalaDijalog();
				pumd.setVisible(true);
				btnUsedMaterial.setBackground(new Color(255, 255, 255));
				oc.deleteMenadzerObavestenje();
			}
		});
		btnUsedMaterial.setBounds(93, 90, 200, 25);
		contentPane.add(btnUsedMaterial);
	}

	public Gost getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Gost korisnik) {
		this.korisnik = korisnik;
	}

	
}
