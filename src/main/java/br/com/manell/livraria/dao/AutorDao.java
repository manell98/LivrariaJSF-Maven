package br.com.manell.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.manell.livraria.modelo.Autor;

public class AutorDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

}
