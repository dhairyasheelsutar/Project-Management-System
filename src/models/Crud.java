package models;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//CRUD class : Performs basic operations on functions

public class Crud {
	private static Database database = new Database();
	
	//Insert query
	
	public static boolean insert_data(String table_name, String[] table_attr, String[] table_values){
		try {
			String sql = "insert into "+ table_name +" ("+ String.join(", ", table_attr) +") values ("+ String.join(", ", table_values) +") ";
			int result = database.dml(sql);
			if(result != -1) {
				return true;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
	//Read query
	
	public static <E> List<List<Object>> find_this_query(String sql) {
		try {
			ResultSet result = database.query(sql);
			ResultSetMetaData rsmd = result.getMetaData();
			List<List<Object>> rowData = new ArrayList<>();
			if(result != null) {
				while(result.next()) {
					List<Object> Columns = new ArrayList<>();
				    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				        Columns.add(result.getObject(i));
				    }
				    rowData.add(Columns);
				}
				
				return rowData;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
	
	public static <E> List<List<Object>> execute_procedures(String sql){
		try {
			ResultSet result = database.stored_procedure(sql);
			ResultSetMetaData rsmd = result.getMetaData();
			List<List<Object>> rowData = new ArrayList<>();
			if(result != null) {
				while(result.next()) {
					List<Object> Columns = new ArrayList<>();
				    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				        Columns.add(result.getObject(i));
				    }
				    rowData.add(Columns);
				}
				return rowData;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static <E> List<List<Object>> read_data(String table_name) {
		try {
			String sql = "select * from "+ table_name +"";
			List<List<Object>> rowData = new ArrayList<>();
			rowData = find_this_query(sql);
			return rowData;
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	//Update query
	
	public static boolean update_data(String table_name, String table_update_values , String primary_keys) {
		try {
			String sql = "update "+ table_name +" set  "+ table_update_values +" where "+ primary_keys +"";
			int result = database.dml(sql);
			if(result != -1) {
				return true;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
	//Delete query
	
	public static boolean delete_data(String table_name, String primary_keys) {
		try {
			String sql = "delete from "+ table_name +" where "+ primary_keys +"";
			int result = database.dml(sql);
			if(result != -1) {
				return true;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return false;
	}
	
	//Search query : Search data by primary key
	
	public static <E> List<List<Object>> search_data(String table_name, String primary_keys) {
		try {
			String sql = "select * from "+ table_name +" where "+ primary_keys +"";
			List<List<Object>> rowData = new ArrayList<>();
			rowData = find_this_query(sql);
			return rowData;
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

}
