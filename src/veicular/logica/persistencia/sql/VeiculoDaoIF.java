package veicular.logica.persistencia.sql;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import veicular.logica.dominio.Proprietario;
import veicular.logica.dominio.Veiculo;



public interface VeiculoDaoIF {

	public void salvar(Veiculo veiculo) throws Exception; //criação 
	public Veiculo findByPlaca(String placa) throws Exception; // pesquisa pela descrição
	public ArrayList<Veiculo> findByProprietario(String proprietario) throws Exception; // pesquisa pela descrição
	public Collection<Veiculo> findAll() throws Exception; // busca todos dos registros
	public void remove(String descricao) throws Exception;
	public Collection<Veiculo> buscaPorPartesVeiculo(String placa) throws Exception;
	public int getQtdVeiculosVinculados(Proprietario proprietario) throws SQLException;
	public boolean getVeiculosNCS(Proprietario proprietario) throws SQLException;
}