package dao;

import dbImpl.DAOException;
import entidades.Contacto;

public interface IContactoDAO {

	public void contactoInsert(Contacto pContacto) throws DAOException;

	public void contactoUpdate(Contacto pContacto) throws DAOException;

	public void contactoDelete(Contacto pContacto) throws DAOException;

	public Contacto getContactoById(int pId) throws DAOException;

	public int getMaxIdContacto() throws DAOException;
}