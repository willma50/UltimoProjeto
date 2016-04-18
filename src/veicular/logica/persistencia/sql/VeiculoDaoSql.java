package veicular.logica.persistencia.sql;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import veicular.logica.dominio.Aeronave;
import veicular.logica.dominio.Embarcacoes;
import veicular.logica.dominio.Proprietario;
import veicular.logica.dominio.Terrestres;
import veicular.logica.dominio.Veiculo;


public class VeiculoDaoSql extends DBDAO implements VeiculoDaoIF{
	
	private static final int TIPO_AERONAVE = 0;
	private static final int TIPO_EMBARCACOES = 1;	
	private static final int TIPO_TERRESTRE = 2;
	private static final int TIPO_OUTRO = 3;
	private static final int FROTA_N = 0;
	private static final int FROTA_S = 1;
	
	private static final String INSERT_VEICULO = "INSERT INTO VEICULO (PLACA, ANOFABRICACAO, CLASSE, valorCompra, proprietario, frota) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_VEICULO = "UPDATE veiculo SET ANOFABRICACAO = ?, frota = ?, valorcompra = ?  where PLACA = ?";
	private static final String FINDBYNOME = "select CLASSE, PLACA, ANOFABRICACAO, valorCompra, proprietario from veiculo where PLACA = ?";
	private static final String FINDBYPROPRIETARIO = "select CLASSE, PLACA, ANOFABRICACAO, valorCompra, proprietario from veiculo where proprietario = ?";
	private static final String FINDALL = "select CLASSE, PLACA, ANOFABRICACAO, valorCompra, proprietario, frota from veiculo";
	private static final String FINDALL_PARTES = "select CLASSE, PLACA, ANOFABRICACAO, valorCompra, proprietario from veiculo where placa like % ? % ";
	private static final String DELETE = "delete from veiculo where placa = ?";
	private static final String QTD_VEICULOS = "SELECT count(*) as qtd FROM veiculo inner join proprietario on veiculo.proprietario = proprietario.nome"+ 
								" where proprietario.ncs = ? group by proprietario.ncs";
	private static final String FINDALL_NCS = "SELECT proprietario, classe, anofabricacao, placa, valorcompra, proprietario.ncs  FROM veiculo inner join " +
								" proprietario on veiculo.proprietario = proprietario.nome where proprietario.ncs = ?;";
	private ProprietarioDaoSql proprietarioDao;
	
	@Override
	public void salvar(Veiculo veiculo) throws Exception {
		Veiculo v = this.findByPlaca(veiculo.getPlaca());
		
		if(v == null)
			this.create(veiculo);
		else
			this.update(veiculo);
		
	}

	public void update(Veiculo veiculo) throws SQLException {
		
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(UPDATE_VEICULO);
		pstam.setInt(1, veiculo.getAnoFabricacao());
		pstam.setInt(2, veiculo.getFrota());
		pstam.setDouble(3, veiculo.getValorCompra());
		pstam.setString(4, veiculo.getPlaca());
		pstam.executeUpdate();
		pstam.close();
		conn.close();
	}
	

	private void create(Veiculo veiculo) throws SQLException {
		
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(INSERT_VEICULO);
		pstam.setString(1, veiculo.getPlaca());
		pstam.setInt(2, veiculo.getAnoFabricacao());

		if(veiculo.getClass().equals(Aeronave.class))
			pstam.setInt(3, VeiculoDaoSql.TIPO_AERONAVE);
		else if(veiculo.getClass().equals(Embarcacoes.class))
			pstam.setInt(3, VeiculoDaoSql.TIPO_EMBARCACOES);
		else if (veiculo.getClass().equals(Terrestres.class))
			pstam.setInt(3, VeiculoDaoSql.TIPO_TERRESTRE);
		else
			pstam.setInt(3, VeiculoDaoSql.TIPO_OUTRO);
		
		pstam.setDouble(4, veiculo.getValorCompra());
		pstam.setString(5, veiculo.getProprietario().getNome());		
		pstam.setInt(6, veiculo.getFrota());
		
		pstam.executeUpdate();
		pstam.close();
		conn.close();
	}

	
	@Override
	public Veiculo findByPlaca(String placa) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(FINDBYNOME);
		pstam.setString(1, placa);
		ResultSet rs = pstam.executeQuery();
		Veiculo veiculo = null;
		
