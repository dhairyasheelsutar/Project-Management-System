package models;

import java.util.HashMap;
import java.util.Map;

public class Review_4 {

	protected static String db_table = "review_4";
	protected static String[] db_fields = {"implementation","testing","report","publications","skills","qa","total"};
	protected static String[] primary_key = {"sid"};
	protected static Functions functions = new Functions();
	
	public String id;
	public String sid;
	public String implementation;
	public String testing;
	public String report;
	public String publications;
	public String skills;
	public String qa;
	public String total;
	
	public Review_4() {}
	
	
	public Review_4(String sid,String implementation,String testing,String report,String publications,String skills,String qa,String total) {
		this.sid=sid;
		this.implementation=implementation;
		this.testing=testing;
		this.report=report;
		this.publications=publications;
		this.skills=skills;
		this.qa=qa;
		this.total=total;
	}
	
	public String[] init() {
		String[] db_values = {this.implementation,this.testing,this.report,this.publications,this.skills,this.qa,this.total};
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
			key_value_pair.put("sid", this.sid);
			return key_value_pair;
		}
	
	
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
	
