package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/student_profile")
public class student_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public student_profile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String sql = "{call check_group_form('"+ userid +"')}";
			List<List<Object>> grp_form = Crud.execute_procedures(sql);
			int id = (int)grp_form.get(0).get(0);
			
			System.out.println(id);
			
			String output = "";
			
			if(id == -1) {
				
				//group not formed yet 
				
				sql = "select distinct userid, msg_body, start_date, msg_receipient.msgid from message join msg_receipient on message.msgid = msg_receipient.msgid where message.userid = '"+ userid +"' or msg_receipient.receipient_id = '"+ userid +"' and msg_receipient.is_read = 0 and msg_receipient.is_declined = 0";
				List<List<Object>> result = Crud.find_this_query(sql);
				
				for(int i=0; i<result.size();i++){
					output += "<div class='d-flex flex-wrap justify-content-between'>";
					output += "<div><h4>"+ result.get(i).get(1) +"</h4><p><i>Posted on "+ result.get(i).get(2) +"</i></p></div>";
					if(result.get(i).get(0).equals(userid) == false){
						output += "<div><a href='accept_group_request?msgid="+result.get(i).get(3)+"' data-userid='"+ userid +"' class='btn btn-success accept-group'>Accept</a><a href='decline_group_request' data-userid='"+ userid +"' class='btn btn-danger decline-group'>Decline</a></div>";
					}
					output += "</div>";
				}
			}else if(id == 0) {
				
				//group formed but not verified by administrator
				
				output += "<div>";
				output += "<p>Your request has been sent to administrator. You will get a group id once administrator verifies the group</p>";
				output += "</div>";
			}
			
			if(output.equals("")) {
				output = "Group join request notifications will be listed here";
			}
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/student/profile.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
