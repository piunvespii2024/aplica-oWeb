package livraria.dao;

import java.util.List;

import org.hibernate.Query;

import livraria.entity.Usuario;
import webf.dao.DAO;
import webf.dao.DAOException;

@SuppressWarnings("unchecked")
public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	/**
	 * Verifica se um usuário existe, com base no seu e-mail
	 * @param email
	 * @return
	 * @throws DAOException
	 */
	public boolean existeUsuario(String email) throws DAOException {
		Query q = query("SELECT COUNT(*) FROM Usuario u WHERE upper(u.email) = :email");
		q.setParameter("email", email.toUpperCase());
		List<Long> results = q.list();
		long count = results.get(0);
		return count > 0;
	}
	
	/**
	 * Obtém um usuário com base no e-mail
	 * @param email
	 * @return
	 * @throws DAOException
	 */
	public Usuario getUsuarioByEmail(String email) throws DAOException {
		Query q = query("FROM Usuario u WHERE upper(u.email) = :email");
		q.setParameter("email", email.toUpperCase());
		return (Usuario) q.uniqueResult();
	}
}
