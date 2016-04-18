package veicular.logica.persistencia.sql;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDAO {
	private static final String URI_DB =  "jdbc:postgresql://10.130.163.151/projetopoo"; //"jdbc:hsqldb:file:Veiculo_BD";;//"jdbc:hsqldb:hsql://localhost/";
	private static final String USER_DB = "postgres";//"SA";
	private static final String PWD_DB = "postgres";//"";

	public DBDAO(){
		try {
		                      
			DriverManager.registerDriver(new org.postgresql.Driver());
			System.out.println("teste sim");
		} catch (SQLException e) {
			System.out.println("teste não");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DBDAO.URI_DB, DBDAO.USER_DB, DBDAO.PWD_DB);
	}
	
}
