package veicular.logica.dominio;



public class DAFIndividual {
	private String placa;
	private int anoFabricacao;
	private String data;
	private int classe;
	private Double baseCalculo;
	private Double aliquota;
	private Double impostoDevido;

	public DAFIndividual(String placa, int anoFabricacao, String data, int classe, Double baseCalculo, Double aliquota, Double impostoDevido) {
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

	public Double getBaseCalculo() {
		return baseCalculo;
	}

	public void setBaseCalculo(Double baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	public Double getAliquota() {
		return aliquota;
	}

	public void setAliquota(Double aliquota) {
		this.aliquota = aliquota;
	}

	public Double getImpostoDevido() {
		return impostoDevido;
	}

	public void setImpostoDevido(Double impostoDevido) {
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



