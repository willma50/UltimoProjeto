package veicular.logica.app;


import java.sql.SQLException;
import java.util.Collection;

import veicular.logica.dominio.DAFFrota;
import veicular.logica.dominio.DAFIndividual;
import veicular.logica.dominio.Veiculo;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.ui.TableModelos;

public interface VeiculoLogicaIF {

	public void addVeiculo(String placa, int anoFabricacao, int classe, double valorCompra, String nomeProprietario) throws Exception;
	public Collection<String> findAll() throws Exception;
	public void setPersistencia(VeiculoDaoIF veiculoDAO);
	public void removerVeiculo(String placa) throws Exception;
	public Veiculo buscaPorPlaca(String placa) throws Exception;
	public Collection<Veiculo> listarVeiculo()throws Exception;
	public Collection<Veiculo> buscaPorPartesVeiculo(String placa)throws Exception;
	public int buscaAno();
	public void atualizaVeiculo(TableModelos modelo);
	public double valorTotalImpostoFrota(String numCSProprietario) throws Exception;
	public double valorMedioImpostoFrota(String numCSProprietario) throws Exception;
	public Collection<DAFIndividual> listarSumarioDAFIndividual(String numCSProprietario) throws Exception;
	public String listarSumarioDAFFrota(String numCSProprietario) throws Exception;
	public boolean esProprietarioFrota(String selectedItem) throws SQLException;

}
