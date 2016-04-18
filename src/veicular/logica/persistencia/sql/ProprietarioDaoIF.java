package veicular.logica.persistencia.sql;

import java.sql.SQLException;
import java.util.Collection;

import veicular.logica.dominio.Proprietario;

public interface ProprietarioDaoIF {

	public Collection<Proprietario> findAll() throws SQLException;
	public Proprietario findByNome(String nome) throws SQLException;
	public Proprietario findByNCS(String nome) throws SQLException;
}
