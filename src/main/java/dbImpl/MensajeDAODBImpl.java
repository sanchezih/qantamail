package dbImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import basics.Constantes;
import basics.DBManager;
import dao.IMensajeDAO;
import entidades.Mensaje;

public class MensajeDAODBImpl implements IMensajeDAO {

	@Override
	public ArrayList<Mensaje> getAllMensaje(String pCaracter, String pEstado)
			throws DAOException {

		ArrayList<Mensaje> retorna = new ArrayList<Mensaje>();

		String query = "SELECT * FROM mensaje WHERE estado = '" + pEstado
				+ "' AND caracter = '" + pCaracter
				+ "' ORDER BY id_mensaje DESC";

		Connection conexion = DBManager.getInstance().connect();

		System.out.println(query);
		try {
			Statement s = conexion.createStatement();
			ResultSet resultSet = s.executeQuery(query);

			while (resultSet.next()) {

				int idMensaje = resultSet.getInt("id_mensaje");
				String de = resultSet.getString("de");
				String para = resultSet.getString("para");
				String cc = resultSet.getString("cc");
				String cco = resultSet.getString("cco");
				String asunto = resultSet.getString("asunto");
				String texto = resultSet.getString("texto");
				String fecha = resultSet.getString("fecha");
				String estado = resultSet.getString("estado");
				String caracter = resultSet.getString("caracter");

				Mensaje mensaje = new Mensaje();

				mensaje.setIdMensaje(idMensaje);
				mensaje.setDe(de);
				mensaje.setPara(para);
				mensaje.setCc(cc);
				mensaje.setCco(cco);
				mensaje.setAsunto(asunto);
				mensaje.setTexto(texto);
				mensaje.setFecha(fecha);
				mensaje.setEstado(estado);
				mensaje.setCaracter(caracter);

				retorna.add(mensaje);
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
		return retorna;
	}

	public Mensaje getMensajeById(int pIdMensaje) throws DAOException {
		Mensaje mensaje = null;
		String query = "SELECT * FROM mensaje WHERE id_mensaje = " + pIdMensaje;
		Connection conexion = DBManager.getInstance().connect();

		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery(query);
			if (rs.next()) {
				mensaje = new Mensaje();
				mensaje.setIdMensaje(pIdMensaje);
				mensaje.setDe(rs.getString("de"));
				mensaje.setPara(rs.getString("para"));
				mensaje.setCc(rs.getString("cc"));
				mensaje.setCco(rs.getString("cco"));
				mensaje.setAsunto(rs.getString("asunto"));
				mensaje.setTexto(rs.getString("texto"));
				mensaje.setFecha(rs.getString("fecha"));
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
		return mensaje;
	}

	public int getMaxIdMensaje() throws DAOException {

		int maximo = 0;

		String query = "SELECT MAX(id_mensaje) maximo FROM mensaje";
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
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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

	@Override
	public void updateEstadoMensaje(String pEstado, int pIdMensaje)
			throws DAOException {
		String query = "UPDATE mensaje SET ESTADO = '" + pEstado
				+ "' WHERE id_mensaje = " + pIdMensaje;

		Connection conexion = DBManager.getInstance().connect();

		try {
			Statement s = conexion.createStatement();
			s.executeUpdate(query);
			conexion.commit();
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
	}

	@Override
	public void mensajeUpdate(Mensaje pMensaje) throws DAOException {
		String fecha = (new SimpleDateFormat(Constantes.FORMATO_FECHA))
				.format(new Date());
		String query = "UPDATE mensaje SET PARA = '" + pMensaje.getPara()
				+ "', CC='" + pMensaje.getCc() + "', CCO='" + pMensaje.getCco()
				+ "', ASUNTO='" + pMensaje.getAsunto() + "', TEXTO='"
				+ pMensaje.getTexto() + "', FECHA='" + fecha
				+ "' WHERE id_mensaje = " + pMensaje.getIdMensaje();

		System.out.println(query);
		Connection conexion = DBManager.getInstance().connect();

		try {
			Statement s = conexion.createStatement();
			s.executeUpdate(query);
			conexion.commit();
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
	}

	@Override
	public void mensajeDelete(Mensaje pMensaje) throws DAOException {
		int idMensaje = pMensaje.getIdMensaje();
		String query = "DELETE FROM mensaje WHERE id_mensaje =" + idMensaje;
		Connection conexion = DBManager.getInstance().connect();
		try {
			Statement s = conexion.createStatement();
			s.executeUpdate(query);
			conexion.commit();
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
	}

	@Override
	public void mensajePendienteEnvioInsert(Mensaje pMensaje, String pEstado)
			throws DAOException {

		String fecha = (new SimpleDateFormat(Constantes.FORMATO_FECHA))
				.format(new Date());

		Connection conexion = DBManager.getInstance().connect();
		try {
			String query = "INSERT INTO mensaje (id_mensaje, de, para, cc, cco, asunto, texto, estado, fecha, caracter) VALUES ("
					+ pMensaje.getIdMensaje()
					+ ", '"
					+ pMensaje.getDe()
					+ "', '"
					+ pMensaje.getPara()
					+ "','"
					+ pMensaje.getCc()
					+ "','"
					+ pMensaje.getCco()
					+ "','"
					+ pMensaje.getAsunto()
					+ "','"
					+ pMensaje.getTexto()
					+ "','"
					+ pEstado
					+ "','"
					+ fecha + "', 'DE')";

			System.out.println(query);
			Statement s = conexion.createStatement();
			s.executeUpdate(query);
			conexion.commit();
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
	}

	@Override
	public void mensajeRecibidoInsert(Mensaje pMensaje) throws DAOException {

		String estado = "RECIBIDO_OK";
		String fecha = (new SimpleDateFormat(Constantes.FORMATO_FECHA))
				.format(new Date());
		Connection conexion = DBManager.getInstance().connect();

		try {
			String query = "INSERT INTO mensaje (id_mensaje, de, para, cc, cco, asunto, texto, estado, fecha, caracter) VALUES ("
					+ pMensaje.getIdMensaje()
					+ ", '"
					+ pMensaje.getDe()
					+ "', '"
					+ pMensaje.getPara()
					+ "','"
					+ pMensaje.getCc()
					+ "','"
					+ pMensaje.getCco()
					+ "','"
					+ pMensaje.getAsunto()
					+ "','"
					+ pMensaje.getTexto()
					+ "','"
					+ estado
					+ "','"
					+ fecha + "', 'PARA')";

			System.out.println(query);
			Statement s = conexion.createStatement();
			s.executeUpdate(query);
			conexion.commit();
		} catch (SQLException e) {
			try {
				conexion.rollback();
				e.printStackTrace();
				throw new DAOException(
						"Error al conectar con la base de datos", e);
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
	}
}