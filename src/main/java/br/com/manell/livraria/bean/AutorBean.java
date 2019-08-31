package br.com.manell.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manell.livraria.dao.AutorDao;
import br.com.manell.livraria.modelo.Autor;
import br.com.manell.livraria.transaction.Transacional;

@Named
@ViewScoped // javax.faces.view.ViewScoped
public class AutorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();
	private Integer autorId;

	@Inject
	private AutorDao autorDao;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPeloId() {
		this.autor = this.autorDao.buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		} else {
			this.autorDao.atualiza(this.autor);
		}
			
		this.autor = new Autor();
		
		return "livro?faces-redirect=true ";
	}
	
	@Transacional
	public void remover(Autor autor) {
		this.autorDao.remove(autor);
	}
	
	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}
}
