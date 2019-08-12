package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Domain extends Crud {

	protected static String db_table = "domain";
	protected static String[] db_fields = {"dname"};
	protected static Functions functions = new Functions();
	
	public String did;
	public String dname;
	
	public Domain(String dname) {
		this.dname = dname;
	}
	
	public Domain(){
		
	}
	
	public String[] init() {
		String[] db_values = {'"'+this.dname+'"'};
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
			key_value_pair.put("did", this.did);
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
		
		public List<List<Object>> read() {
			try {
				List<List<Object>> domains = (List<List<Object>>) Crud.read_data(db_table);
				return domains;
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
		
		
}
