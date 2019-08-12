package models;

import java.util.HashMap;
import java.util.Map;

public class Review_1 {

	protected static String db_table = "review_1";
	protected static String[] db_fields = {"topic", "scope","survey","planning","skills","qa","total"};
	protected static String[] primary_key = {"sid"};
	protected static Functions functions = new Functions();
	
	public String id;
	public String sid;
	public String topic;
	public String scope;
	public String survey;
	public String planning;
	public String skills;
	public String qa;
	public String total;
	
	public Review_1() {}
	
	
	public Review_1(String sid,String topic,String scope,String survey,String planning,String skills,String qa,String total) {
		this.sid=sid;
		this.topic=topic;
		this.scope=scope;
		this.survey=survey;
		this.planning=planning;
		this.skills=skills;
		this.qa=qa;
		this.total=total;
	}
	
	public String[] init() {
		String[] db_values = {this.topic,this.scope, this.survey,this.planning,this.skills,this.qa,this.total};
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
	
