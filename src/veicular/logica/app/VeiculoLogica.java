package veicular.logica.app;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import veicular.funcoes.FuncoesData;
import veicular.logica.dominio.Aeronave;
import veicular.logica.dominio.DAFFrota;
import veicular.logica.dominio.DAFIndividual;
import veicular.logica.dominio.Embarcacoes;
import veicular.logica.dominio.Imposto;
import veicular.logica.dominio.Proprietario;
import veicular.logica.dominio.Terrestres;
import veicular.logica.dominio.Veiculo;
import veicular.logica.persistencia.sql.ProprietarioDaoIF;
import veicular.logica.persistencia.sql.ProprietarioDaoSql;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.logica.persistencia.sql.VeiculoDaoSql;
import veicular.ui.TableModelos;

public class VeiculoLogica implements VeiculoLogicaIF{
	
	private static final int FROTA_N = 0;
	private static final int FROTA_S = 1;
	private static final int TIPO_AERONAVE = 0;
	private static final int TIPO_EMBARCACOES = 1;	
	private static final int TIPO_TERRESTRE = 2;
	private static final int TIPO_OUTRO = 3;
	private VeiculoDaoIF veiculoDao;
	private ProprietarioDaoIF ProprietarioDao;
	private FuncoesData funcao;

	@Override
	public void addVeiculo(String placa, int anoFabricacao, int classe, double valorCompra, String nomeProprietario) throws Exception {
		
		if(classe == 0)
			this.addAeronave(placa, anoFabricacao, valorCompra, nomeProprietario);
		if(classe == 1)
			this.addEmbarcacoes(placa, anoFabricacao, valorCompra, nomeProprietario);
		if(classe == 2)
			this.addTerrestres(placa, anoFabricacao, valorCompra, nomeProprietario);
			
	}
	
	public void addAeronave(String placa, int anoFabricacao, double valorCompra, String nomeProprietario) throws Exception{
		Aeronave aeronave = null;
		this.ProprietarioDao = new ProprietarioDaoSql();
		Proprietario proprietario = this.ProprietarioDao.findByNome(nomeProprietario);
		System.out.println(proprietario.getNCS());
		if(this.getQtdVeiculosVinculados(proprietario)){
			aeronave = new Aeronave(placa, anoFabricacao, valorCompra, proprietario, FROTA_S);
		}else
			aeronave = new Aeronave(placa, anoFabricacao, valorCompra, proprietario, FROTA_N);
		System.out.println("anos: "+aeronave.getBaseCalculo());
		this.veiculoDao.salvar(aeronave);
		
	}

	public void addEmbarcacoes(String placa, int anoFabricacao, double valorCompra, String nomeProprietario) throws Exception{
		Embarcacoes embarcacoes = null;
		this.ProprietarioDao = new ProprietarioDaoSql();
		Proprietario proprietario = this.ProprietarioDao.findByNome(nomeProprietario);
		if(this.getQtdVeiculosVinculados(proprietario)){
			embarcacoes = new Embarcacoes(placa, anoFabricacao, valorCompra, proprietario, FROTA_S);
		}else
			embarcacoes = new Embarcacoes(placa, anoFabricacao, valorCompra, proprietario, FROTA_N);
		System.out.println("base de calculo: "+embarcacoes.getBaseCalculo());
		this.veiculoDao.salvar(embarcacoes);
	}	
	
	
	public void addTerrestres(String placa, int anoFabricacao, double valorCompra, String nomeProprietario) throws Exception {
		Terrestres terrestre = null;
		this.ProprietarioDao = new ProprietarioDaoSql();
		Proprietario proprietario = this.ProprietarioDao.findByNome(nomeProprietario);
		if(this.getQtdVeiculosVinculados(proprietario)){
			terrestre = new Terrestres(placa, anoFabricacao, valorCompra, proprietario, FROTA_S);
		}else
			terrestre = new Terrestres(placa, anoFabricacao, valorCompra, proprietario, FROTA_N);
			
		System.out.println("base : "+terrestre.getBaseCalculo());
		this.veiculoDao.salvar(terrestre);
	}
	
	@Override
	public Collection<String> findAll() throws Exception { 
		Collection<Veiculo> veiculo = this.veiculoDao.findAll();
		Set<String> placaVeiculo = new HashSet<String>();
		Iterator<Veiculo> itVeiculo = veiculo.iterator();
		
		while(itVeiculo.hasNext()){
			placaVeiculo.add(itVeiculo.next().getPlaca());
		}
		
		return placaVeiculo;
	}
	
