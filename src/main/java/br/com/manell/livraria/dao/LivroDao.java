package br.com.manell.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.manell.livraria.modelo.Livro;

public class LivroDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Livro>(this.em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

}
