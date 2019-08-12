package coordinator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Database;
import models.Functions;

@WebServlet("/approve_student_group")
public class approve_student_group extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public approve_student_group() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String grpid = (String)request.getParameter("grpid");
			String sql = "update stdgroup set is_active = 1 where grpid = "+ grpid +"";
			
			String output = "";
			Database database = new Database();
			Functions functions = new Functions();
			int result = database.dml(sql);
			if(result != -1) {
				output = functions.set_output_message("Group "+ grpid +" is approved", true);
				sql = "insert into grplog (log, grpid, logdate, log_staff) values ('You got a group id "+ grpid +"', "+ grpid +", now(), 'Group "+ grpid +" is formed')";
				database.dml(sql);
			}else {
				output = functions.set_output_message("Group "+ grpid +" is not approved", false);
			}
			
			request.setAttribute("error", output);
			request.getRequestDispatcher("student_group").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
