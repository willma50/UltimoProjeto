package veicular.logica.dominio;

public class Embarcacoes extends Veiculo{

	private static final double aliquota = 0.035;
	
	public Embarcacoes(String placa,
			int anoFabricacao, double  valorCompra, Proprietario proprietario, int frota) {
		super(placa, anoFabricacao, valorCompra, proprietario, frota);
	}

	public Embarcacoes(String placa, int anoFabricacao, double  valorCompra, Proprietario proprietario){
		super(placa, anoFabricacao, valorCompra, proprietario);
	}
	
	@Override
	public Double getImpostoDevido() {
		return Embarcacoes.aliquota * this.getBaseCalculo();
	}
	
	public Double getAliquota(){
		return Embarcacoes.aliquota;
	}

}
