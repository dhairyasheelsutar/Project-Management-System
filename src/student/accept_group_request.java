package student;

import java.io.IOException;
import models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accept_group_request")
public class accept_group_request extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public accept_group_request() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String name = (String)request.getSession().getAttribute("name");
			String msgid = request.getParameter("msgid");
			String sql = "update msg_receipient set is_read = 1 where receipient_id = '"+ userid +"' and msgid = "+ msgid +"";
			String output = "";
			Database database = new Database();
			int res = database.dml(sql);
			
			if(res != -1) {
				output += "<div>";
				output += "<p>Your request has been sent to administrator. You will get a group id once administrator verifies the group</p>";
				output += "</div>";
			}
			
			String query = "{call prepare_group("+ msgid +")}";
			Crud.execute_procedures(query);
			
			request.setAttribute("output", output);
			request.setAttribute("userid", userid);
			request.setAttribute("name", name);
			request.getRequestDispatcher("index_student").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
