package veicular.logica.dominio;

public class Adaptados extends Veiculo{
	
	private  final static double aliquota = 0.0;


	public Adaptados(){
		super();
	}
	
	//isento
	@Override
	public double setImpostoDevido() {
		return this.getImpostoDevido();
	}

}
