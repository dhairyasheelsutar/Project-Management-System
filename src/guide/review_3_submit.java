package guide;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;
import models.Review_3;


@WebServlet("/review_3_submit")
public class review_3_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public review_3_submit() {
        super();
        // TODO Auto-generated constructor stub
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
			String[] design = request.getParameterValues("design3");
			String[] results = request.getParameterValues("results3");
			String[] implementation = request.getParameterValues("implemention3");
			String[] skills = request.getParameterValues("skills3");
			String[] qa = request.getParameterValues("qa3");
			String[] summarize = request.getParameterValues("summarize3");
			
			Review_3[] review = new Review_3[roll.length];
			
			for(int i=0;i<review.length;i++) {
				review[i] = new Review_3(roll[i], design[i], implementation[i], results[i], skills[i], qa[i], summarize[i], String.valueOf(Integer.parseInt(implementation[i]) + Integer.parseInt(results[i]) + Integer.parseInt(skills[i]) + Integer.parseInt(qa[i])));
			}
			
			String output = "";
			Functions functions = new Functions();
			Database database = new Database();
//4 members
			
			if(review.length == 4) {
				if(review[0].update() && review[1].update() && review[2].update() && review[3].update()) {
					output = functions.set_output_message("Review 3 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 3rd review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 3 completed')");
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
			}else if(review.length == 3) {
				
				//3 members
				
				if(review[0].update() && review[1].update() && review[2].update()) {
					output = functions.set_output_message("Review 3 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 3rd review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 3 completed')");
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
