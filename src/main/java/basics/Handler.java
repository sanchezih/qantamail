package basics;

import dbImpl.ContactoDAODBImpl;
import dbImpl.DAOException;
import dbImpl.MensajeDAODBImpl;
import dbImpl.UsuarioDAOImpl;
import entidades.Contacto;
import entidades.Mensaje;
import gui.BarraDeMenu;
import gui.PanelContactos;
import gui.PanelLogIn;
import gui.PanelPrincipal;
import gui.VentanaContacto;
import gui.VentanaMensaje;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mail.EnviarMensaje;
import tableModel.TableModelContacto;
import tableModel.TableModelMensaje;
import bo.BOException;
import bo.ContactoBO;
import bo.MensajeBO;
import bo.UsuarioBO;

public class Handler {

	TableModelContacto tableModelContacto = new TableModelContacto();
	PanelContactos panelDeContactos = new PanelContactos(this);
	public String panelCargado = null;
	private String caracter = null;
	private String estado = null;
	private JFrame frame;
	Contacto contacto = null;

	public ArrayList<Mensaje> getMensajesSegunCriterio(int pNumeroDeCriterio) throws DAOException {
		MensajeDAODBImpl dm = new MensajeDAODBImpl();
		setCaracterYEstado(pNumeroDeCriterio);
		ArrayList<Mensaje> mensajes = dm.getAllMensaje(caracter, estado);
		return mensajes;
	}

	public void eliminarContacto(Contacto pContacto) {
		ContactoBO bo = new ContactoBO();
		bo.setDao(new ContactoDAODBImpl());
		bo.contactoDelete(pContacto);
	}

