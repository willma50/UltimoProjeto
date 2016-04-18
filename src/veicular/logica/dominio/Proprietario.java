package veicular.logica.dominio;

public class Proprietario {

	private String  NCS;
	private String nome;
	
	public Proprietario(String nome, String nCS) {
		this.setNCS(nCS); 
		this.setNome(nome);
	}
	
	public String getNCS() {
		return this.NCS;
	}
	public void setNCS(String nCS) {
		this.NCS = nCS;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
