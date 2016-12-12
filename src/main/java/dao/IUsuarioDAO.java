package dao;

import dbImpl.DAOException;

public interface IUsuarioDAO {

	public boolean getUsuarioByNombreUsuario(String nombre, String password) throws DAOException;

}
