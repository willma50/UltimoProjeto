package veicular.logica.app;


import java.util.Collection;

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
}
