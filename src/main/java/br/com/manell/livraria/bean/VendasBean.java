package br.com.manell.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.manell.livraria.dao.VendasDao;
import br.com.manell.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendasDao vendasDao;

	public BarChartModel getVendasModel() {
		
        BarChartModel model = new BarChartModel();
        model.setTitle("Vendas - 2019");
        model.setShowPointLabels(true);
        model.setLegendPosition("ne");
        model.setAnimate(true);
 
        ChartSeries vendaSerie = new ChartSeries();
        vendaSerie.setLabel("2018");
        
        List<Venda> vendas = getVendas();
        
        for (Venda venda : vendas) {			
        	vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
        
        model.addSeries(vendaSerie);
 
        return model;
    }
	
	public List<Venda> getVendas() {
		
		List<Venda> vendas = vendasDao.listaTodos();
		
		return vendas;	
	}

}
