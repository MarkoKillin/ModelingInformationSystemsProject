package gui_users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import crud.MaterijalCrud;

public class PregledUtrosenogMaterijalaDijalog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MaterijalCrud mc = new MaterijalCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PregledUtrosenogMaterijalaDijalog dialog = new PregledUtrosenogMaterijalaDijalog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PregledUtrosenogMaterijalaDijalog() {
		setTitle("Materijal");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(10, 45, 416, 120);
			List<String> listMaterijal = mc.allMaterial();
			for (String s : listMaterijal) {
				textArea.append(s + "\n");
			}
			contentPanel.add(textArea);
		}
		{
			JLabel lblListaUtrosenogMaterijala = new JLabel("Lista utrosenog materijala:");
			lblListaUtrosenogMaterijala.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblListaUtrosenogMaterijala.setBounds(10, 10, 178, 25);
			contentPanel.add(lblListaUtrosenogMaterijala);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Poruci");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PorucivanjeMaterijalaDijalog pmd = new PorucivanjeMaterijalaDijalog();
						pmd.setVisible(true);
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
