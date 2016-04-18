package veicular.logica.app;

import java.util.Collection;

public interface ProprietarioLogicaIF {

	public Collection<String> findAll() throws Exception;
	public Collection<String> findAll_NAME(String nome) throws Exception;
	
}
