package student;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/guide_preference")
public class guide_preference extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public guide_preference() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String grpid = (String)request.getSession().getAttribute("group_id");
			System.out.println(grpid);
			Functions functions = new Functions();
			String error = "";
			String guidelist = "";
			if(grpid.equals("-1")) {
				error = "You haven't formed group yet. Please contact administrator for furthor information";
				error = functions.set_output_message(error, false);
				request.setAttribute("output", error);
				request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
			}else {
				
				String query = "select grpid from guide_preferences where grpid = "+ grpid +"";
				List<List<Object>>check = Crud.find_this_query(query);
				
				if(check.isEmpty()) {
					String sql = "select name, teacher_capability.tid from teacher_capability, user, teacher where user.userid = teacher.userid and teacher.tid = teacher_capability.tid and  teacher_capability.cid = 1003";
					List<List<Object>> guides = new ArrayList<>();
					guides = Crud.find_this_query(sql);
					
					if(guides.isEmpty() == false) {
						
						guidelist = "<option value='null'>Select Guide</option>";
						
						for(int i=0;i<guides.size();i++) {
							guidelist += "<option value='"+ ((List<Object>) guides.get(i)).get(1) +"'>"+ ((List<Object>) guides.get(i)).get(0) +"</option>";
						}
					}else {
						error = "Currently no data available";
					}
					
					request.setAttribute("error", error);
					request.setAttribute("guidelist", guidelist);
					request.getRequestDispatcher("admin/student/templates/guide_preference.jsp").forward(request, response);
					
				}else {
					
					String output = "";
					output = functions.set_output_message("You have already submitted guide preferences", true);
					request.setAttribute("output", output);
					request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
					
				}
				
			}
			
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
