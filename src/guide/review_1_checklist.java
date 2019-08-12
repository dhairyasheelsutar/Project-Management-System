package guide;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;

@WebServlet("/review_1_checklist")
public class review_1_checklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public review_1_checklist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database database = new Database();
		Functions functions = new Functions();
		
		//16 number of questions in checklist. from 1-16 in database
		
		String[] values = new String[16];
		String grpid = request.getParameter("grpid");
		
		
		for(int i=0;i<16;i++) {
			values[i] = request.getParameter("check1list" + i);
		}
		
		//update query construction
		
		String query = "update checklist set grpid" + grpid + " = ( case";
		for(int i=0;i<16;i++) {
			int j=i+1;
			query += " when id = " + j + " then " + values[i];
		}
		query += " end ) where type = 1";
		
		String output = "";
		
		if(database.dml(query) != -1) {
			output = functions.set_output_message("Review 1 checklist updated", true);
		}else {
			output = functions.set_output_message("Something went wrong", false);
		}
		
		request.setAttribute("review_result", output);
		
		//forward to servlet with grpid (imp).
		
		request.getRequestDispatcher("student_group_single_guide?grpid=" + grpid).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
