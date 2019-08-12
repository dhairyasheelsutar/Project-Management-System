package guide;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Functions;
import models.Review_2;
import models.Database;

@WebServlet("/review_2_submit")
public class review_2_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public review_2_submit() {
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
			String[] survey = request.getParameterValues("survey2");
			String[] design = request.getParameterValues("design2");
			String[] features = request.getParameterValues("features2");
			String[] planning = request.getParameterValues("planning2");
			String[] basic = request.getParameterValues("basic2");
			String[] skills = request.getParameterValues("skills2");
			String[] qa = request.getParameterValues("qa2");
			String[] summarize = request.getParameterValues("summarize2");
			
			Review_2[] review = new Review_2[roll.length];
			
			for(int i=0;i<review.length;i++) {
				review[i] = new Review_2(roll[i], survey[i], design[i], features[i], planning[i], basic[i], skills[i], qa[i], summarize[i], String.valueOf(Integer.parseInt(design[i]) + Integer.parseInt(features[i]) + Integer.parseInt(planning[i]) + Integer.parseInt(basic[i]) + Integer.parseInt(skills[i]) + Integer.parseInt(qa[i])));
			}
			
			String output = "";
			Functions functions = new Functions();
			Database database = new Database();
//4 members
			
			if(review.length == 4) {
				if(review[0].update() && review[1].update() && review[2].update() && review[3].update()) {
					output = functions.set_output_message("Review 2 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 2nd review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 2 completed')");
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
			}else if(review.length == 3) {
				
				//3 members
				
				if(review[0].update() && review[1].update() && review[2].update()) {
					output = functions.set_output_message("Review 2 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 2nd review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 2 completed')");
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
