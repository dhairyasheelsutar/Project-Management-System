package models;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//User table in database

public class User extends Crud {
	
	//Static members stores table_name, table_attributes, primary_key in db
	
	protected static String db_table = "user";
	protected static String[] db_fields = {"userid", "username", "password", "name", "email", "mobileno"};
	protected static String[] primary_key = {"userid"};
	protected static Functions functions = new Functions();
	
	//Database table attributes
	
	public String uid;
	public String username;
	public String password;
	public String name;
	public String email;
	public String mobileno;

	public User() {
		
	}
	
	public User(String id) {
		this.uid = id;
	}
	
	public User(String id, String username, String password, String name, String email, String mobileno) {
		this.uid = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobileno = mobileno;
	}
	
	public String[] init() {
		String[] db_values = {'"' + this.uid + '"', '"' + this.username + '"', '"' + this.password + '"', '"'+ this.name +'"', '"'+ this.email +'"', this.mobileno};
		return db_values;
	}
	
	//Create key => value map of tuple
	
	public Map<String, String> create_hash_map() {
		String[] db_values = this.init();
		Map<String, String> key_value_pair = new HashMap<String, String>();
		for(int i=0;i<db_fields.length;i++) {
			key_value_pair.put(db_fields[i], db_values[i]);
		}
		return key_value_pair;
	}
	
	//Mapping primary keys
	
	public Map<String, String> map_primary_keys() {
		Map<String, String> key_value_pair = new HashMap<String, String>();
		key_value_pair.put("userid", '"'+this.uid+'"');
		return key_value_pair;
	}

	//Insert query of user
	
	public boolean create(){
		try {
			String[] db_values = this.init();
			if(Crud.insert_data(db_table, db_fields, db_values)) {
				return true;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}
	
	//ArrayList of users records
	
	public List<List<Object>> read() {
		try {
			List<List<Object>> users = (List<List<Object>>) Crud.read_data(db_table);
			return users;
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	//Update query
	
	public boolean update() {
		Map<String, String> key_value_pair = this.create_hash_map();
		Map<String, String> primary_key_pairs = this.map_primary_keys();
		String db_update_values = functions.trim_first_last(key_value_pair);
		String db_primary_key_values = functions.primary_key_array(primary_key_pairs);
		boolean result = Crud.update_data(db_table, db_update_values, db_primary_key_values);
		if(result) {
			return true;
		}
		
		return false;
	}
	
	//Delete query

	public boolean delete() {
		Map<String, String> primary_key_pairs = this.map_primary_keys();
		String db_primary_key_values = functions.primary_key_array(primary_key_pairs);
		boolean result = Crud.delete_data(db_table, db_primary_key_values);
		if(result) {
			return true;
		}
		return false;
	}
	
	//Search by primary keys
	
	public List<Object> search() {
		try {
			Map<String, String> primary_key_pairs = this.map_primary_keys();
			String db_primary_key_values = functions.primary_key_array(primary_key_pairs);
			List<List<Object>> users = (List<List<Object>>) Crud.search_data(db_table, db_primary_key_values);
			List<Object> single = users.get(0);
			return single;
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}

}
