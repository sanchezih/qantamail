package bo;

import dao.IMensajeDAO;
import dbImpl.DAOException;
import entidades.Mensaje;

public class MensajeBO {

	private IMensajeDAO iMensajeDAO;

	public void mensajePendienteEnvioInsert(Mensaje pMensaje, String pEstado)
			throws BOException {
		try {
			iMensajeDAO.mensajePendienteEnvioInsert(pMensaje, pEstado);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public void mensajeRecibidoInsert(Mensaje pMensaje) throws BOException {
		try {
			iMensajeDAO.mensajeRecibidoInsert(pMensaje);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public void updateEstadoMensaje(String pEstado, int pIdMensaje)
			throws BOException {
		try {
			iMensajeDAO.updateEstadoMensaje(pEstado, pIdMensaje);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public void mensajeUpdate(Mensaje pMensaje) throws BOException {
		try {
			iMensajeDAO.mensajeUpdate(pMensaje);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public Mensaje getMensajeById(int pId) throws BOException {
		try {
			return iMensajeDAO.getMensajeById(pId);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public void setDao(IMensajeDAO pIMensajeDAO)  {
		this.iMensajeDAO = pIMensajeDAO;
	}

	public int getMaxIdMensaje() throws BOException {
		try {
			return iMensajeDAO.getMaxIdMensaje();
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}

	public void mensajeDelete(Mensaje pMensaje) throws BOException {
		try {
			iMensajeDAO.mensajeDelete(pMensaje);
		} catch (DAOException e) {
			throw new BOException("Error de base de datos", e);
		}
	}
}
