package gui_start;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import crud.GostCrud;
import model.Gost;

public class RegisterWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JTextField tfEmail;
	private JTextField tfSurname;
	private JTextField tfName;
	private JTextField tfCellNumber;
	private GostCrud gc = new GostCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterWindow dialog = new RegisterWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisterWindow() {
		setTitle("Register Window");
		setBounds(100, 100, 400, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(130, 190, 246, 25);
		contentPanel.add(tfUsername);
		
		JLabel lblUsername = new JLabel("Korisnicno ime: ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(10, 190, 110, 25);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Lozinka:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 230, 110, 25);
		contentPanel.add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(130, 230, 246, 25);
		contentPanel.add(pfPassword);
		
		JLabel lblEmail = new JLabel("email: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 110, 110, 25);
		contentPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(130, 110, 246, 25);
		contentPanel.add(tfEmail);
		
		JLabel lblSurname = new JLabel("Prezime: ");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(10, 70, 110, 25);
		contentPanel.add(lblSurname);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(130, 70, 246, 25);
		contentPanel.add(tfSurname);
		
		JLabel lblName = new JLabel("Ime: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(10, 30, 110, 25);
		contentPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(130, 30, 246, 25);
		contentPanel.add(tfName);
		
		JLabel lblCellNumber = new JLabel("Broj telefona: ");
		lblCellNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCellNumber.setBounds(10, 150, 110, 25);
		contentPanel.add(lblCellNumber);
		
		tfCellNumber = new JTextField();
		tfCellNumber.setColumns(10);
		tfCellNumber.setBounds(130, 150, 246, 25);
		contentPanel.add(tfCellNumber);
		
		JButton btnRegistrujSe = new JButton("Registruj se");
		btnRegistrujSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String surname = tfSurname.getText();
				String cellNumber = tfCellNumber.getText();
				String email = tfEmail.getText();
				String username = tfUsername.getText();
				String password = new String(pfPassword.getPassword());
				Gost g = new Gost(name, surname, cellNumber, email, username, password, "g");
				if(gc.addGost(g))
					JOptionPane.showMessageDialog(RegisterWindow.this, "Uspesno ste kreirali nalog", 
						"Uspesna registracija", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(RegisterWindow.this, "Doslo je do greske pri registraciji", 
						"Neuspesna registracija", JOptionPane.ERROR_MESSAGE);
				setVisible(false);
			}
		});
		btnRegistrujSe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrujSe.setBounds(10, 298, 125, 25);
		contentPanel.add(btnRegistrujSe);
		
		JButton btnCancel = new JButton("Odustani");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(251, 298, 125, 25);
		contentPanel.add(btnCancel);
	}

}
