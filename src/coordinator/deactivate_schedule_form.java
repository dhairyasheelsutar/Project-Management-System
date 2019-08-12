package coordinator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;

@WebServlet("/deactivate_schedule_form")
public class deactivate_schedule_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public deactivate_schedule_form() {
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
			String sch_id = request.getParameter("sch_id");
			Database database = new Database();
			Functions functions = new Functions();
			String output = "";
			String sql = "update schedule set is_active = 0 where sch_id = "+ sch_id +"";
			int result = database.dml(sql);
			
			if(result != -1) {
				output = functions.set_output_message("Schedule "+ sch_id +" is deactivated", true);
			}else {
				output = functions.set_output_message("Something went wrong", false);
			}
			
			request.setAttribute("error_msg", output);
			request.getRequestDispatcher("schedule").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
