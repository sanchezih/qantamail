package bo;

import dao.IMensajeDAO;
import dao.IUsuarioDAO;
import dbImpl.DAOException;

public class UsuarioBO {

	private IUsuarioDAO iUsuarioDAO;

	public boolean getUsuarioByNombreUsuario(String name, String password) throws BOException {
		boolean si = false;
		try {
			si= iUsuarioDAO.getUsuarioByNombreUsuario(name, password);
			return si;
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}
	public void setDao(IUsuarioDAO pIUsuarioDAO)  {
		this.iUsuarioDAO = pIUsuarioDAO;
	}

}