package veicular.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import veicular.logica.app.DAFIndividualLogica;
import veicular.logica.app.DAFIndividualLogicaIF;

public class ImprimirDAFIndividual implements ImprimirIF{
	
	private DAFIndividualLogicaIF DAFLogica;
	String placa;

	public void imprime() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Informe a placa do veículo: ");
		placa = reader.readLine();
		
		DAFLogica = new DAFIndividualLogica();
		DAFLogica.imprimir(placa);	
	}
	
	//teste
	
	public static void main(String[] args) throws Exception {
		ImprimirDAFIndividual ID = new ImprimirDAFIndividual();
		ID.imprime();
	}

}

