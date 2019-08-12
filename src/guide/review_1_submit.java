package guide;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.Functions;
import models.Review_1;


@WebServlet("/review_1_submit")
public class review_1_submit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public review_1_submit() {
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
			String[] background1 = request.getParameterValues("background1");
			String[] scope1 = request.getParameterValues("scope1");
			String[] skills1 = request.getParameterValues("skills1");
			String[] survey1 = request.getParameterValues("survey1");
			String[] planning1 = request.getParameterValues("planning1");
			String[] qa1 = request.getParameterValues("qa1");
			
			Review_1[] review1 = new Review_1[roll.length];
			for(int i=0;i<review1.length;i++) {
				review1[i] = new Review_1(roll[i], background1[i], scope1[i], survey1[i], planning1[i], skills1[i], qa1[i], String.valueOf(Integer.parseInt(background1[i]) + Integer.parseInt(skills1[i]) + Integer.parseInt(scope1[i]) + Integer.parseInt(survey1[i]) + Integer.parseInt(planning1[i]) + Integer.parseInt(qa1[i])));
			}
			
			String output = "";
			Functions functions = new Functions();
			Database database = new Database();
			
			//4 members
			
			if(review1.length == 4) {
				if(review1[0].update() && review1[1].update() && review1[2].update() && review1[3].update()) {
					output = functions.set_output_message("Review 1 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 1st review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 1 completed')");
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
			}else if(review1.length == 3) {
				
				//3 members
				
				if(review1[0].update() && review1[1].update() && review1[2].update()) {
					output = functions.set_output_message("Review 1 updated successfully", true);
					database.dml("insert into grplog (log, grpid, logdate, log_staff) values ('Your 1st review is completed', "+ grpid +", now(), 'Group ID "+ grpid +" review 1 completed')");
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
