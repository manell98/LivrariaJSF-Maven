package br.com.manell.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manell.livraria.dao.AutorDao;
import br.com.manell.livraria.dao.LivroDao;
import br.com.manell.livraria.modelo.Autor;
import br.com.manell.livraria.modelo.Livro;
import br.com.manell.livraria.transaction.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {
    private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;

	private List<Livro> livros;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	public void carregarLivroPeloId() {
		this.livro = this.livroDao.buscaPorId(livroId);
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros() {
				
		if(this.livros == null) {
			this.livros = livroDao.listaTodos();
		}	
		return livros;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}

		if(this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
			this.livros = livroDao.listaTodos();
		}
		
		this.livro = new Livro();	
	}
	
	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		this.livroDao.remove(livro);
		this.livros = livroDao.listaTodos();
	}
	
	public void removerAutorDoLivro(Autor autor) {
		System.out.println("Removendo autor");
		this.livro.removeAutor(autor);
	}
	
	public void carregar(Livro livro) {
		System.out.println("Carregando livro: " + livro.getTitulo());
		this.livro = this.livroDao.buscaPorId(livro.getId());
	}
	
	public void gravarAutor() {
		Autor autor = this.autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
	    String valor = value.toString();
	    if (!valor.startsWith("1")) {
	        throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
	    }
	}
	
	public String formAutor() {
		System.out.println("Chamando o formulário de autor");
		return "autor?faces-redirect=true";
	}

}
