package models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Notifications extends Crud {
	private Database database = new Database();
	private static Notification_sender sender;
	private static Notification_receipient[] receipient;
	
	public Notifications(Notification_sender actor, Notification_receipient[] receiver) {
		sender = actor;
		receipient = receiver;
	}
	
	public boolean create() {
		try {
			boolean insert_message = Crud.insert_data(Notification_sender.db_table, Notification_sender.db_fields, sender.init());
			List<List<Object>> notification_sender = new ArrayList<>();
			notification_sender = Crud.find_this_query("select last_insert_id() from message limit 1");
			BigInteger id = (BigInteger) notification_sender.get(0).get(0);
			String sql = "insert into msg_receipient ("+ String.join(", ", Notification_receipient.db_fields) +") values ";
			for(int i=0;i<receipient.length;i++) {
				sql += "('"+ receipient[i].receipient_id +"', "+ receipient[i].is_read +", "+ id +", "+ receipient[i].is_declined +")";
				if(i < receipient.length - 1) {
					sql += ", ";
				}
			}
			int insert_msg_receipient = database.dml(sql);
			if(insert_message == true && insert_msg_receipient != -1) {
				return true;
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return false;
	}
}
