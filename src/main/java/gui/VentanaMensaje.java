package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tableModel.TableModelMensaje;
import validaciones.ValidadorEmail;
import bo.BOException;
import dbImpl.DAOException;
import entidades.Mensaje;

public class VentanaMensaje extends VentanaPlantilla {
	private PanelPrincipal vPrincipal;
	private ValidadorEmail validadorEmail = new ValidadorEmail();
	private int nroFila;
	private String idMensaje = "";
	private JTextField textFieldPara = new JTextField();
	private JTextField textFieldAsunto = new JTextField();
	private JTextField textFieldCC = new JTextField();
	private JTextField textFieldCCO = new JTextField();
	private JButton btnEnviar = new JButton("Enviar");
	private JButton btnGuardarBorrador = new JButton("Guardar borrador");

	private JTextArea contenidoMensaje = new JTextArea();

	final PanelPrincipal pPrincipal;

	public VentanaMensaje(final PanelPrincipal pPanelPrincipal, int pFila) {

		setTitle("Mensaje sin titulo");

		pPrincipal = pPanelPrincipal;
		this.nroFila = pFila;

		// JPanel panelNorte = new JPanel();

		// getContentPane().add(panelNorte, BorderLayout.NORTH);

		// panelNorte.setLayout(new BorderLayout(0, 0));

		// JPanel panelSur = new JPanel();
		// getContentPane().add(panelSur, BorderLayout.SOUTH);
		// panelSur.setLayout(new BorderLayout(0, 0));

		// JPanel panelBotonera = new JPanel();
		// panelSur.add(panelBotonera, BorderLayout.EAST);

		panelBotonera.add(btnEnviar);

		panelBotonera.add(btnGuardarBorrador);

		// JPanel panelCentro = new JPanel();
		// getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panelTextFields = new JPanel();
		panelCentro.add(panelTextFields, BorderLayout.NORTH);
		panelTextFields.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panelTextFields.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel lblPara = new JLabel("Para: ");
		panel_2.add(lblPara);

		panel_2.add(textFieldPara);
		textFieldPara.setColumns(10);

		JPanel panel_3 = new JPanel();
		panelTextFields.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		JLabel labelCC = new JLabel("CC: ");
		panel_4.add(labelCC);

		panel_4.add(textFieldCC);
		textFieldCC.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		JLabel labelCCO = new JLabel("CCO: ");
		panel_6.add(labelCCO);

		panel_6.add(textFieldCCO);
		textFieldCCO.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

		JLabel lblAsunto = new JLabel("Asunto: ");
		panel_7.add(lblAsunto);

		panel_7.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		panelCentro.add(scrollPane, BorderLayout.CENTER);

		contenidoMensaje.setLineWrap(true);
		scrollPane.setViewportView(contenidoMensaje);

		definirEventos();
		definirTamanio();
	}

	public void setVentanaPrincipal(PanelPrincipal pPanelPrincipal) {
		this.vPrincipal = pPanelPrincipal;
	}

	@Override
	public void definirEventos() {

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validarCampos() == 0) {
					try {
						handler.enviarMensajePress(idMensaje, textFieldPara
								.getText(), textFieldCC.getText(), textFieldCCO
								.getText(), textFieldAsunto.getText(),
								contenidoMensaje.getText().toString());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								"Error de base de datos", "Error",
								JOptionPane.OK_OPTION);
						e1.printStackTrace();
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					VentanaMensaje.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Debe ingresar un destinatario", "Error",
							JOptionPane.OK_OPTION);
				}
			}
		});

		btnGuardarBorrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Mensaje mensaje = null;
				if (validarCampos() == 0) {
					try {
						mensaje = handler.guardarMensajePress(idMensaje,
								textFieldPara.getText(), textFieldCC.getText(),
								textFieldCCO.getText(),
								textFieldAsunto.getText(),
								contenidoMensaje.getText());
						if (pPrincipal != null) {
							if (idMensaje.isEmpty()) {
								((TableModelMensaje) pPrincipal.getTabla()
										.getModel()).insertarMensaje(mensaje);
							} else {
								((TableModelMensaje) pPrincipal.getTabla()
										.getModel()).modificarMensaje(nroFila,
										mensaje);
							}
						}

					} catch (BOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				VentanaMensaje.this.dispose();
			}
		});
	}

	public void rellenarTextFields(Mensaje pMensaje) {
		idMensaje = (String.valueOf(pMensaje.getIdMensaje()));
		textFieldPara.setText(pMensaje.getPara());
		textFieldCC.setText(pMensaje.getCc());
		textFieldCCO.setText(pMensaje.getCco());
		textFieldAsunto.setText(pMensaje.getAsunto());
		contenidoMensaje.setText(pMensaje.getTexto());
	}

	public void llenarTextFields(String emails) {
		textFieldPara.setText(emails);
	}

	public void llenarTextFields(Mensaje pMensaje, String pTipoEnvio) {

		contenidoMensaje.setText("\n\n-----Mensaje original-----\n" + "De: "
				+ pMensaje.getDe() + "\n" + "Enviado el: "
				+ pMensaje.getFecha() + "\n" + "Para: " + pMensaje.getPara()
				+ "\n" + "CC: " + pMensaje.getCc() + "\n" + "Asunto: "
				+ pMensaje.getAsunto() + "\n\n" + pMensaje.getTexto());

		String paraFiltrado = "";
		String deFiltrado = "";
		String ccFiltrado = "";
		String paraConsolidado = "";

		if (!pMensaje.getPara().equals("")) {
			paraFiltrado = miParser(pMensaje.getPara());
		}

		if (!pMensaje.getDe().equals("")) {
			deFiltrado = miParser(pMensaje.getDe());
		}

		if (!pMensaje.getCc().equals("")) {
			ccFiltrado = miParser(pMensaje.getCc());
		}

		if (pTipoEnvio.equals("RESPONDER_A_TODOS")) {

			textFieldAsunto.setText("RE: " + pMensaje.getAsunto());

			paraConsolidado = deFiltrado;

			if (!paraFiltrado.equals("")) {
				paraConsolidado = paraConsolidado + ", " + paraFiltrado;
			}
			textFieldPara.setText(paraConsolidado);

			textFieldCC.setText(ccFiltrado);
			textFieldCCO.setText("");

		} else {
			textFieldCC.setText("");
			textFieldCCO.setText("");
			if (pTipoEnvio.equals("RESPONDER")) {
				textFieldAsunto.setText("RE: " + pMensaje.getAsunto());

				textFieldPara.setText(deFiltrado);
			} else {
				textFieldAsunto.setText("RV: " + pMensaje.getAsunto());
				textFieldPara.setText("");
			}
		}
	}

	private String miParser(String pEntrada) {
		String salida = new String();
		int i = 0;
		StringTokenizer token = new StringTokenizer(pEntrada, ",");
		while (token.hasMoreTokens()) {
			String tok = token.nextToken();

			if (!tok.contains(basics.Constantes.CUENTA)) {
				if (i > 0) {
					salida = salida + ", ";
				}
				salida = salida + tok;
				i++;
			}
		}
		return salida;
	}

	@Override
	protected int validarCampos() {

		if (textFieldPara.getText().isEmpty()) {
			if (textFieldAsunto.getText().isEmpty()) {
				if (textFieldCC.getText().isEmpty()) {
					if (textFieldCCO.getText().isEmpty()) {
						if (contenidoMensaje.getText().isEmpty()) {
							return -1;
						}
					}
				}
			}
		}
		return 0;
	}

	@Override
	protected void definirTamanio() {
		setBounds(0, 0, 640, 480);
		setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
	}
}