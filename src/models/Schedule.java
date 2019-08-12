package models;

import java.util.HashMap;
import java.util.Map;

public class Schedule extends Crud {

	protected static String db_table = "schedule";
	protected static String[] db_fields = {"start_date", "expiry_date", "notification", "template", "is_active"};
	protected static String[] primary_key = {"sch_id"};
	protected static Functions functions = new Functions();
	
	public String sch_id;
	public String start_date;
	public String expiry_date;
	public String notification;
	public String template;
	public String is_active;
	
	public Schedule() {
		
	}

	public Schedule(String id) {
		this.sch_id = id;
	}
	
	public Schedule(String start_date,String expiry_date,String notification,String template, String is_active) {
		this.start_date = start_date;
		this.expiry_date = expiry_date;
		this.notification = notification;
		this.template = template;
		this.is_active = is_active;
	}
	
	public String[] init() {
		String[] db_values = {'"'+this.start_date+'"', '"'+this.expiry_date+'"', '"'+ this.notification +'"', '"'+ this.template +'"', this.is_active};
		return db_values;
	}
	
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
		key_value_pair.put("sch_id", this.sch_id);
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
}


