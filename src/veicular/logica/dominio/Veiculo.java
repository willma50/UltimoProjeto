package veicular.logica.dominio;

import veicular.funcoes.FuncoesData;

public abstract class Veiculo {
	
	private int id;
	protected String placa;
	private int anoFabricacao;
	private double valorCompra;
	private Proprietario proprietario;
	private Double aliquota;
	private Double impostoDevido;
	private FuncoesData funcao;
	private int frota;
	private int classe;
	private long numInscFiscal; //tem que add ao construtor e ao banco
	
	public Veiculo(){
		
	}
	
	public Veiculo(String placa, int anoFabricacao, double valorCompra, Proprietario proprietario, int frota){
		this(placa, anoFabricacao, valorCompra, proprietario);
		this.setFrota(frota);
	}
	
	public Veiculo(String placa, int anoFabricacao, double valorCompra, Proprietario proprietario){
		this.setPlaca(placa);
		this.setAnoFabricacao(anoFabricacao);		
		this.setValorCompra(valorCompra);
		this.proprietario = proprietario;
	}
	
	public double setImpostoDevido() {
		return this.impostoDevido;
	}
	
	public double getBaseCalculo(){
		double base = 0, valor = this.getValorCompra();
		int i = 0;
		int qtdAno = (new FuncoesData().getAno()) - this.getAnoFabricacao();
		while(qtdAno > i){
			base = valor - (valor * (0.05));
			valor = base;
			i++;
		}
		return base;
	}
	
	public int getFrota() {
		return frota;
	}

	public void setFrota(int frota) {
		this.frota = frota;
	}
	
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	public double getValorCompra() {
		return valorCompra;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPlaca() {
		return this.placa;
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

	public Proprietario getProprietario() {
		return this.proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Double getAliquota() {
		return this.aliquota;
	}

	public Double getImpostoDevido() {
		//this.impostoDevido = aliquota*this.getBaseCalculo(); 
		return this.impostoDevido;
	}


	public FuncoesData getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncoesData funcao) {
		this.funcao = funcao;
	}

	public int getClasse() {
		return this.classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public long getNumInscFiscal() {
		return numInscFiscal;
	}

	public void setNumInscFiscal(long numInscFiscal) {
		this.numInscFiscal = numInscFiscal;
	}
	
	
}
