package br.com.manell.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.manell.livraria.dao.UsuarioDao;
import br.com.manell.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private FacesContext context;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		System.out.println("Fazendo o login do usuario " + this.usuario.getEmail());
		
		boolean existe = usuarioDao.existe(this.usuario);
		
		if(existe) {			
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);		
			return "livro?faces-redirect=true";			
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usu�rio n�o econtrado!"));
		
		return "login?faces-redirect=true";	
	}
	
	public String deslogar() {
		
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}

}