	@Override
	public void setPersistencia(VeiculoDaoIF veiculoDAO) {
		this.veiculoDao = veiculoDAO;
	}

	@Override
	public void removerVeiculo(String placa) throws Exception {
		this.veiculoDao.remove(placa);
	}

	@Override
	public int buscaAno() {	
		return (new FuncoesData().getAno());
	}

	@Override
	public Collection<Veiculo> listarVeiculo() throws Exception {		
		return this.veiculoDao.findAll();
	}

	@Override
	public Veiculo buscaPorPlaca(String placa) throws Exception {		
		return this.veiculoDao.findByPlaca(placa);
	}

	@Override
	public Collection<Veiculo> buscaPorPartesVeiculo(String placa) throws Exception {		
		return this.veiculoDao.buscaPorPartesVeiculo(placa);
	}
	
	//colocar um comentário aqui
	private boolean getQtdVeiculosVinculados(Proprietario proprietario) throws SQLException {
		if(this.veiculoDao.getVeiculosNCS(proprietario)){
			return true;
		}
			return false;
	}

	public FuncoesData getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncoesData funcao) {
		this.funcao = funcao;
	}
	
	@Override
	public double valorTotalImpostoFrota(String numCSProprietario) throws Exception{
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = this.veiculoDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		double somaImposto = 0;
		
		while(itV.hasNext()){
			Veiculo veiculo =itV.next();
			if(veiculo.getClasse()!=3){
				somaImposto += Imposto.getCalculoImposto(veiculo);
			}
		}
		somaImposto = calcDescontoImposto(somaImposto);
		return somaImposto;		
	}
	
	@Override
	public double valorMedioImpostoFrota(String numCSProprietario) throws Exception{
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = this.veiculoDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		double mediaImposto = 0;
		int cont=0;
		while(itV.hasNext()){
			cont++;
			Veiculo veiculo =itV.next();
			if(veiculo.getClasse()!=3){
				mediaImposto += Imposto.getCalculoImposto(veiculo);
			}
		}
		mediaImposto = calcDescontoImposto(mediaImposto);
		return mediaImposto/cont;		
	}
		
		
	public Double calcDescontoImposto(Double imposto){	
		Double desconto = imposto*0.05;
		return imposto-desconto;
	}
		
	
	@Override
	public void atualizaVeiculo(TableModelos modelo) {
	
		
	}

	@Override
	public Collection<DAFIndividual> listarSumarioDAFIndividual(String numCSProprietario) throws Exception {
		
		Collection<Veiculo> listVeiculos = this.veiculoDao.findByProprietario(numCSProprietario);		
		Iterator<Veiculo> itVeiculo = listVeiculos.iterator();
		Set<DAFIndividual> sumarioIndividual = new HashSet<DAFIndividual>();
		ArrayList<String> placas = new ArrayList<String>();
		System.out.println("individual");
		while(itVeiculo.hasNext()){
				
			Veiculo veiculo = itVeiculo.next();			
			sumarioIndividual.add(new DAFIndividual(veiculo.getPlaca(), veiculo.getAnoFabricacao(), FuncoesData.getDate(), 
										this.veiculoDao.getClasseVeiculo(veiculo), veiculo.getBaseCalculo(), veiculo.getAliquota(), 
										valorTotalImpostoFrota(numCSProprietario) ));
		}
		
		return sumarioIndividual;
	}

	@Override
	public String listarSumarioDAFFrota(String numCSProprietario) throws Exception {
			
		Collection<Veiculo> listVeiculos = this.veiculoDao.findByProprietario(numCSProprietario);		
		Iterator<Veiculo> itVeiculo = listVeiculos.iterator();
		ArrayList<String> placas = new ArrayList<String>();
		System.out.println("frota");
		while(itVeiculo.hasNext()){
			
			Veiculo veiculo = itVeiculo.next();
			placas.add(veiculo.getPlaca() + "\n");
			
		}
		
		DAFFrota dafF = new DAFFrota(numCSProprietario, placas, FuncoesData.getDate(), valorTotalImpostoFrota(numCSProprietario)); 
	
		//System.out.println(dafF.toString());
		return dafF.toString();
	}

	
	@Override	
	public boolean esProprietarioFrota(String nonme) throws SQLException {		
		return this.veiculoDao.esProprietarioFrota(nonme);
	}

}

	

