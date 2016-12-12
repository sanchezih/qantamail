package tableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entidades.Mensaje;

public class TableModelMensaje extends AbstractTableModel {

	private ArrayList<Mensaje> listaDeMensajes;

	private String[] nombreColumnas = {/* "ID", */"De", "Para",/* "CC", "CCO", */
	"Asunto",/* "Texto", */"Fecha"/* , "Estado", "Caracter" */};

	// public static final int ID = 0;
	public static final int DE = 0;
	public static final int PARA = 1;
	// public static final int CC = 3;
	// public static final int CCO = 4;
	public static final int ASUNTO = 2;
	// public static final int TEXTO = 6;
	public static final int FECHA = 3;

	// public static final int ESTADO = 8;
	// public static final int CARACTER = 9;

	public void setListaDeMensajes(ArrayList<Mensaje> Mensajes) {
		this.listaDeMensajes = Mensajes;
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public int getRowCount() {
		return listaDeMensajes.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Mensaje mensaje = (Mensaje) listaDeMensajes.get(fila);

		switch (columna) {
		// case ID:return mensaje.getIdMensaje();
		case DE:
			return mensaje.getDe();
		case PARA:
			return mensaje.getPara();
			// case CC: return mensaje.getCc();
			// case CCO: return mensaje.getCco();
		case ASUNTO:
			return mensaje.getAsunto();
			// case TEXTO: return mensaje.getTexto();
		case FECHA:
			return mensaje.getFecha();
			// case ESTADO: return mensaje.getEstado();
			// case CARACTER: return mensaje.getCaracter();
		}
		return null;
	}

	public String getColumnName(int columna) {
		return nombreColumnas[columna];
	}

	public void insertarMensaje(Mensaje pMensaje) {
		listaDeMensajes.add(pMensaje);
		fireTableDataChanged();
	}

	public Mensaje getMensaje(int fila) {
		return listaDeMensajes.get(fila);
	}

	public void modificarMensaje(int pFila, Mensaje pMensaje) {
		listaDeMensajes.set(pFila, pMensaje);
		fireTableDataChanged();
	}

	public void eliminarMensaje(int fila) {
		System.out.println("remuevo la fila " + fila);
		listaDeMensajes.remove(fila);
		fireTableDataChanged();
	}
}
