package models;


public class Abstract {

	protected static String db_table = "abstract";
	protected static String[] db_fields = {"grpid", "background", "purpose","method","results","conclusion"};
	protected static String[] primary_key = {"id"};
	
	public String id;
	public String grpid;
	public String background;
	public String purpose;
	public String method;
	public String results;
	public String conclusion;
	
	public Abstract() {}
	
	public Abstract(String id) {
		this.id=id;
	}
	public Abstract(String grpid,String background,String purpose,String method,String results,String conclusion) {
		this.grpid=grpid;
		this.background=background;
		this.purpose=purpose;
		this.method=method;
		this.results=results;
		this.conclusion=conclusion;
	}
	
	public String[] init() {
		String[] db_values = {this.grpid,'"'+this.background+'"','"'+this.purpose+'"', '"'+this.method+'"','"'+this.results+'"','"'+this.conclusion+'"'};
		return db_values;
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
	
