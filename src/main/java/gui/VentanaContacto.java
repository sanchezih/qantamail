package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tableModel.TableModelContacto;
import validaciones.ValidadorEmail;
import bo.BOException;
import entidades.Contacto;

public class VentanaContacto extends VentanaPlantilla {
	private ValidadorEmail validadorEmail = new ValidadorEmail();
	private int nroFila;

	JButton btnGuardar = new JButton("Guardar");

	private String idContacto = "";
	private JTextField textFieldNombre = new JTextField();;
	private JTextField textFieldApellido = new JTextField();;
	private JTextField textFieldEmail = new JTextField();
	private JTextField textFieldTelefono = new JTextField();;
	private JTextField textFieldDireccion = new JTextField();;

	JLabel lblNombre = new JLabel("Nombre:");
	JLabel lblApellido = new JLabel("Apellido:");
	JLabel lblEmail = new JLabel("Email:");
	JLabel lblTelefono = new JLabel("Telefono:");
	JLabel lblDireccion = new JLabel("Direccion:");

	private PanelContactos vContactos;

	// JPanel panelCentro = new JPanel();
	// JPanel panelSur = new JPanel();
	// JPanel panelBotonera = new JPanel();
	final PanelContactos pContactos;

	// private final JPanel panelNorte = new JPanel();

	public VentanaContacto(final PanelContactos pPanelContactos, int pFila) {

		pContactos = pPanelContactos;
		this.nroFila = pFila;

		setTitle("Contacto");

		// getContentPane().setLayout(new BorderLayout(0, 0));

		// getContentPane().add(panelCentro, BorderLayout.CENTER);
		// panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelCentro.add(lblNombre);

		panelCentro.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		panelCentro.add(lblApellido);

		panelCentro.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		panelCentro.add(lblEmail);

		panelCentro.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		panelCentro.add(lblTelefono);

		panelCentro.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		panelCentro.add(lblDireccion);

		panelCentro.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

		// getContentPane().add(panelSur, BorderLayout.SOUTH);
		// panelSur.setLayout(new BorderLayout(0, 0));
		//
		// panelSur.add(panelBotonera, BorderLayout.EAST);
		// panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelBotonera.add(btnGuardar);

		// getContentPane().add(panelNorte, BorderLayout.NORTH);
		definirEventos();
		definirTamanio();
	}

	public void rellenarTextFields(Contacto pContacto) {
		idContacto = (String.valueOf(pContacto.getIdContacto()));
		textFieldNombre.setText(pContacto.getNombre());
		textFieldApellido.setText(pContacto.getApellido());
		textFieldEmail.setText(pContacto.getEmail());
		textFieldDireccion.setText(pContacto.getDireccion());
		textFieldTelefono.setText(pContacto.getTelefono());
	}

	// ver la utilidad de este metodo
	public void setVentanaContactos(PanelContactos panelContactos) {
		this.vContactos = panelContactos;
	}

	@Override
	public void definirEventos() {

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (validarCampos()) {
				case 0:
					System.out.println("entra en 0");
					Contacto contacto = null;

					try {
						contacto = handler.guardarContactoPress(idContacto,
								textFieldNombre.getText(),
								textFieldApellido.getText(),
								textFieldEmail.getText(),
								textFieldTelefono.getText(),
								textFieldDireccion.getText());
					} catch (BOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (pContactos != null) {
						if (idContacto.isEmpty()) {
							((TableModelContacto) pContactos.getTabla()
									.getModel()).insertarContacto(contacto);
						} else {
							((TableModelContacto) pContactos.getTabla()
									.getModel()).modificarContacto(nroFila,
									contacto);
						}
						pContactos.getTabla()
								.changeSelection(0, 0, false, true);
					}
					VentanaContacto.this.dispose();
					break;
				case -1:
					JOptionPane.showMessageDialog(null,
							"Complete al menos un campo", "Error",
							JOptionPane.OK_OPTION);
					break;
				case -2:
					JOptionPane.showMessageDialog(null,
							"La direccion de email es incorrecta", "Error",
							JOptionPane.OK_OPTION);
					break;

				}

			}
		});
	}

	@Override
	protected int validarCampos() {

		if (textFieldEmail.getText().isEmpty()) {
			if (textFieldApellido.getText().isEmpty()) {
				if (textFieldTelefono.getText().isEmpty()) {
					if (textFieldDireccion.getText().isEmpty()) {
						if (textFieldNombre.getText().isEmpty()) {
							return -1;
						}
					}
				}
			}
		} else {

			if (!validadorEmail.validar(textFieldEmail.getText())) {
				return -2;
			}
		}
		return 0;
	}

	@Override
	protected void definirTamanio() {
		setBounds(0, 0, 380, 200);
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
	}
}
