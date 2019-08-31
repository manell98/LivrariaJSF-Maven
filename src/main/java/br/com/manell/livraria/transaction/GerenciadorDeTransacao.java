package br.com.manell.livraria.transaction;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;

	@AroundInvoke
	public Object executa(InvocationContext context) throws Exception {

		// abre a transacao
		em.getTransaction().begin();
		
		// chama os daos que precisam de um transacao
		Object resultado = context.proceed();
		
		// commita a transacao
		em.getTransaction().commit();
		
		return resultado;
	}
	
}
