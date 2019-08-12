package student;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;
import models.Notification_receipient;
import models.Notification_sender;
import models.Notifications;


@WebServlet("/form_group")
public class form_group extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public form_group() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String grpid = (String)request.getSession().getAttribute("group_id");
			
			if(grpid.equals("-1") || grpid.equals("0")) {
				
				String sql = "select username from user, student where student.grpid = 0 and student.userid = user.userid and student.userid <> '"+ userid +"'";
				List<List<Object>> result = Crud.find_this_query(sql);
				String output = "";
				
				if(result.isEmpty()) {
					output += "<option>All students formed group</option>";
				}else {
					for(int i=0;i<result.size();i++) {
						output += "<option value='"+ result.get(i).get(0) +"'>"+ result.get(i).get(0) +"</option>";
					}
				}
				
				request.setAttribute("roll_numbers", output);
				request.getRequestDispatcher("admin/student/templates/form_group.jsp").forward(request, response);
			}else {
				
				String output = "";
				Functions functions = new Functions();
				output = functions.set_output_message("You have already formed group", true);
				request.setAttribute("output", output);
				request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String sender_name = (String)request.getSession().getAttribute("name");
			
			Functions functions = new Functions();
			String output = "";
			
			String roll2 = request.getParameter("roll2");
			String roll3 = request.getParameter("roll3");
			String sql = "";
			
			int flag = 0;
			
			if(request.getParameter("roll4").equals("null") == false) {
				String roll4  = request.getParameter("roll4");
				if(roll2.equals("null") || roll3.equals("null")) {
					flag = 1;
				}else if(sender_name.equals(roll2) || sender_name.equals(roll3) || sender_name.equals(roll4) || roll2.equals(roll3) || roll3.equals(roll4) || roll2.equals(roll4) ) {
					flag = -1;
				}
				sql = "select name, userid from user where username in ('"+ roll2 +"', '"+ roll3 +"', '"+ roll4 +"')";
			}else {
				
				if(roll2.equals("null") || roll3.equals("null")) {
					flag = 1;
				}else if(sender_name.equals(roll2) || sender_name.equals(roll3) || roll2.equals(roll3)) {
					flag = -1;
				}
				sql = "select name, userid from user where username in ('"+ roll2 +"', '"+ roll3 +"')";
			}
			
			if(flag == 0) {
				List<List<Object>> result = new ArrayList<>();
				result = Crud.find_this_query(sql);
				
				
				if(result.isEmpty() == false) {
					String[] receipient_names = new String[result.size()];
					String[] uniqueids = new String[result.size()];
					
					
					
					for(int i=0;i<result.size();i++) {
						List<Object> rs = (List<Object>) result.get(i);
						receipient_names[i] = (String)rs.get(0);
						uniqueids[i] = (String)rs.get(1);
					}
					
					
					String msg_body = "";
					
					if(result.size() == 2) {
						msg_body = sender_name + " invited " + receipient_names[0] + ", " + receipient_names[1] + " to join group";
						Notification_sender sender = new Notification_sender(msg_body, userid, "now()", "2");
						Notification_receipient[] receiver = new Notification_receipient[2];
						receiver[0] = new Notification_receipient(uniqueids[0], "0", "0", "0");
						receiver[1] = new Notification_receipient(uniqueids[1], "0", "0", "0");
						
						Notifications notifications = new Notifications(sender, receiver);
						
						if(notifications.create()) {
							output = functions.set_output_message("Group join request has been sent", true);
						}else {
							output = functions.set_output_message("Something went wrong", false);
						}
						
						
					}else {
						System.out.println("Hello World");
						msg_body = sender_name + " invited " + receipient_names[0] + ", " + receipient_names[1] + ", " + receipient_names[2] +" to join group";
						Notification_sender sender = new Notification_sender(msg_body, userid, "now()", "3");
						Notification_receipient[] receiver = new Notification_receipient[3];
						receiver[0] = new Notification_receipient(uniqueids[0], "0", "0", "0");
						receiver[1] = new Notification_receipient(uniqueids[1], "0", "0", "0");
						receiver[2] = new Notification_receipient(uniqueids[2], "0", "0", "0");
						
						Notifications notifications = new Notifications(sender, receiver);
						
						if(notifications.create()) {
							output = functions.set_output_message("Group join request has been sent", true);
						}else {
							output = functions.set_output_message("Something went wrong", false);
						}
						
					}
					
				}else {
					output = functions.set_output_message("Enter valid roll numbers", false);
				}
			}else if(flag == -1){
				output = functions.set_output_message("Please enter distinct roll numbers", false);
			}else if(flag == 1) {
				output = functions.set_output_message("Please select roll numbers", false);
			}
			
			sql = "select username from user, student where student.grpid = 0 and student.userid = user.userid";
			List<List<Object>> result = Crud.find_this_query(sql);
			String error = "";
			
			if(result.isEmpty()) {
				error += "<option>All students formed group</option>";
			}else {
				for(int i=0;i<result.size();i++) {
					error += "<option value='"+ result.get(i).get(0) +"'>"+ result.get(i).get(0) +"</option>";
				}
			}
			
			request.setAttribute("roll_numbers", error);
			request.setAttribute("result", output);
			request.getRequestDispatcher("form_group_result").forward(request, response);
			
		}
		
		
		
			
		
	}

}
