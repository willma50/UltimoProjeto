package veicular.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import veicular.logica.app.DAFFrotaLogica;
import veicular.logica.app.DAFFrotaLogicaIF;

public class ImprimirDAFFrota implements ImprimirIF{
	
	private DAFFrotaLogicaIF DAFLogica;
	String numCSProprietario;

	public void imprime() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Informe o numero de Contribuição Socal: ");
		numCSProprietario = reader.readLine();
		
		DAFLogica = new DAFFrotaLogica();
		DAFLogica.imprimir(numCSProprietario);
	}
	
	
	//teste
	public static void main(String[] args) throws Exception {
		ImprimirDAFFrota ID = new ImprimirDAFFrota();
		ID.imprime();
	}

}
