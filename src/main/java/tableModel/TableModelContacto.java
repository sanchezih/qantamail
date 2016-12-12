package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import entidades.Contacto;

public class TableModelContacto extends AbstractTableModel {

	private ArrayList<Contacto> listaDeContactos;

	private String[] nombreColumnas = { /* "ID", */"Nombre", "Apellido",
			"Email", "Telefono", "Direccion" };

	// public static final int ID = 0;
	public static final int NOMBRE = 0;
	public static final int APELLIDO = 1;
	public static final int EMAIL = 2;
	public static final int TELEFONO = 3;
	public static final int DIRECCION = 4;

	public void setListaDeContactos(ArrayList<Contacto> contactos) {
		this.listaDeContactos = contactos;
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public int getRowCount() {
		return listaDeContactos.size();
	}

	@Override
	public Object getValueAt(int fila, int pColumna) {
		Contacto contacto = (Contacto) listaDeContactos.get(fila);

		switch (pColumna) {
		/* case ID: return contacto.getIdContacto(); */
		case NOMBRE:
			return contacto.getNombre();
		case APELLIDO:
			return contacto.getApellido();
		case EMAIL:
			return contacto.getEmail();
		case TELEFONO:
			return contacto.getTelefono();
		case DIRECCION:
			return contacto.getDireccion();
		}
		return null;
	}

	public String getColumnName(int pColumna) {
		return nombreColumnas[pColumna];
	}

	public Contacto getContacto(int pFila) {
		return listaDeContactos.get(pFila);
	}

	public void eliminarContacto(int pFila) {
		listaDeContactos.remove(pFila);
		fireTableDataChanged();
	}

	public void insertarContacto(Contacto pContacto) {
		listaDeContactos.add(pContacto);
		fireTableDataChanged();
	}

	public void modificarContacto(int pFila, Contacto pContacto) {
		listaDeContactos.set(pFila, pContacto);
		fireTableDataChanged();
	}
}
