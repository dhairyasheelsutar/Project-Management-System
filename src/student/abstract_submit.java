package student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import models.Abstract;
import models.Database;
import models.Functions;

@WebServlet("/abstract_submit")
public class abstract_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public abstract_submit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grpid = (String)request.getSession().getAttribute("group_id");
		String background = request.getParameter("background");
		String purpose = request.getParameter("purpose");
		String method = request.getParameter("methods");
		String results = request.getParameter("results");
		String conclusion = request.getParameter("conclusion");
		
		String result = "";
		Functions functions = new Functions();
		
		Abstract grpabstract = new Abstract(grpid, background, purpose,method,results,conclusion );
	
		if(grpabstract.create()) {
			result = functions.set_output_message("Value inserted", true);
			String sql = "insert into grplog (log, grpid, logdate, log_staff) values ('You have submitted project synopsis', "+ grpid +", now(), 'Group "+ grpid +" has submitted project synopsis')";
			Database database = new Database();
			database.dml(sql);
			
		}else {
			result = functions.set_output_message("something went wrong", false);
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("abstract_project").forward(request, response);
	}

}
