package gui_users;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Gost;

public class GostWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gost korisnik;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GostWindow frame = new GostWindow();
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
	public GostWindow() {
		setTitle("Gost Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRoomReservation = new JButton("Rezervacija Sobe");
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
		
		JButton btnReportProblem = new JButton("Prijava Kvara");
		btnReportProblem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaKvaraDialog pkd = new PrijavaKvaraDialog();
				pkd.setVisible(true);
			}
		});
		btnReportProblem.setBounds(93, 90, 200, 25);
		contentPane.add(btnReportProblem);
	}

	public Gost getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Gost korisnik) {
		this.korisnik = korisnik;
	}

	
}
