package br.com.manell.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.manell.livraria.modelo.Usuario;
import br.com.manell.livraria.transaction.Transacional;

public class UsuarioDao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;

	@Transacional
	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
	}

}
