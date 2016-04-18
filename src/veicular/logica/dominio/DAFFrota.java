package veicular.logica.dominio;

import java.text.ParseException;
import java.util.ArrayList;

import veicular.funcoes.FuncoesData;

public class DAFFrota {
	private String numCSProprietario;
	private String data;
	private ArrayList<String> placas;
	private int numVeiculos;
	private Double impostoDevido;

	public DAFFrota(String numCSProprietario, ArrayList<String> placas2, String data, Double impostoDevido) throws ParseException {
		this.setNumCSProprietario(numCSProprietario);
		this.setData(FuncoesData.getDate());
		this.setPlaca(placas2);
		this.setImpostoDevido(impostoDevido);
	}

	private void setData(String data) {
		this.data=data;		
	}

	public ArrayList<String> getPlaca() {
		return this.placas;
	}

	public void setPlaca(ArrayList<String> placas2) {
		this.placas = placas2;
	}

	public Double getImpostoDevido() {
		return impostoDevido;
	}

	public void setImpostoDevido(Double impostoDevido) {
		this.impostoDevido = impostoDevido;
	}
	
	public void calcImpostoDevido(Double impostoDevido) {
		this.impostoDevido = impostoDevido;
	}
	
	public String toString(){
		String imprime = "Numero da Contribuição Social: " + this.numCSProprietario + "\n"; 
		imprime+= "Placas :" + this.placas + "\n";
		imprime+= "Data de pagamento: " + this.data + "\n";
		imprime+= "Imposto :" + this.impostoDevido + "\n";
		
		return imprime;
	}



	public String getNumCSProprietario() {
		return numCSProprietario;
	}



	public void setNumCSProprietario(String numCSProprietario) {
		this.numCSProprietario = numCSProprietario;
	}

}

