package dbImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import basics.DBManager;

import dao.IUsuarioDAO;

public class UsuarioDAOImpl implements IUsuarioDAO {

	public boolean getUsuarioByNombreUsuario(String pNombreUsuario, String pPassword) throws DAOException {

		String query = "SELECT * FROM usuario WHERE nombre_usuario = '" + pNombreUsuario + "'AND password='" + pPassword
				+ "' ";
		System.out.println(query);
		Connection conexion = DBManager.getInstance().connect();

		try {
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery(query);
			if (rs.next()) {
				return true;
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
		return false;
	}
}
