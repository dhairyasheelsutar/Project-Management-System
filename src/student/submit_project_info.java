package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Database;
import models.Functions;
import models.Sponsorship;

@WebServlet("/submit_project_info")
public class submit_project_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public submit_project_info() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
	
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String group_id = (String)request.getSession().getAttribute("group_id");
			String  error = "";
			if(group_id == null){
				error = "You haven't formed any group yet. Contact administrator for more information.";
			}else {
				String title = request.getParameter("title");
				String [] domains = request.getParameterValues("domains");
				String project_domain = request.getParameter("project_domain");
				String company_name = request.getParameter("company_name");
				String external_guide = request.getParameter("external_guide");
				Functions functions = new Functions();
				
				Database database = new Database();
				String sql = "";
				
				if(domains == null){
					error = functions.set_output_message("Please submit domains", false);
				}else if(domains.length <= 3){
					sql = "insert into group_domain values ";
					for(int i=0;i<domains.length;i++) {
						sql += "( "+ group_id +", "+ domains[i] +" )";
						if(i < domains.length - 1){
							sql += ", ";
						}
					}
					
					database.dml(sql);
	
					if(!company_name.equals("") && !external_guide.equals("")) {
						Sponsorship sponsor = new Sponsorship(company_name, external_guide);
						sql = "select sponsor_id from stdgroup where grpid = " + group_id;
						List<List<Object>> sponsor_id = new ArrayList<>();
						sponsor_id = Crud.find_this_query(sql);
						String sp_id = sponsor_id.get(0).get(0).toString();
						sponsor.sponsor_id = sp_id;
						if(sponsor.update()) {
							sql = "update stdgroup set project_title = '"+ title +"', project_domain = '"+ project_domain +"', sponsor_id = " + sp_id + " where grpid = " + group_id;
							int result = database.dml(sql);
							
							
							
							if(result != -1){
								error = functions.set_output_message("Project Information saved", true);
								sql = "insert into grplog (log, grpid, logdate, log_staff) values ('You submitted the project details alongwith the sponsorship details', "+ group_id +", now(), 'Group "+ group_id +" submitted the project details alongwith the sponsorship details')";
								database.dml(sql);
							}else {
								error = functions.set_output_message("Something Went Wrong", false);
							}
							
							
						}
					}else {
						sql = "update stdgroup set project_title = '"+ title +"', project_domain = '"+ project_domain +"' where grpid = " + group_id;
						int result = database.dml(sql);
						if(result != -1){
							error = functions.set_output_message("Project Information saved", true);
							sql = "insert into grplog (log, grpid, logdate, log_staff) values ('You submitted the project details', "+ group_id +", now(), 'Group "+ group_id +" submitted project details')";
							database.dml(sql);
						}else {
							error = functions.set_output_message("Something Went Wrong", false);
						}
					}
				}else {
					error = functions.set_output_message("You are allowed to submit maximum 3 domains", false);
				}
			}
			
			request.setAttribute("output", error);
			request.getRequestDispatcher("submit_domain").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
