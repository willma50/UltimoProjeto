package veicular.logica.dominio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import veicular.funcoes.FuncoesData;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.logica.persistencia.sql.VeiculoDaoSql;

public class Imposto {
	
	private double valorMedio;
	private double valorTotal;
	
	
	
	public static double getCalculoImposto(Veiculo veiculo) throws Exception{
		
		int dias = verificaPrazo();		
		double vImposto = veiculo.getBaseCalculo() * veiculo.getAliquota();		
		int i=0;
		if(dias < 0){
			System.out.println("Multa");
			vImposto = vImposto + (vImposto * 0.05); // multa
			dias = dias*-1;
			while(dias > i){
				vImposto = vImposto + (vImposto * 0.005); //juros
				i++;
			}
		}
		System.out.println("imposto: "+vImposto);
		return vImposto;		
	}
	 
	
	public static int verificaPrazo() throws Exception {
		String dataAtual = FuncoesData.getDate();
		String prazo = "31-01-"+FuncoesData.getAno();
		//System.out.println(FuncoesData.dataDiff(FuncoesData.formataData("03-02-2016"), FuncoesData.formataData(prazo)));
		int deferenca = FuncoesData.dataDiff(FuncoesData.formataData(dataAtual), FuncoesData.formataData(prazo));
		return deferenca;
	}


	/*public static Double valorTotalImpostoFrota(String numCSProprietario) throws Exception{
		
		VeiculoDaoIF vDao = new VeiculoDaoSql();
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = vDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		
		Double somaImposto = (double) 0;
		while(itV.hasNext()){
			Veiculo v =itV.next();
			if(v.getClasse()!=3){
				somaImposto+=v.getImpostoDevido();
			}
		}
		somaImposto = calcDescontoImposto(somaImposto);
		return somaImposto;		
	}
	
	public static Double valorMedioImpostoFrota(String numCSProprietario) throws Exception{
		VeiculoDaoIF vDao = null;
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = vDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		Double mediaImposto = (double) 0;
		int cont=0;
		while(itV.hasNext()){
			cont++;
			Veiculo v =itV.next();
			if(v.getClasse()!=3){
				mediaImposto+=v.getImpostoDevido();
			}
		}
		mediaImposto = calcDescontoImposto(mediaImposto);
		return mediaImposto/cont;		
	}*/

}
