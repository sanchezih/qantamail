package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tableModel.TableModelContacto;
import basics.Handler;
import basics.MiTipoTabla;
import dbImpl.DAOException;
import entidades.Contacto;

public class PanelContactos extends JPanel {

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			System.out.println("[PanelContactos.java] se cargo el panel "
					+ handler.panelCargado);

			int fila = tabla.getSelectedRow();
			if (handler.panelCargado.equals("PANEL_CONTACTOS")) {
				fila = -1;
			}
			/* Cuando no hay una fila seleccionada, se envia -1 */
			System.out.println("la fila q mando es la " + fila);

			handler.nuevoContactoPress(PanelContactos.this, fila);
		}
	};

	MouseAdapter mouseAdapter = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {

			tableModelContacto = (TableModelContacto) tabla.getModel();

			try {
				rellenarTabla(tabla);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// tabla.changeSelection(0, 0, false, true);
		}
	};

	private Handler handler;

	private JPanel filter = new JPanel();
	private JTable table;
	private MiTipoTabla tabla = new MiTipoTabla();
	ArrayList<Contacto> contactos;
	JButton btnNuevo = new JButton("Nuevo");
	JButton btnEditar = new JButton("Editar");
	JButton btnEliminar = new JButton("Eliminar");
	JButton btnEnviarCorreo = new JButton("Enviar correo");
	TableModelContacto tableModelContacto = new TableModelContacto();

	public TableModelContacto getTableModelContacto() {
		return tableModelContacto;
	}

	public void setTableModelContacto(TableModelContacto tableModelContacto) {
		this.tableModelContacto = tableModelContacto;
	}

	public PanelContactos(Handler handler) {
		setLayout(new BorderLayout());
		this.handler = handler;
		init();
	}

	public void init() {
		setName("PANEL_CONTACTOS");

		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonera = new JPanel();
		panelSur.add(panelBotonera, BorderLayout.EAST);
		panelBotonera.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelBotonera.add(btnNuevo);
		panelBotonera.add(btnEditar);
		panelBotonera.add(btnEliminar);
		panelBotonera.add(btnEnviarCorreo);

		try {
			rellenarTabla(tabla);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane panelTabla = new JScrollPane(tabla);
		panelCentro.add(panelTabla);
		definirEventos();
		setVisible(true);

	}

	public void rellenarTabla(JTable tabla) throws DAOException {
		contactos = handler.rellenarTablaContactos();
		tableModelContacto.setListaDeContactos(contactos);
		tabla.setModel(tableModelContacto);
		tableModelContacto.fireTableDataChanged();
		tabla.changeSelection(0, 0, false, true);
	}

	public void ningunContactoSeleccionado() {
		JOptionPane.showMessageDialog(null, "Seleccione un contacto.", "ERROR",
				JOptionPane.OK_OPTION);
	}

	public JTable getTabla() {
		return tabla;
	}

	public void llamarAlBotonNuevoDelPanelDeContactos() {
		actionListener.actionPerformed(null);
	}

	public void definirEventos() {

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] filasSeleccionadas = tabla.getSelectedRows();

				if (filasSeleccionadas.length > 0) {
					System.out.println("se seleccionaron "
							+ filasSeleccionadas.length + " filas");
					TableModelContacto t = (TableModelContacto) tabla
							.getModel();

					for (int i = 0; i < filasSeleccionadas.length; i++) {
						System.out.println("fila seleccionada "
								+ filasSeleccionadas[i]);

						Contacto contacto = t
								.getContacto(filasSeleccionadas[i]);
						handler.eliminarContacto(contacto);
						// t.eliminarMensaje(filasSeleccionadas[i]);
						// tabla.changeSelection(0, 0, false, true);
						// mouseAdapterTabla.mouseClicked(null);
					}
					mouseAdapter.mouseClicked(null);
				}
			}
		});

		btnNuevo.addActionListener(actionListener);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tabla.getSelectedRow();

				if (tabla.getSelectedRowCount() == 1) {
					System.out.println("El contacto a editar es: "
							+ ((TableModelContacto) tabla.getModel())
									.getContacto(filaSeleccionada)
									.getIdContacto());

					TableModelContacto t = (TableModelContacto) tabla.getModel();
					Contacto contacto = t.getContacto(filaSeleccionada);
					PanelContactos pc = PanelContactos.this;
					handler.getContactoByid(contacto, pc, filaSeleccionada);
				}
			}
		});
		btnEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emails = "";

				int[] filasSeleccionadas = tabla.getSelectedRows();

				if (filasSeleccionadas.length > 0) {
					TableModelContacto t = (TableModelContacto) tabla.getModel();

					for (int i = 0; i < filasSeleccionadas.length; i++) {
						String email = t.getContacto(filasSeleccionadas[i]).getEmail();
						if (i == 0) {
							emails = emails + email;
						} else {
							emails = emails + ", " + email;
						}
					}
					handler.enviarCorreoAContactos(emails);

				}
			}
		});
	}
}
