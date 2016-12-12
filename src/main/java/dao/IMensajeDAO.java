package dao;

import java.util.ArrayList;

import dbImpl.DAOException;
import entidades.Mensaje;

public interface IMensajeDAO {

	public void mensajePendienteEnvioInsert(Mensaje pMensaje, String pEstado)
			throws DAOException;

	public void mensajeRecibidoInsert(Mensaje pMensaje) throws DAOException;

	public Mensaje getMensajeById(int pId) throws DAOException;

	public ArrayList<Mensaje> getAllMensaje(String pCaracter, String pEstado)
			throws DAOException;

	public void updateEstadoMensaje(String pEstado, int idMenasje)
			throws DAOException;

	public void mensajeDelete(Mensaje pMensaje) throws DAOException;

	public void mensajeUpdate(Mensaje pMensaje) throws DAOException;

	public int getMaxIdMensaje() throws DAOException;
}