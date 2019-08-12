package models;

import java.util.HashMap;
import java.util.Map;

public class Notification_sender {

	protected static String db_table = "message";
	protected static String[] db_fields = {"msg_body", "userid", "start_date", "count_receipients"};
	protected static String[] primary_key = {"msgid"};
	
	public int msgid;
	public String msg_body;
	public String userid;
	public String start_date;
	public String count_receipients;
	
	public Notification_sender() {}
	
	public Notification_sender(int msgid) {
		this.msgid = msgid;
	}
	
	public Notification_sender(String msg_body, String userid, String start_date, String count_receipients) {
		this.msg_body = msg_body;
		this.userid = userid;
		this.start_date = start_date;
		this.count_receipients = count_receipients;
	}
	
	public String[] init() {
		String[] db_values = {'"' + this.msg_body + '"', '"' + this.userid + '"',  this.start_date, this.count_receipients};
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
	
	public Map<String, Integer> map_primary_keys() {
		Map<String, Integer> key_value_pair = new HashMap<String, Integer>();
		key_value_pair.put("msgid", this.msgid);
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
	
}