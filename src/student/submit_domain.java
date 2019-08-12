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
import models.Domain;
import models.Functions;


@WebServlet("/submit_domain")
public class submit_domain extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public submit_domain() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String group_id = (String)request.getSession().getAttribute("group_id");
			Functions functions = new Functions();
			String domain_list = "";
			String project_domain = "";
			String error = "";
			if(group_id.equals("-1")){
				error = "You haven't formed any group yet. Contact administrator for more information.";
				error = functions.set_output_message(error, false);
				request.setAttribute("output", error);
				request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
			}else {
				
				String query = "select * from group_domain where grpid = "+ group_id +"";
				List<List<Object>>check = Crud.find_this_query(query);
				
				if(check.isEmpty()) {
					List<List<Object>> domains = new ArrayList<>();
					Domain dom = new Domain();
					domains = dom.read();
			
					if(domains.isEmpty()){
						domain_list = "No data available currently";
						project_domain = "No data available currently";
					}else {
						
						project_domain = "<select class='form-control' name='project_domain'>";
						for(int i=0;i<domains.size();i++) {
							domain_list += "<div class='mx-1'>";
							domain_list += "<input type='checkbox' class='mx-1' name='domains' value='"+ domains.get(i).get(0) +"' />";
							domain_list += "<label>"+ domains.get(i).get(1) +"</label>";
							domain_list += "</div>";
					
							project_domain += "<option value='"+ domains.get(i).get(1) +"'>"+ domains.get(i).get(1) +"</option>";
						}
						project_domain += "</select>";
					}
					
					request.setAttribute("error", error);
					request.setAttribute("domain_list", domain_list);
					request.setAttribute("project_domain", project_domain);
					request.getRequestDispatcher("admin/student/templates/submit_domain.jsp").forward(request, response);
					
				}else {
					error = "You already submitted the project information";
					error = functions.set_output_message(error, true);
					request.setAttribute("output", error);
					request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
					
				}
				
				
				
			}
			
				
			
		}
		
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
