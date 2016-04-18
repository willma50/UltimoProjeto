package veicular.init;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import veicular.logica.persistencia.sql.DBDAO;


public class InitData {
	
	public void run() throws SQLException{
		DBDAO dbDAO = new DBDAO();
		Connection conn = dbDAO.getConnection();
		Statement stmt = conn.createStatement();
		conn.setAutoCommit(true);
		try{
			stmt.execute("DROP TABLE CONTA");
		}catch(java.sql.SQLSyntaxErrorException ex){
			System.out.println("Tabela CONTA inexistente...");
		}
			
		stmt.execute("CREATE TABLE CONTA( NUMERO  INTEGER, NOME VARCHAR(300), SALDO REAL, TIPO INTEGER)");
		stmt.close();
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO CONTA (NUMERO, NOME, SALDO, TIPO) VALUES (? , ?, ?, ?)");
		pStmt.setLong(1, 300); pStmt.setString(2, "Capital"); pStmt.setDouble(3, 0); pStmt.setInt(4, 2);
		pStmt.executeUpdate();
		pStmt.setLong(1, 100); pStmt.setString(2, "Caixa"); pStmt.setDouble(3, 0); pStmt.setInt(4, 0);
		pStmt.executeUpdate();
		pStmt.setLong(1, 200); pStmt.setString(2, "Bancos"); pStmt.setDouble(3, 0); pStmt.setInt(4, 1);
		pStmt.executeUpdate();
		pStmt.setLong(1, 110); pStmt.setString(2, "Imoveis"); pStmt.setDouble(3, 0); pStmt.setInt(4, 0);
		pStmt.executeUpdate();

		System.out.println("Banco Preparado...");
	}

	public static void main(String[] args) throws SQLException {
		(new InitData()).run();
	}

}
