package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/abstract_project")
public class abstract_project extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public abstract_project() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			
			String grpid = (String)request.getSession().getAttribute("group_id");
			Functions functions = new Functions();
			String output = "";
			if(grpid.equals("-1")) {
				output = "You haven't formed group yet. Please contact administrator for furthor information";
				output = functions.set_output_message(output, false);
				request.setAttribute("output", output);
				request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
			}else{
				String sql = "select grpid from abstract where grpid = "+ grpid +"";
				List<List<Object>>result = Crud.find_this_query(sql);
				
				if(result.isEmpty()) {
					request.getRequestDispatcher("admin/student/templates/abstract_project.jsp").forward(request, response);					
				}else {
					output = "You have already submitted the project synopsis";
					output = functions.set_output_message(output, true);
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
