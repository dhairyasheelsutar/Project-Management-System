package guide;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Database;
import models.Functions;

@WebServlet("/aproove_abstract")
public class aproove_abstract extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public aproove_abstract() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String) request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String grpid = request.getParameter("grpid");
			String sql = "delete from abstract where grpid = "+ grpid +"";
			Database database = new Database();
			Functions functions = new Functions();
			String output = "";
			if(database.dml(sql) != -1) {
				output = functions.set_output_message("Group " + grpid + " project abstract is rejected", true);
				sql = "insert into grplog (log, grpid, logdate, log_staff) values ('Your project synopsis is rejected. You are requested to submit it again', "+ grpid +", now(), 'You rejected Group ID "+ grpid +" project synopsis')";
				database.dml(sql);
				
			}else {
				output = functions.set_output_message("Something went wrong", false);
			}
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("student_group_single_guide").forward(request, response);
		}
		
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
