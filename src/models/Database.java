
//Database Class : Returns database connection object

package models;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	private Connection connection;
	
	public Database() {
		this.set_connection();
	}
	
	//Set connection on object creation
	
	public Statement set_connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_management_system_final?useSSL=false","root","");
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	//Dml (insert, delete, update) query functions
	
	public int dml(String sql){
		try {
			Statement stmt = this.connection.createStatement();
			System.out.println(sql);
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				return result;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet stored_procedure(String sql) {
		try {
			
			CallableStatement stmt = this.connection.prepareCall(sql);
			boolean hadresult = stmt.execute();
			if(hadresult) {
				ResultSet result = stmt.getResultSet();
				if(result != null) {
					return result;
				}
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
	
	//Select statement query functions
	
	public ResultSet query(String sql){
		try {
			Statement stmt = this.connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if(result != null) {
				return result;
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return null;
	}
	
}

