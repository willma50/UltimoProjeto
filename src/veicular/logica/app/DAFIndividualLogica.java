package veicular.logica.app;


import veicular.funcoes.FuncoesData;
import veicular.logica.dominio.DAFIndividual;
import veicular.logica.dominio.Veiculo;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.logica.persistencia.sql.VeiculoDaoSql;

public class DAFIndividualLogica implements DAFIndividualLogicaIF{	

	
	public void imprimir(String placa)throws Exception{
		
		DAFIndividual dafI = null;
		VeiculoDaoIF VSql = new VeiculoDaoSql();
		Veiculo v = VSql.findByPlaca(placa);
		
		Double aliquota = v.getAliquota();
		Double baseCalculo = v.getBaseCalculo();
		Double impostoDevido = v.getImpostoDevido();
		
		String data=FuncoesData.getDate();
		int classe = v.getClasse();
			int anoFabricacao = v.getAnoFabricacao();
			dafI = new DAFIndividual(placa, anoFabricacao, data, classe, baseCalculo, aliquota, impostoDevido);	
		
		//
			
		System.out.println(dafI.toString());
	} 
}
	