package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import basics.Handler;

public class PanelLogIn extends JPanel {

	private Handler handler;
	JLabel lblUsuario = new JLabel("Usuario:");
	JLabel lblPassword = new JLabel("Password:");
	final JTextField textFieldUsuario = new JTextField(10);
	final JPasswordField textFieldPassword = new JPasswordField(10);
	JButton btnLogIn = new JButton("Log in");

	public PanelLogIn(Handler handler) {
		this.handler = handler;
		init();
	}

	public void init() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel.add(lblUsuario);
		panel.add(textFieldUsuario);
		panel.add(lblPassword);
		panel.add(textFieldPassword);
		panel.add(btnLogIn);

		add(panel);
		definirEventos();

	}

	public void definirEventos() {
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCampos()) {
					JOptionPane.showMessageDialog(null,
							"Debe ingresar un usuario y una contraseña",
							"Error", JOptionPane.OK_OPTION);
				} else {
					handler.logInAdministrator(textFieldUsuario.getText(),
							textFieldPassword.getText());
				}
			}
		});

	}

	public boolean validarCampos() {
		if (textFieldUsuario.getText().isEmpty()
				|| textFieldPassword.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}