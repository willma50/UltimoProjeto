package veicular.logica.dominio;

public class Terrestres extends Veiculo{

	private final static double aliquota = 0.01; 
	
	public Terrestres(String placa, int anoFabricacao, double valorCompra, Proprietario proprietario, int frota) {
		super(placa, anoFabricacao, valorCompra, proprietario, frota);
	}

	public Terrestres(String placa, int anoFabricacao, double valorCompra, Proprietario proprietario){
		super(placa, anoFabricacao, valorCompra, proprietario);
	}

	@Override
	public Double getImpostoDevido() {
		return Terrestres.aliquota * this.getBaseCalculo();
	}

		public Double getAliquota(){
			return Terrestres.aliquota;
		}

}
