package gui_users;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.ObavestenjaCrud;
import model.Gost;

public class DomarWindow extends JFrame {

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
					DomarWindow frame = new DomarWindow();
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
	public DomarWindow() {
		setTitle("Domar Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInputMaterijal = new JButton("Pregled Kvarova");
		if(oc.domarObavestenje())
			btnInputMaterijal.setBackground(new Color(255, 0, 0));
		else 
			btnInputMaterijal.setBackground(new Color(255, 255, 255));
		btnInputMaterijal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosMaterijalaDomarDijalog umdd = new UnosMaterijalaDomarDijalog();
				umdd.setVisible(true);
				btnInputMaterijal.setBackground(new Color(255, 255, 255));
				oc.deleteDomarObavestenje();
			}
		});
		btnInputMaterijal.setBounds(93, 35, 200, 25);
		contentPane.add(btnInputMaterijal);
		
		JButton btnProblemReport = new JButton("Prijava Kvara");
		btnProblemReport.setBackground(new Color(255, 255, 255));
		btnProblemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrijavaKvaraDialog pkd = new PrijavaKvaraDialog();
				pkd.setVisible(true);
			}
		});
		btnProblemReport.setBounds(93, 80, 200, 25);
		contentPane.add(btnProblemReport);
	}

	public Gost getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Gost korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
