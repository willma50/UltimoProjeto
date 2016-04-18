package veicular.logica.app;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import veicular.funcoes.FuncoesData;
import veicular.logica.dominio.DAFFrota;
import veicular.logica.dominio.Veiculo;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.logica.persistencia.sql.VeiculoDaoSql;

public class DAFFrotaLogica implements DAFFrotaLogicaIF{

	
	private VeiculoDaoIF veiculoDao;
	private VeiculoLogicaIF appLogica;

	public void imprimir(String numCSProprietario) throws Exception{
		
		this.veiculoDao = new VeiculoDaoSql();		
		Collection<Veiculo> listVeiculos = this.veiculoDao.findByProprietario(numCSProprietario);		
		//listVeiculo = this.veiculoDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itVeiculo = listVeiculos.iterator();
		//ArrayList<Veiculo> listVeiculos = new ArrayList<Veiculo>();
		////VeiculoDaoSql vSql = new VeiculoDaoSql();
		//listVeiculos = vSql.findByProprietario(numCSProprietario);
		
		String data=FuncoesData.getDate();
		double totalImposto;
		
		this.appLogica = new VeiculoLogica();
		totalImposto = this.appLogica.valorTotalImpostoFrota(numCSProprietario);
		
		Iterator<Veiculo> itV = listVeiculos.iterator();
		ArrayList<String> placas = new ArrayList<String>();
		String aux;
		String proprietario = numCSProprietario;
		do{
	//		Proprietario p = itV.next().getProprietario();
//			p.setNome(numCSProprietario);
			Veiculo v =itV.next();
			aux = v.getPlaca() + "\n";
			placas.add(aux);
		}while(itV.hasNext());
					
		DAFFrota dafF = new DAFFrota(proprietario, placas, data, totalImposto); 
		
		System.out.println(dafF.toString());
	}
	

	
	

}
