package veicular.logica.persistencia.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import veicular.logica.dominio.Proprietario;
import veicular.logica.dominio.Veiculo;


public class ProprietarioDaoSql extends DBDAO implements ProprietarioDaoIF{

	private static final String FINDBYNOME = "select nome, ncs from proprietario where nome = ?";
	private static final String FINDALL = "select nome, ncs from proprietario";
	private static final String FINDBYNCS = "select nome, ncs from proprietario where ncs = ?";

	@Override
	public Collection<Proprietario> findAll() throws SQLException {
		Set<Proprietario> proprietarios = new HashSet<Proprietario>();
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(ProprietarioDaoSql.FINDALL);
		ResultSet rs = pstam.executeQuery();
		
		while(rs.next()){
			proprietarios.add(new Proprietario(rs.getString("nome"), rs.getString("ncs")));
		}
		rs.close();
		pstam.close();
		conn.close();
		return proprietarios;
	}

	@Override
	public Proprietario findByNome(String nome) throws SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(ProprietarioDaoSql.FINDBYNOME);
		pstam.setString(1, nome);
		ResultSet rs = pstam.executeQuery();
		Proprietario proprietario = null;
		if(rs.next())
			proprietario = new Proprietario(rs.getString("nome"), rs.getString("ncs"));
		
		rs.close();
		pstam.close();
		conn.close();
		return proprietario;
	}

	@Override
	public Proprietario findByNCS(String nome) throws SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstam = conn.prepareStatement(ProprietarioDaoSql.FINDBYNCS);
		pstam.setString(1, nome);
		ResultSet rs = pstam.executeQuery();
		Proprietario proprietario = null;
		if(rs.next())
			proprietario = new Proprietario(rs.getString("nome"), rs.getString("ncs"));
		
		rs.close();
		pstam.close();
		conn.close();
		return proprietario;
	}

	
	

}
	