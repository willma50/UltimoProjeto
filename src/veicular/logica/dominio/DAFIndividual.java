package veicular.logica.dominio;



public class DAFIndividual {
	private String placa;
	private int anoFabricacao;
	private String data;
	private int classe;
	private double baseCalculo;
	private double aliquota;
	private double impostoDevido;

	public DAFIndividual(String placa, int anoFabricacao, String data, int classe, double baseCalculo, double aliquota, double impostoDevido) {
		this.setPlaca(placa);
		this.setAnoFabricacao(anoFabricacao);
		this.setData(data);
		this.setClasse(classe);		
		this.setBaseCalculo(baseCalculo);
		this.setAliquota(aliquota);
		this.setImpostoDevido(impostoDevido);
		
	}



	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public double getBaseCalculo() {
		return baseCalculo;
	}

	public void setBaseCalculo(double baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	public double getAliquota() {
		return aliquota;
	}

	public void setAliquota(double aliquota) {
		this.aliquota = aliquota;
	}

	public double getImpostoDevido() {
		return impostoDevido;
	}

	public void setImpostoDevido(double impostoDevido) {
		this.impostoDevido = impostoDevido;
	}
	
	public String toString(){
		String imprime =  "Placa :" + this.placa + "\n";
		imprime+= "Ano de fabricação :" + this.anoFabricacao + "\n";
		imprime+= "Data de pagamento: " + this.data + "\n";
		imprime+= "Classe do veículo :" + this.classe + "\n";
		imprime+= "Base de calculo" +  this.baseCalculo + "\n";
		imprime+= "Aliquota :" + this.aliquota + "\n";
		imprime+= "Imposto :" + this.impostoDevido + "\n";
		
		return imprime;
	}

}



