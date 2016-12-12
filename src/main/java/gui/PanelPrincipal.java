package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tableModel.TableModelContacto;
import tableModel.TableModelMensaje;
import basics.Handler;
import basics.MiTipoTabla;
import bo.BOException;
import dbImpl.DAOException;
import entidades.Contacto;
import entidades.Mensaje;

public class PanelPrincipal extends JPanel {

	public static final int ENTRADA = 0;
	public static final int SALIDA = 1;
	public static final int BORRADORES = 2;
	public static final int ENVIADOS = 3;

	ArrayList<Contacto> contactos;

	JButton btnNuevo = new JButton("Nuevo");
	JButton btnResponder = new JButton("Responder");
	JButton btnEliminar = new JButton("Eliminar");
	JButton btnResponderATodos = new JButton("Responder a todos");
	JButton btnReenviar = new JButton("Reenviar");
	JButton btnEditar = new JButton("Editar");
	TableModelContacto tableModelDeContactos = new TableModelContacto();

	Mensaje mensajeParaSetear = new Mensaje();

	final int nroItemDefaultLista = 0;
	final int nroFilaDefaultTablaMensajes = 0;
	TableModelMensaje tableModelMensaje = new TableModelMensaje();

	Handler handler = new Handler();

	private JTextField textFieldDe = new JTextField();
	private JTextField textFieldPara = new JTextField();
	private JTextField textFieldCC = new JTextField();
	private JTextField textFieldAsunto = new JTextField();
	private JTextField textFieldFecha = new JTextField();

	DefaultListModel modeloDeLista = new DefaultListModel();
	final JList listaDeCarpetas = new JList(modeloDeLista);
	JPanel pnlTabla = new JPanel();
	final MiTipoTabla tabla = new MiTipoTabla();
	final JTextArea textMensaje = new JTextArea();

	MouseAdapter mouseAdapter = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			try {

				int numeroDeItemSeleccionado = listaDeCarpetas
						.getSelectedIndex();
				tableModelMensaje = (TableModelMensaje) tabla.getModel();

				handler.lalala(numeroDeItemSeleccionado, tableModelMensaje);
				tabla.changeSelection(0, 0, false, true);
				System.out.println("el la tabla  hay " + tabla.getRowCount());
				if (tabla.getRowCount() > 0) {

					mouseAdapterTabla.mouseClicked(null);
				} else {
					limpiarCampos();
				}

			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	MouseAdapter mouseAdapterTabla = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (tabla.getSelectedRowCount() == 1) {
				try {
					buscarContenidoMensaje();
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {

			}
		}
	};

	public PanelPrincipal(Handler handler) {
		setLayout(new BorderLayout());
		this.handler = handler;
		try {
			init();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws DAOException {
		setName("PANEL_PRINCIPAL");
		JScrollPane scrollPane = new JScrollPane();

		// Creo los JPanel
		JPanel panelBarraEstado = new JPanel();
		JPanel panelDatos = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JPanel panel_5 = new JPanel();
		JPanel panel_6 = new JPanel();
		JPanel panel_8 = new JPanel();
		JPanel panel_9 = new JPanel();
		JPanel panel_10 = new JPanel();
		JPanel panelCarpetas = new JPanel();
		JPanel panelMensajeActual = new JPanel();

		// Creo los JSplitPane
		JSplitPane splitPaneCentral = new JSplitPane();
		JSplitPane splitPaneMensajes = new JSplitPane();
		splitPaneMensajes.setDividerLocation(180);

		// Creo la barra de menu
		JMenuBar barraMenu = new JMenuBar();
		JMenu menuArchivo = new JMenu("Archivo");

		setLayout(new BorderLayout(0, 0));

		barraMenu.add(menuArchivo);

		add(panelBarraEstado, BorderLayout.SOUTH);
		panelBarraEstado.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonera = new JPanel();
		panelBarraEstado.add(panelBotonera, BorderLayout.EAST);
		panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelBotonera.add(btnNuevo);
		panelBotonera.add(btnResponder);
		panelBotonera.add(btnResponderATodos);
		panelBotonera.add(btnReenviar);
		panelBotonera.add(btnEditar);
		panelBotonera.add(btnEliminar);

		add(splitPaneCentral, BorderLayout.CENTER);
		splitPaneMensajes.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneCentral.setRightComponent(splitPaneMensajes);

		splitPaneMensajes.setRightComponent(panelMensajeActual);
		panelMensajeActual.setLayout(new BorderLayout(0, 0));

		panelMensajeActual.add(panelDatos, BorderLayout.NORTH);
		panelDatos.setLayout(new BorderLayout(0, 0));

		panelDatos.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("De: ");
		panel_1.add(lblNewLabel);

		textFieldDe.setEditable(false);
		panel_1.add(textFieldDe);
		textFieldDe.setColumns(10);

		panelDatos.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Para: ");
		panel_3.add(lblNewLabel_1);

		textFieldPara.setEditable(false);
		panel_3.add(textFieldPara);
		textFieldPara.setColumns(10);

		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		JLabel lblCC = new JLabel("CC: ");
		panel_5.add(lblCC);

		textFieldCC = new JTextField();
		textFieldCC.setEditable(false);
		textFieldCC.setColumns(10);
		panel_5.add(textFieldCC);

		panel_4.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		panel_6.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		panel_8.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));

		JLabel lblAsunto = new JLabel("Asunto: ");
		panel_9.add(lblAsunto);

		textFieldAsunto.setEditable(false);
		panel_9.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);

		panel_8.add(panel_10, BorderLayout.SOUTH);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		JLabel lblNewLabel_4 = new JLabel("Fecha: ");
		panel_10.add(lblNewLabel_4);

		textFieldFecha.setEditable(false);
		panel_10.add(textFieldFecha);
		textFieldFecha.setColumns(10);

		panelMensajeActual.add(scrollPane, BorderLayout.CENTER);

		textMensaje.setEditable(false);
		textMensaje.setLineWrap(true);
		scrollPane.setViewportView(textMensaje);

		ArrayList<Mensaje> mensajes = handler
				.getMensajesSegunCriterio(nroItemDefaultLista);
		tableModelMensaje.setListaDeMensajes(mensajes);
		tabla.setModel(tableModelMensaje);

		JScrollPane panel5 = new JScrollPane(tabla);

		splitPaneMensajes.setLeftComponent(panel5);

		splitPaneCentral.setLeftComponent(panelCarpetas);
		panelCarpetas.setLayout(new BorderLayout(0, 0));

		modeloDeLista.add(ENTRADA, "Bandeja de entrada");
		modeloDeLista.add(SALIDA, "Bandeja de salida");
		modeloDeLista.add(BORRADORES, "Borradores");
		modeloDeLista.add(ENVIADOS, "Elementos enviados");

		listaDeCarpetas.setVisibleRowCount(1);
		listaDeCarpetas.setSelectedIndex(nroItemDefaultLista);

		mouseAdapter.mouseClicked(null);
		panelCarpetas.add(listaDeCarpetas, BorderLayout.CENTER);
		definirEventos();
	}