		if(rs.next()){			
			proprietarioDao = new ProprietarioDaoSql();
			if(rs.getInt("CLASSE") == 0)			
				veiculo = new Aeronave(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));			
			else if(rs.getInt("CLASSE") == 1	)		
				veiculo = new Embarcacoes(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));
			else if(rs.getInt("CLASSE") == 2	)		
				veiculo = new Terrestres(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));
			
			//else veiculo = new Terrestres(rs.getString("DESCRICAO"), rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"));
			
		}	
		rs.close();
		pstam.close();
		conn.close();
		
		return veiculo;
	}

	public ArrayList<Veiculo> findByProprietario(String proprietario) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(FINDBYPROPRIETARIO);
		pstam.setString(1, proprietario);
		ResultSet rs = pstam.executeQuery();
		ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
		
		while(rs.next()){			
			proprietarioDao = new ProprietarioDaoSql();
			if(rs.getInt("CLASSE") == 0)			
				veiculos.add(new Aeronave(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario"))));			
			else if(rs.getInt("CLASSE") == 1	)		
				veiculos.add(new Embarcacoes(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario"))));
			else if(rs.getInt("CLASSE") == 2	)		
				veiculos.add(new Terrestres(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario"))));
						
		}	
		rs.close();
		pstam.close();
		conn.close();
		
		return veiculos;
	}
	

	@Override
	public Collection<Veiculo> findAll() throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(FINDALL);
		ResultSet rs = pstam.executeQuery();
		List<Veiculo> listVeiculo = new ArrayList<>();	
		Veiculo veiculo = null;
		proprietarioDao = new ProprietarioDaoSql();
		while(rs.next()){
			
			if(rs.getInt("CLASSE") == 0)			
				veiculo = new Aeronave(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")), rs.getInt("frota"));
			
			else if(rs.getInt("CLASSE") == 1	)		
				veiculo = new Embarcacoes(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")), rs.getInt("frota"));
			
			else if(rs.getInt("CLASSE") == 2	)		
				veiculo = new Terrestres(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")), rs.getInt("frota"));
			
			listVeiculo.add(veiculo);
		}
		rs.close();
		pstam.close();
		conn.close();
		
		return listVeiculo;
	}

	@Override
	public void remove(String placa) throws Exception {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(DELETE);
		pstam.setString(1, placa);
		pstam.executeUpdate();
		pstam.close();
		conn.close();
		
	}

	@Override
	public Collection<Veiculo> buscaPorPartesVeiculo(String placa) throws Exception {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(FINDALL);
		
		//pstam.setString(1, placa);
		ResultSet rs = pstam.executeQuery();
		List<Veiculo> listVeiculo = new ArrayList<>();	
		Veiculo veiculo = null;
		proprietarioDao = new ProprietarioDaoSql();
		System.out.println(rs);
		while(rs.next()){
			
			if(rs.getInt("CLASSE") == 0)			
				veiculo = new Aeronave(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));
			
			else if(rs.getInt("CLASSE") == 1	)		
				veiculo = new Embarcacoes(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));
			
			else if(rs.getInt("CLASSE") == 2	)		
				veiculo = new Terrestres(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietarioDao.findByNome(rs.getString("proprietario")));
			
			listVeiculo.add(veiculo);
		}
		rs.close();
		pstam.close();
		conn.close();
		
		return listVeiculo;
	}

	@Override
	public int getQtdVeiculosVinculados(Proprietario proprietario) throws SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(QTD_VEICULOS);
		pstam.setString(1, proprietario.getNCS());
		ResultSet rs = pstam.executeQuery();
		int quantidade = 0;
		if(rs.next()){		
			quantidade = rs.getInt("qtd");
		}
		
		return quantidade;
	}

	@Override
	public boolean getVeiculosNCS(Proprietario proprietario) throws SQLException {
		if(this.getQtdVeiculosVinculados(proprietario) >= 4 ){
			Connection conn = this.getConnection();
			PreparedStatement pstam = conn.prepareStatement(FINDALL_NCS);
			pstam.setString(1, proprietario.getNCS());
			ResultSet rs = pstam.executeQuery();
			
			while(rs.next()){			
				if(rs.getInt("CLASSE") == 0)			
					this.update(new Aeronave(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietario, FROTA_S));
				
				else if(rs.getInt("CLASSE") == 1	)		
					this.update(new Embarcacoes(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietario, FROTA_S));
				
				else if(rs.getInt("CLASSE") == 2	)		
					this.update(new Terrestres(rs.getString("PLACA"), rs.getInt("ANOFABRICACAO"), rs.getDouble("valorCompra"), proprietario, FROTA_S));
			}
			rs.close();
			pstam.close();
			conn.close();
			return true;
		}		
		return false;
	}

	@Override
	public int getClasseVeiculo(Veiculo veiculo) {
		
		if(veiculo.getClass().equals(Aeronave.class))
			return VeiculoDaoSql.TIPO_AERONAVE;
		else if(veiculo.getClass().equals(Embarcacoes.class))
			return VeiculoDaoSql.TIPO_EMBARCACOES;
		else if (veiculo.getClass().equals(Terrestres.class))
			return VeiculoDaoSql.TIPO_TERRESTRE;
		
			
		return VeiculoDaoSql.TIPO_OUTRO;
		
		//return 0;
	}
	
}
