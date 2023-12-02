package dbImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import basics.DBManager;
import dao.IContactoDAO;
import entidades.Contacto;

public class ContactoDAODBImpl implements IContactoDAO {

	/**
	 * 
	 */
	public void contactoInsert(Contacto pContacto) throws DAOException {

		String query = "INSERT INTO contacto (id_contacto, nombre, apellido, email,  telefono, direccion) VALUES ("
				+ pContacto.getIdContacto() + ", '" + pContacto.getNombre() + "', '" + pContacto.getApellido() + "', '"
				+ pContacto.getEmail() + "', '" + pContacto.getTelefono() + "', '" + pContacto.getDireccion() + "')";

		Connection connection = DBManager.getInstance().connect();

		try {
			Statement s = connection.createStatement();
			s.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void contactoUpdate(Contacto pContacto) throws DAOException {
		String query = "UPDATE contacto SET NOMBRE = '" + pContacto.getNombre() + "', APELLIDO='"
				+ pContacto.getApellido() + "', EMAIL='" + pContacto.getEmail() + "',TELEFONO='"
				+ pContacto.getTelefono() + "',DIRECCION='" + pContacto.getDireccion() + "' WHERE id_contacto = "
				+ pContacto.getIdContacto();

		Connection connection = DBManager.getInstance().connect();

		try {
			Statement s = connection.createStatement();
			s.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void contactoDelete(Contacto pContacto) throws DAOException {

		String query = "DELETE FROM contacto WHERE id_contacto =" + pContacto.getIdContacto();
		;
		Connection connection = DBManager.getInstance().connect();
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public ArrayList<Contacto> getAllContacto() throws DAOException {

		ArrayList<Contacto> listaDeContactos = new ArrayList<Contacto>();

		String query = "SELECT * FROM contacto";
		Connection connection = DBManager.getInstance().connect();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()) {
				int idContacto = rs.getInt("id_contacto");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				String direccion = rs.getString("direccion");

				Contacto contacto = new Contacto();

				contacto.setIdContacto(idContacto);
				contacto.setNombre(nombre);
				contacto.setApellido(apellido);
				contacto.setEmail(email);
				contacto.setTelefono(telefono);
				contacto.setDireccion(direccion);

				listaDeContactos.add(contacto);
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return listaDeContactos;
	}

	public Contacto getContactoById(int idContacto) throws DAOException {
		Contacto contacto = null;
		String query = "SELECT * FROM contacto WHERE id_contacto = '" + idContacto + "'";
		Connection conexion = DBManager.getInstance().connect();
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery(query);

			if (rs.next()) {
				int i = rs.getInt("id_contacto");
				contacto = new Contacto();
				contacto.setIdContacto(i);
			}

		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return contacto;
	}

	public int getMaxIdContacto() throws DAOException {
		int maximo = 0;
		String query = "SELECT MAX(id_contacto) maximo FROM contacto";
		Connection conexion = DBManager.getInstance().connect();
		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery(query);
			if (rs.next()) {
				maximo = rs.getInt("maximo");
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException("Error al conectar con la base de datos", e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conexion.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return maximo;
	}
}
