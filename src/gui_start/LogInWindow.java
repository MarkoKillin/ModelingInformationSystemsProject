package gui_start;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.GostCrud;
import gui_users.DomarWindow;
import gui_users.GostWindow;
import gui_users.MenadzerWindow;
import gui_users.RezervacijaSobeDijalog;
import model.Gost;

/*
 * dodati da svaki korisnik koristi model, tj da u naslovu prozora pise ime korisnika
 */
public class LogInWindow {

	public JFrame frmLoginWindow;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private GostCrud gc = new GostCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInWindow window = new LogInWindow();
					window.frmLoginWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogInWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginWindow = new JFrame();
		frmLoginWindow.setTitle("LogIn Window");
		frmLoginWindow.setBounds(100, 100, 480, 220);
		frmLoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginWindow.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Korisnicno ime: ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(10, 30, 110, 25);
		frmLoginWindow.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Lozinka:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 77, 110, 25);
		frmLoginWindow.getContentPane().add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(130, 30, 330, 25);
		frmLoginWindow.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(130, 77, 330, 25);
		frmLoginWindow.getContentPane().add(pfPassword);
		
		JButton btnRegister = new JButton("Registruj se");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow rw = new RegisterWindow();
				rw.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBounds(330, 135, 125, 25);
		frmLoginWindow.getContentPane().add(btnRegister);
		
		JButton btnLogin = new JButton("Uloguj se");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				String password = new String(pfPassword.getPassword());
				Gost g = gc.login(username, password);
				if(g == null) {
					JOptionPane.showMessageDialog(frmLoginWindow, "Doslo je do greske pri prijavljivanju", 
						"Neuspesna prijava", JOptionPane.ERROR_MESSAGE);
				} else if(g.getType().equals("g")) {
					JOptionPane.showMessageDialog(frmLoginWindow, "Uspesno ste se prijavili, dobro dosli " + g.getName(), 
						"Uspesna prijava (gost)", JOptionPane.INFORMATION_MESSAGE);
					frmLoginWindow.dispose();
					GostWindow gw = new GostWindow();
					gw.setKorisnik(g);
					gw.setVisible(true);
					
				} else if(g.getType().equals("m")) {
					JOptionPane.showMessageDialog(frmLoginWindow, "Uspesno ste se prijavili, dobro dosli " + g.getName(), 
						"Uspesna prijava (menadzer)", JOptionPane.INFORMATION_MESSAGE);
					frmLoginWindow.dispose();
					MenadzerWindow mw = new MenadzerWindow();
					mw.setKorisnik(g);
					mw.setVisible(true);
					
				} else if(g.getType().equals("d")) {
					JOptionPane.showMessageDialog(frmLoginWindow, "Uspesno ste se prijavili, dobro dosli " + g.getName(), 
						"Uspesna prijava (domar)", JOptionPane.INFORMATION_MESSAGE);
					frmLoginWindow.dispose();
					DomarWindow dw = new DomarWindow();
					dw.setKorisnik(g);
					dw.setVisible(true);
					
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(10, 135, 125, 25);
		frmLoginWindow.getContentPane().add(btnLogin);
		
		JButton btnShowRooms = new JButton("Pregled soba");
		btnShowRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RezervacijaSobeDijalog rsd = new RezervacijaSobeDijalog();
				rsd.setVisible(true);
				rsd.setKorisnik(null);
				frmLoginWindow.dispose();
			}
		});
		btnShowRooms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShowRooms.setBounds(170, 135, 125, 25);
		frmLoginWindow.getContentPane().add(btnShowRooms);
	}
}
