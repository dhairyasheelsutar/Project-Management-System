package models;

import java.util.HashMap;
import java.util.Map;

public class Review_3 {

	protected static String db_table = "review_3";
	protected static String[] db_fields = { "design","implementation","results","skills","qa","summarize","total"};
	protected static String[] primary_key = {"sid"};
	protected static Functions functions = new Functions();
	
	public String id;
	public String sid;
	public String design;
	public String implementation;
	public String results;
	public String skills;
	public String qa;
	public String summarize;
	public String total;
	
	public Review_3() {}
	
	
	public Review_3(String sid,String design,String implementation,String results,String skills,String qa,String summarize,String total) {
		this.sid=sid;
		this.design=design;
		this.implementation=implementation;
		this.results=results;
		this.skills=skills;
		this.qa=qa;
		this.summarize=summarize;
		this.total=total;
	}
	
	public String[] init() {
		String[] db_values = {'"'+this.design+'"',this.implementation,this.results,this.skills,this.qa,'"'+this.summarize+'"',this.total};
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
	
