package veicular.logica.dominio;

public class Aeronave extends Veiculo{

	private static final double aliquota = 0.025;
	
	public Aeronave(String placa,
			int anoFabricacao, double  valorCompra, Proprietario proprietario, int frota) {
		super(placa, anoFabricacao, valorCompra, proprietario, frota);
	}
	
	public Aeronave(String placa,
			int anoFabricacao, double  valorCompra, Proprietario proprietario){
		super(placa, anoFabricacao, valorCompra, proprietario);
	}

	@Override
	public Double getImpostoDevido() {
		return Aeronave.aliquota * this.getBaseCalculo();
	}
	
	public Double getAliquota(){
		return Aeronave.aliquota;
	}

}
