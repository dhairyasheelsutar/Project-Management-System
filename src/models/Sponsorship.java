package models;

import java.util.HashMap;
import java.util.Map;

public class Sponsorship extends Crud {

	protected static String db_table = "sponsorship";
	protected static String[] db_fields = {"company_name", "external_guide"};
	protected static String[] primary_key = {"sponsor_id"};
	protected static Functions functions = new Functions();
	
	public String sponsor_id;
	public String company_name;
	public String external_guide;
	
	public Sponsorship() {
		
	}
	
	public Sponsorship(String sponsor_id) {
		this.sponsor_id = sponsor_id;
	}
	
	public Sponsorship(String company_name, String external_guide) {
		this.company_name = company_name;
		this.external_guide = external_guide;
	}
	
	public String[] init() {
		String[] db_values = {'"'+ this.company_name +'"', '"'+ this.external_guide +'"'};
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
		key_value_pair.put("sponsor_id", this.sponsor_id);
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
	

}