	public JTable getTabla() {
		return tabla;
	}

	public void MostrarErrorVariosMensajesSeleccionados() {
		JOptionPane.showMessageDialog(null, "Debe seleccionar solo un mensaje",
				"Error", JOptionPane.OK_OPTION);
	}

	public void MostrarErrorNoParaBorrador() {
		JOptionPane.showMessageDialog(null,
				"Accion invalida para un mensaje borrador", "Error",
				JOptionPane.OK_OPTION);
	}

	public void definirEventos() {

		listaDeCarpetas.addMouseListener(mouseAdapter);

		tabla.addMouseListener(mouseAdapterTabla);

		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.nuevoMensajePress();
			}
		});

		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaDeCarpetas.getSelectedIndex() != BORRADORES) {
					if (tabla.getSelectedRowCount() == 1) {
						handler.responderATodos(mensajeParaSetear, "RESPONDER");
					} else {
						MostrarErrorVariosMensajesSeleccionados();
					}
				} else {
					MostrarErrorNoParaBorrador();
				}
			}
		});

		btnResponderATodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("Responder a todos - El mail para responder tiene ID: "
								+ mensajeParaSetear.getIdMensaje());
				if (listaDeCarpetas.getSelectedIndex() != BORRADORES) {
					if (tabla.getSelectedRowCount() == 1) {
						handler.responderATodos(mensajeParaSetear,
								"RESPONDER_A_TODOS");
					} else {
						MostrarErrorVariosMensajesSeleccionados();
					}
				} else {
					MostrarErrorNoParaBorrador();
				}
			}
		});

		btnReenviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("Reenviar mensaje - El mail para reenviar tiene ID: "
								+ mensajeParaSetear.getIdMensaje());
				if (listaDeCarpetas.getSelectedIndex() != BORRADORES) {
					if (tabla.getSelectedRowCount() == 1) {
						handler.responderATodos(mensajeParaSetear, "REENVIAR");
					} else {
						MostrarErrorVariosMensajesSeleccionados();
					}
				} else {
					MostrarErrorNoParaBorrador();
				}
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaDeCarpetas.getSelectedIndex() == BORRADORES) {
					if (tabla.getSelectedRowCount() == 1) {
						int filaSeleccionada = tabla.getSelectedRow();
						TableModelMensaje t = (TableModelMensaje) tabla
								.getModel();
						Mensaje mensaje = t.getMensaje(filaSeleccionada);
						PanelPrincipal panelPrincipal = PanelPrincipal.this;
						handler.getMensajeById(mensaje, panelPrincipal,
								filaSeleccionada);
					} else {
						MostrarErrorVariosMensajesSeleccionados();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"No se puede editar un mensaje que no es borrador",
							"Error", JOptionPane.OK_OPTION);
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] filasSeleccionadas = tabla.getSelectedRows();

				if (filasSeleccionadas.length > 0) {
					System.out.println("se seleccionaron "
							+ filasSeleccionadas.length + " filas");
					TableModelMensaje t = (TableModelMensaje) tabla.getModel();

					for (int i = 0; i < filasSeleccionadas.length; i++) {
						try {
							System.out.println("fila seleccionada "
									+ filasSeleccionadas[i]);
							Mensaje mensaje = t
									.getMensaje(filasSeleccionadas[i]);
							handler.eliminarMensaje(mensaje);
						} catch (BOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// t.eliminarMensaje(filasSeleccionadas[i]);
						// tabla.changeSelection(0, 0, false, true);
						// mouseAdapterTabla.mouseClicked(null);

					}
					mouseAdapter.mouseClicked(null);

				}

			}
		});
	}

	public void buscarContenidoMensaje() throws DAOException, BOException {
		int idMensaje = ((TableModelMensaje) tabla.getModel()).getMensaje(
				tabla.getSelectedRow()).getIdMensaje();
		mensajeParaSetear = handler.getMensajeByid(idMensaje);
		handler.setMensajeSeleccionado(mensajeParaSetear, textFieldDe,
				textFieldPara, textFieldCC, textFieldFecha, textFieldAsunto,
				textMensaje);
	}

	private void limpiarCampos() {
		textFieldAsunto.setText("");
		textFieldCC.setText("");
		textFieldDe.setText("");
		textFieldFecha.setText("");
		textFieldPara.setText("");
		textMensaje.setText("");
	}
}
