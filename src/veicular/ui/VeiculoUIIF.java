package veicular.ui;

import veicular.logica.app.VeiculoLogicaIF;


public interface VeiculoUIIF {
	
	public void setLogic(VeiculoLogicaIF app);
	public void setDisplay() throws Exception;

}
