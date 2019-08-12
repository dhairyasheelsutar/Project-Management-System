package models;

import java.util.HashMap;
import java.util.Map;

public class Notification_receipient{
	protected static String db_table = "msg_receipient";
	protected static String[] db_fields = {"receipient_id", "is_read", "msgid", "is_declined"};
	protected static String[] primary_key = {"msgrecid"};
	
	public int msgrecid;
	public String receipient_id;
	public String is_read;
	public String msgid;
	public String is_declined;

	public Notification_receipient(int msgrecid) {
		this.msgrecid = msgrecid;
	}
	
	public Notification_receipient(String receipient_id, String is_read, String msgid, String is_declined) {
		this.receipient_id = receipient_id;
		this.is_read = is_read;
		this.msgid = msgid;
		this.is_declined = is_declined;
	}
	
	public String[] init() {
		String[] db_values = {'"'+this.receipient_id+'"', this.is_read, this.msgid, this.is_declined};
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
		key_value_pair.put("msgrecid", this.msgrecid);
		return key_value_pair;
	}

}