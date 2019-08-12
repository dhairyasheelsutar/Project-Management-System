package models;

import java.util.HashMap;
import java.util.Map;

public class Comment {
	protected static String db_table = "grp_reviewer";
	protected static String[] db_fields = {"tid", "grpid", "review1", "review2", "review3", "review4"};
	protected static String[] primary_key = {"tid", "grpid"};
	protected static Functions functions = new Functions(); 
	
	public String tid;
	public String grpid;
	public String review1;
	public String review2;
	public String review3;
	public String review4;
	
	public Comment() {}
	
	
	public Comment(String grpid,String tid, String review1, String review2, String review3, String review4) {
		this.grpid=grpid;
		this.tid = tid;
		this.review1=review1;
		this.review2=review2;
		this.review3=review3;
		this.review4=review4;
}

	public String[] init() {
		String[] db_values = {this.tid,this.grpid,'"' + this.review1 +'"', '"' + this.review2 +'"', '"' + this.review3 +'"', '"' + this.review4 +'"'};
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
			key_value_pair.put("tid", this.tid);
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
	
