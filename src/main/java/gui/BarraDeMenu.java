package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import basics.Handler;
import bo.BOException;
import dbImpl.DAOException;
import entidades.Mensaje;

public class BarraDeMenu extends JMenuBar {

	final Mensaje mensajeParaSetear = new Mensaje();

	public BarraDeMenu(final Handler handler, JFrame frame) {

		setVisible(true);

		JMenu menuNuevo = new JMenu("Nuevo");
		JMenu menuArchivo = new JMenu("Archivo");
		JMenu menuVer = new JMenu("Ver");

		JMenuItem itemNuevoMensaje = new JMenuItem("Nuevo mensaje");
		JMenuItem itemNuevoContacto = new JMenuItem("Nuevo contacto");

		JMenuItem itemSalir = new JMenuItem("Salir");

		menuArchivo.add(menuNuevo);
		menuNuevo.add(itemNuevoMensaje);
		menuNuevo.add(itemNuevoContacto);

		JMenuItem itemRecibirTodo = new JMenuItem("Recibir mensajes");

		menuArchivo.add(itemRecibirTodo);

		JSeparator separator = new JSeparator();
		menuArchivo.add(separator);
		menuArchivo.add(itemSalir);

		add(menuArchivo);
		add(menuVer);

		JMenuItem itemVerCorreo = new JMenuItem("Ver correo");
		menuVer.add(itemVerCorreo);

		itemVerCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.cargarPanelPrincipal();
			}
		});

		JMenuItem itemVerContactos = new JMenuItem("Ver contactos");
		menuVer.add(itemVerContactos);

		itemVerContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.cargarPanelDeContactos();
			}
		});

		itemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.logOut();
			}
		});

		itemRecibirTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					handler.enviarYRecibirTodoPress();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		itemNuevoMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.nuevoMensajePress();
			}
		});

		itemNuevoContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handler.nuevoContactoPressDesdeMenu();
			}
		});

	}

}
