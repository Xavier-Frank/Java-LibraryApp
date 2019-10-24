package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String URL="jdbc:mysql://localhost/Library?useSSL=false";
	private static final String USERNAME="XAVI";
	private static final String PASSWORD="@LMNTrix24";
	
	public static Connection  getConnection() throws SQLException {
		
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	}
	//Testing the Database Connection
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		
		try {
			connection=DataBaseConnection.getConnection();
			System.out.println("Hurray"); //Print hurray if the connection is successful
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		finally {
			if (connection !=null) {
				connection.close();
				
			}
		}
		
	}

}
