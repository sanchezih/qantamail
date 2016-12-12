package bo;

import dao.IContactoDAO;
import dbImpl.DAOException;
import entidades.Contacto;

public class ContactoBO {

	private IContactoDAO iContactoDAO;

	public void contactoInsert(Contacto pContacto) {
		try {
			iContactoDAO.contactoInsert(pContacto);
		} catch (DAOException e) {
			/* No hago nada */
		}
	}

	public void contactoDelete(Contacto pContacto) {
		try {
			iContactoDAO.contactoDelete(pContacto);
		} catch (DAOException e) {
			/* No hago nada */
		}
	}

	public void contactoUpdate(Contacto pContacto) {
		try {
			iContactoDAO.contactoUpdate(pContacto);
		} catch (DAOException e) {
			/* No hago nada */
		}
	}

	public void setDao(IContactoDAO dao) {
		this.iContactoDAO = dao;
	}

	public Contacto getContactoById(int pId) throws BOException {
		try {
			return iContactoDAO.getContactoById(pId);
		} catch (DAOException e) {
			throw new BOException("Error de Base de Datos", e);
		}
	}

	public int getMaxIdContacto() throws BOException {
		try {
			return iContactoDAO.getMaxIdContacto();
		} catch (DAOException e) {
			throw new BOException("Error de Base de Datos", e);
		}
	}
}
