package br.com.manell.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent argevent0) {
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Fase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}

}
