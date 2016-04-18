package veicular.logica.app;

import java.sql.SQLException;
import java.text.ParseException;

import veicular.logica.dominio.DAFFrota;

public interface DAFFrotaLogicaIF {
	public void imprimir(String numCSProprietario) throws ParseException, ClassNotFoundException, SQLException, Exception;

}
