package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;

@WebServlet("/submit_guide_preference")
public class submit_guide_preference extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public submit_guide_preference() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			
			String grpid = (String)request.getSession().getAttribute("group_id");
			
			
			String pref1 = request.getParameter("pref1");
			String pref2 = request.getParameter("pref2");
			String pref3 = request.getParameter("pref3");
			
			String result = "";
			Functions functions = new Functions();
			Database database = new Database();
			
			if(grpid == null) {
				result = "You haven't formed group yet. Please contact administrator for furthor information";
			}else {
				
				if(pref1.equals("null") || pref2.equals("null") || pref3.equals("null")) {
					result = functions.set_output_message("Please select preferences", false);
				}else if(pref1.equals(pref2) || pref1.equals(pref3) || pref2.equals(pref3)) {
					result = functions.set_output_message("Please select different preferences", false);
				}else {
					String sql = "insert into guide_preferences (grpid, guideid) values ("+ grpid +", "+ pref1 +"), ("+ grpid +", "+ pref2 +"), ("+ grpid +", "+ pref3 +")";
					int result1 = database.dml(sql);
					if(result1 != -1) {
						result = functions.set_output_message("Guide preferences submitted", true);
					}
					
					sql = "insert into grplog (log, grpid, logdate, log_staff) values ('You submitted guide preferences', "+ grpid +", now(), 'Group "+ grpid +" submitted guide preferences')";
					database.dml(sql);
				}
			}
			
			request.setAttribute("result", result);
			request.getRequestDispatcher("guide_preference").forward(request, response);
			
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
