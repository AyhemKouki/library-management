package library_management;

import java.sql.*;

public class Database_connection {
	//url 
	private static final String url = "jdbc:mysql://localhost:3306/biblio";
	private static final String user = "root";
	private static final String password ="AyhemKouki2486";
	
	//open connection
	public static Connection OpenConnection() {
		Connection connect = null ;
		try {
			connect = DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	//close connection
	public static void CloseConnection(Connection connect) {
		try {
			if(connect!=null && !connect.isClosed()) {
				connect.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ClosePreparedStatement(PreparedStatement statement)
	{
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.getMessage();
	            }
	        }
	}
	
	public static void CloseResult(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			}catch(SQLException e) {
				e.getMessage();
			}
		}
	}
}
