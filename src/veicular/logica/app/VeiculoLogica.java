package veicular.logica.app;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import veicular.funcoes.FuncoesData;
import veicular.logica.dominio.Aeronave;
import veicular.logica.dominio.Embarcacoes;
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
	
	
	public Double valorTotalImpostoFrota(String numCSProprietario) throws Exception{
		VeiculoDaoIF vDao = new VeiculoDaoSql();
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = vDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		Double somaImposto = (double) 0;
		while(itV.hasNext()){
			Veiculo v =itV.next();
			if(v.getClasse()!=3){
				somaImposto+=v.getImpostoDevido();
			}
		}
		somaImposto = calcDescontoImposto(somaImposto);
		return somaImposto;		
	}
	public Double valorMedioImpostoFrota(String numCSProprietario) throws Exception{
		VeiculoDaoIF vDao = null;
		ArrayList<Veiculo> listVeiculo = new ArrayList<Veiculo>();		
		listVeiculo = vDao.findByProprietario(numCSProprietario);
		Iterator<Veiculo> itV = listVeiculo.iterator();
		Double mediaImposto = (double) 0;
		int cont=0;
		while(itV.hasNext()){
			cont++;
			Veiculo v =itV.next();
			if(v.getClasse()!=3){
				mediaImposto+=v.getImpostoDevido();
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
}

	

