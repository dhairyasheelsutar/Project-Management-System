package models;

import java.util.HashMap;
import java.util.Map;

public class Group extends Crud {

	protected static String db_table = "stdgroup";
	protected static String[] db_fields = {"project_title", "project_domain", "sponsor_id", "guidename", "guideid"};
	protected static String[] primary_key = {"grpid"};
	protected static Functions functions = new Functions();
	
	public String grpid;
	public String project_title;
	public String project_domain;
	public String sponsor_id;
	public String guidename;
	public String guideid;
	public String pbs_id;
	
	public Group() {
		
	}
	
	public Group(String grpid) {
		this.grpid = grpid;
	}
	
	public Group(String project_title, String project_domain, String sponsor_id, String guidename, String guideid, String pbs_id) {
		this.project_title = project_title;
		this.project_domain = project_domain; 
		this.sponsor_id = sponsor_id;
		this.guidename = guidename;
		this.guideid = guideid;
		this.pbs_id = pbs_id;
	}
	
	public String[] init() {
		String[] db_values = {'"'+ this.project_title +'"', '"'+ this.project_domain +'"', this.sponsor_id, '"'+ this.guidename +'"', this.guideid};
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
		key_value_pair.put("grpid", this.grpid);
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