	public void eliminarMensaje(Mensaje pMensaje) throws BOException {
		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());
		bo.mensajeDelete(pMensaje);
	}

	public void getContactoByid(Contacto pContacto, PanelContactos pPanelContactos, int pFila) {
		ContactoBO bo = new ContactoBO();
		bo.setDao(new ContactoDAODBImpl());

		VentanaContacto ventanaContacto = new VentanaContacto(pPanelContactos, pFila);
		ventanaContacto.setVentanaContactos(pPanelContactos);
		ventanaContacto.rellenarTextFields(pContacto);
	}

	public void getMensajeById(Mensaje pMensaje, PanelPrincipal pp, int fila) {
		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());

		VentanaMensaje ventanaMensaje = new VentanaMensaje(pp, fila);
		ventanaMensaje.setVentanaPrincipal(pp);
		ventanaMensaje.rellenarTextFields(pMensaje);
	}

	public void setCaracterYEstado(int pNum) {
		switch (pNum) {
		case 0:
			caracter = "PARA";
			estado = "RECIBIDO_OK";
			break;
		case 1:
			caracter = "DE";
			estado = "PENDIENTE_ENVIO";
			break;
		case 2:
			caracter = "DE";
			estado = "BORRADOR";
			break;
		case 3:
			caracter = "DE";
			estado = "ENVIADO_OK";
			break;
		}
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void initApp() {
		setFrame(new MiFrame());
		cargarPanelLogIn();
		frame.setVisible(true);
	}

	public void logOut() {
		frame.setJMenuBar(new JMenuBar());
		cargarPanelLogIn();
		frame.validate();
	}

	public void cargarPanelDeContactos() {
		PanelContactos panelContactos = new PanelContactos(this);
		cambiarPanel(panelContactos);
		panelContactos.getTableModelContacto();
	}

	public void cargarPanelPrincipal() {

		PanelPrincipal panelPrincipal = new PanelPrincipal(this);
		cambiarPanel(panelPrincipal);
	}

	public void cambiarPanel(JPanel pPanel) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(pPanel);
		frame.getContentPane().validate();
		panelCargado = pPanel.getName();
	}

	public void cargarPanelLogIn() {
		cambiarPanel(new PanelLogIn(this));
	}

	public void setMensajeSeleccionado(Mensaje pMensaje, JTextField textFieldDe, JTextField textFieldPara,
			JTextField textFieldCC, JTextField textFieldFecha, JTextField textFieldAsunto, JTextArea textoMensaje) {

		textFieldDe.setText(pMensaje.getDe());
		textFieldPara.setText(pMensaje.getPara());
		textFieldCC.setText(pMensaje.getCc());
		textFieldFecha.setText(pMensaje.getFecha());
		textFieldAsunto.setText(pMensaje.getAsunto());
		textoMensaje.setText(pMensaje.getTexto());
	}

	public void lalala(int criterio, TableModelMensaje tableModelMensaje) throws DAOException {
		ArrayList<Mensaje> mensajes = getMensajesSegunCriterio(criterio);
		tableModelMensaje.setListaDeMensajes(mensajes);
		tableModelMensaje.fireTableDataChanged();
	}

	public Mensaje getMensajeByid(int idMensaje) throws BOException {
		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());

		return bo.getMensajeById(idMensaje);
	}

	public Contacto guardarContactoPress(String pId, String pNombre, String pApellido, String pEmail, String pTelefono,
			String pDireccion) throws BOException {
		Contacto contacto = new Contacto();

		ContactoBO bo = new ContactoBO();
		bo.setDao(new ContactoDAODBImpl());

		contacto.setNombre(pNombre);
		contacto.setApellido(pApellido);
		contacto.setEmail(pEmail);
		contacto.setTelefono(pTelefono);
		contacto.setDireccion(pDireccion);

		if (pId.isEmpty()) {
			int idContacto = getIdParaContacto();
			contacto.setIdContacto(idContacto);
			bo.contactoInsert(contacto);
		} else {
			contacto.setIdContacto(Integer.parseInt(pId));
			bo.contactoUpdate(contacto);
		}
		return contacto;
	}

	public Mensaje guardarMensajePress(String pId, String pPara, String pCc, String pCco, String pAsunto, String pTexto)
			throws BOException, SQLException, DAOException {
		Mensaje mensaje = new Mensaje();

		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());

		mensaje.setDe(Constantes.CUENTA);
		mensaje.setPara(pPara);
		mensaje.setCc(pCc);
		mensaje.setCco(pCco);
		mensaje.setAsunto(pAsunto);
		mensaje.setTexto(pTexto);

		if (pId.isEmpty()) {
			int idMensaje = getLastCodigoParaMensaje();
			mensaje.setIdMensaje(idMensaje);
			bo.mensajePendienteEnvioInsert(mensaje, "BORRADOR");
		} else {
			mensaje.setIdMensaje(Integer.parseInt(pId));
			bo.mensajeUpdate(mensaje);
		}
		return mensaje;
	}

	public void enviarMensajePress(String pId, String pPara, String pCc, String pCco, String pAsunto, String pTexto)
			throws SQLException, DAOException, BOException {

		Mensaje mensaje = new Mensaje();
		EnviarMensaje mail = new EnviarMensaje();

		if (pAsunto.isEmpty()) {
			pAsunto = "(Sin asunto)";
		}

		mensaje = guardarMensajePress(pId, pPara, pCc, pCco, pAsunto, pTexto);

		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());

		bo.updateEstadoMensaje("PENDIENTE", mensaje.getIdMensaje());
		mail.EnviarMensaje(mensaje);
		bo.updateEstadoMensaje("ENVIADO_OK", mensaje.getIdMensaje());
	}

	/************************************************************************************/
	// public Mensaje guardarMensajeEditadoPress(String pId, String para,String
	// pCc, String pCco, String pAsunto, String pTexto)throws SQLException,
	// DAOException {
	//
	// Mensaje mensaje = new Mensaje();
	//
	// mensaje.setIdMensaje(Integer.parseInt(pId));
	// mensaje.setDe(Constantes.CUENTA);
	// mensaje.setPara(para);
	// mensaje.setCc(pCc);
	// mensaje.setCco(pCco);
	// mensaje.setAsunto(pAsunto);
	// mensaje.setTexto(pTexto);
	//
	// MensajeBO bo = new MensajeBO();
	// bo.setDao(new MensajeDAODBImpl());
	//
	// bo.mensajeUpdate(mensaje);
	// return mensaje;
	// }

	public int getIdParaContacto() throws BOException {
		ContactoBO bo = new ContactoBO();
		bo.setDao(new ContactoDAODBImpl());
		int maximo = bo.getMaxIdContacto();
		return maximo + 1;
	}

	public int getLastCodigoParaMensaje() throws BOException {
		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());
		int maximo = bo.getMaxIdMensaje();
		return maximo + 1;
	}

	public void enviarYRecibirTodoPress() throws SQLException, DAOException, BOException {

		MensajeBO bo = new MensajeBO();
		bo.setDao(new MensajeDAODBImpl());

		ArrayList<Mensaje> listaDeMensajes = mail.RecibirMensajes.recibir();
		Iterator<Mensaje> i = listaDeMensajes.iterator();
		int cantRecibidos = 0;

		while (i.hasNext()) {
			Mensaje mensaje = (Mensaje) i.next();
			mensaje.setIdMensaje(getLastCodigoParaMensaje());
			bo.mensajeRecibidoInsert(mensaje);
			cantRecibidos++;
		}
		if (cantRecibidos > 0) {
			cargarPanelPrincipal();
		}
		JOptionPane.showMessageDialog(null, "Se recibieron " + cantRecibidos + " mensajes", "Recibir mensajes",
				JOptionPane.OK_OPTION);

	}

	public void logInAdministrator(String pUsuario, String pPassword) {

		UsuarioBO bo = new UsuarioBO();
		bo.setDao(new UsuarioDAOImpl());
		try {
			if (bo.getUsuarioByNombreUsuario(pUsuario, pPassword)) {
				frame.getContentPane().removeAll();
				JMenuBar menuBar = new BarraDeMenu(this, frame);
				frame.setJMenuBar(menuBar);
				cargarPanelPrincipal();
				frame.validate();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error", JOptionPane.OK_OPTION);
			}
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nuevoContactoPressDesdeMenu() {
		if (panelCargado.equals("PANEL_PRINCIPAL")) {

			System.out.println("Se llamo desde el principal");

			nuevoContactoPress(null, -2);

		} else {

			System.out.println("Se llamo desde contactos");
			panelDeContactos.llamarAlBotonNuevoDelPanelDeContactos();
		}
	}

	public void nuevoContactoPress(PanelContactos pPanelContactos, int fila) {
		panelDeContactos = pPanelContactos;
		new VentanaContacto(panelDeContactos, fila);
	}

	public void verContactosPress() throws DAOException {
		new PanelContactos(this);
	}

	public void mostrarError(String pMensajeDeError) {
		JOptionPane.showMessageDialog(null, pMensajeDeError, "Error", JOptionPane.OK_OPTION);
	}

	public ArrayList<Contacto> rellenarTablaContactos() throws DAOException {
		ContactoDAODBImpl dm = new ContactoDAODBImpl();
		ArrayList<Contacto> contactos = dm.getAllContacto();
		return contactos;
	}

	public void nuevoMensajePress() {
		new VentanaMensaje(null, 0);
	}

	public void responderATodos(Mensaje pMensaje, String pTipoDeEnvio) {
		VentanaMensaje ventanaMensaje = new VentanaMensaje(null, 0);
		ventanaMensaje.llenarTextFields(pMensaje, pTipoDeEnvio);
	}

	public void enviarCorreoAContactos(String emails) {
		System.out.println(emails);
		VentanaMensaje ventanaMensaje = new VentanaMensaje(null, 0);

		ventanaMensaje.llenarTextFields(emails);
	}
}
