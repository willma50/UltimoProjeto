package veicular.logica.app;


import java.util.ArrayList;
import java.util.Iterator;

import veicular.funcoes.FuncoesData;
import veicular.logica.dominio.DAFFrota;
import veicular.logica.dominio.Veiculo;
import veicular.logica.persistencia.sql.VeiculoDaoSql;

public class DAFFrotaLogica implements DAFFrotaLogicaIF{

	
	public void imprimir(String numCSProprietario) throws Exception{
		
		ArrayList<Veiculo> listVeiculos = new ArrayList<Veiculo>();
		VeiculoDaoSql vSql = new VeiculoDaoSql();
		listVeiculos = vSql.findByProprietario(numCSProprietario);
		
		String data=FuncoesData.getDate();
		Double totalImposto; 
		
		VeiculoLogica vl = new VeiculoLogica();
		totalImposto = vl.valorTotalImpostoFrota(numCSProprietario);
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
	

	/*
	 
	public double getImposto() throws Exception{
		String dataAtual = FuncoesData.getDate();
		String prazo = "31-01-"+FuncoesData.getAno();
		System.out.println(FuncoesData.dataDiff(FuncoesData.formataData("03-02-2016"), FuncoesData.formataData(prazo)));
		int qtd = FuncoesData.dataDiff(FuncoesData.formataData(dataAtual), FuncoesData.formataData(prazo));
		this.imposto = 100 * 2.5;//this.veiculo.getBaseCalculo() * this.veiculo.getAliquota();
		int i=0;
		if(qtd < 0){
			System.out.println("Multa");
			this.imposto = this.imposto + (this.imposto * 0.05);
			qtd = qtd*-1;
			while(qtd > i){
				this.imposto = this.imposto + (this.imposto * 0.005);
				i++;
			}
		}
		System.out.println("imposto: "+this.imposto);
		
		return this.imposto;
		
	}
	 * */
	

}
