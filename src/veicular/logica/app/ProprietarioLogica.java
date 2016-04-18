package veicular.logica.app;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import veicular.logica.dominio.Proprietario;
import veicular.logica.persistencia.sql.ProprietarioDaoIF;
import veicular.logica.persistencia.sql.ProprietarioDaoSql;

public class ProprietarioLogica implements ProprietarioLogicaIF{

	private ProprietarioDaoIF proprietarioDao;
	
	@Override
	public Collection<String> findAll() throws Exception {
		this.proprietarioDao = new ProprietarioDaoSql();
		Collection<Proprietario> proprietarios = proprietarioDao.findAll();
		Set<String> nomeProprietario = new HashSet<String>();
		Iterator<Proprietario> itProprietario = proprietarios.iterator();
		while(itProprietario.hasNext())
			nomeProprietario.add(itProprietario.next().getNome());
		
		return nomeProprietario;
	}

	@Override
	public Collection<String> findAll_NAME(String nome) throws Exception {
		this.proprietarioDao = new ProprietarioDaoSql();
		Proprietario proprietario = proprietarioDao.findByNome(nome);
		Collection<Proprietario> nomeProprietario = new HashSet<Proprietario>();
		//proprietarios.add(proprietario);
		//Iterator<Proprietario> itProprietario = proprietario.iterator();
		//while(itProprietario.hasNext())
			nomeProprietario.addAll(nomeProprietario);
		
		return null;
	}

}
