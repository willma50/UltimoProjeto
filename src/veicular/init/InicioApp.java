package veicular.init;


import java.io.FileReader;
import java.util.Properties;

import veicular.logica.app.VeiculoLogicaIF;
import veicular.logica.persistencia.sql.VeiculoDaoIF;
import veicular.ui.VeiculoUIIF;


public class InicioApp {

	   public void run(String fileName) throws Exception{
	    	Properties prop = new Properties();
	    	prop.load(new FileReader(fileName));
	    	VeiculoDaoIF veiculoDAO = (VeiculoDaoIF)(Class.forName(prop.getProperty("PERSISTENCIA")).newInstance());
	    	VeiculoLogicaIF app = (VeiculoLogicaIF)(Class.forName(prop.getProperty("LOGICA")).newInstance());
	   		app.setPersistencia(veiculoDAO);
	    	VeiculoUIIF ui = (VeiculoUIIF)(Class.forName(prop.getProperty("UI")).newInstance());
	    	ui.setLogic(app);	    	
	   		ui.setDisplay(); 
	    }

	public static void main(String[] args) throws Exception{
		
		(new InicioApp()).run("config.app");
		InicioApp app = new InicioApp();
	}
}
