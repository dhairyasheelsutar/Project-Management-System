package guide;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;
import models.Review_4;


@WebServlet("/review_4_submit")
public class review_4_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public review_4_submit() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String user = (String)request.getSession().getAttribute("userid");
		
		if(user == null) {
			response.sendRedirect("index.jsp");
		}else if(user.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {

			String grpid = request.getParameter("grpid");
			String[] roll = request.getParameterValues("roll");
			String[] testing = request.getParameterValues("testing4");
			String[] report = request.getParameterValues("report4");
			String[] implementation = request.getParameterValues("implemention4");
			String[] skills = request.getParameterValues("skills4");
			String[] qa = request.getParameterValues("qa4");
			String[] publications = request.getParameterValues("publications4");
			Review_4[] review = new Review_4[roll.length];
			
			for(int i=0;i<review.length;i++) {
				review[i] = new Review_4(roll[i], implementation[i], testing[i], report[i], publications[i], skills[i], qa[i], String.valueOf(Integer.parseInt(implementation[i]) + Integer.parseInt(testing[i]) + Integer.parseInt(skills[i]) + Integer.parseInt(qa[i]) + Integer.parseInt(report[i]) + Integer.parseInt(publications[i])));
			}
			
			String output = "";
			Functions functions = new Functions();
			Database database = new Database();
//4 members
			
			if(review.length == 4) {
				if(review[0].update() && review[1].update() && review[2].update() && review[3].update()) {
					output = functions.set_output_message("Review 4 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 4th review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 4 completed')");
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
			}else if(review.length == 3) {
				
				//3 members
				
				if(review[0].update() && review[1].update() && review[2].update()) {
					output = functions.set_output_message("Review 4 updated successfully", true);
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
			}
			
			request.setAttribute("review_result", output);
			request.getRequestDispatcher("student_group_single_guide?grpid=" + grpid).forward(request, response);
			
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
