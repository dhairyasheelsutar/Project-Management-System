package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/index_student")
public class index_student extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public index_student() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String name = (String)request.getSession().getAttribute("name");
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
			
			sql = "select logdate, log from grplog where grpid = (select stdgroup.grpid from stdgroup join student on stdgroup.grpid = student.grpid where student.userid = '"+ userid +"' and stdgroup.is_active <> 0)";
			List<List<Object>> grplog = new ArrayList<>();
			grplog = Crud.find_this_query(sql);
			
			String group_logs = "";
			if(grplog.isEmpty() == false) {
				
				group_logs += "<table class='table table-flush'>";
				group_logs += "<thead>";
				group_logs += "<tr>";
				group_logs += "<th>Date</td>";
				group_logs += "<th>Activity log</td>";
				group_logs += "<tr>";
				group_logs += "</thead>";
				group_logs += "<tbody>";
				for(int i=0;i<grplog.size();i++) {
					List<Object> grp = (List<Object>)grplog.get(i);
					
					group_logs += "<tr>";
					group_logs += "<td>"+ grp.get(0) +"</td>";
					group_logs += "<td><i>"+ grp.get(1) +"</i></td>";
					group_logs += "</tr>";
				}
				
				group_logs += "</tbody>";
				group_logs += "</table>";
				
			}else {
				group_logs = "Currently there are no group logs";
			}
			
			String schedule_str = "";
			List<List<Object>> schedule = new ArrayList<>();
			schedule = Crud.find_this_query("select * from schedule");
			
			if(schedule.isEmpty() == false) {
				for(int i=0;i<schedule.size();i++) {
			
					schedule_str += "<tr class='text-center'>";
					schedule_str += "<td>"+ schedule.get(i).get(1) +"</td>";
					schedule_str += "<td>"+ schedule.get(i).get(2) +"</td>";
					schedule_str += "<td>"+ schedule.get(i).get(3) +"</td>";
					schedule_str += "</tr>";
				}
				
			}else {
				schedule_str += "Schedule is not available";
			}
			
			sql = "select name, dname from user, teacher, teacher_domain, domain where user.userid = teacher.userid and teacher.tid = teacher_domain.tid and domain.did = teacher_domain.did order by name";
			List<List<Object>> staff_list = new ArrayList<>();
			staff_list = Crud.find_this_query(sql);
			String staff_list_str = "";
			if(staff_list.isEmpty() == false){
				int count = 0;
				String k = "";
				for(int i=0;i<staff_list.size();i++) {
					
					if(k.equals("") && count == 0) {
						staff_list_str += "<tr class='text-center'>";
						staff_list_str += "<td>"+ staff_list.get(i).get(0) +"</td>";
						staff_list_str += "<td>";
						staff_list_str += "<ul class='d-flex flex-wrap justify-content-center'>";
						staff_list_str += "<li class='px-1'>"+ staff_list.get(i).get(1) +" | </li>";
						k = (String)staff_list.get(i).get(0);
					}
					
					if(count != 0) {
						staff_list_str += "<li class='px-1'>"+ staff_list.get(i).get(1) +" | </li>";
					}
					
					count++;
					
					if(staff_list.size() != (i+1)) {
						if(staff_list.get(i).get(0).toString().equals(staff_list.get(i+1).get(0).toString()) == false) {
							staff_list_str += "</ul>";
							staff_list_str += "</tr>";
							count = 0;
							k = "";
						}
					}else {
						staff_list_str += "</ul>";
						staff_list_str += "</tr>";
						count = 0;
						k = "";
					}
				}
				
			}else {
				staff_list_str += "<p>No data available yet.</p>";
			}
			
			
			
			request.setAttribute("output", output);
			request.setAttribute("group_logs", group_logs);
			request.setAttribute("schedule", schedule_str);
			request.setAttribute("staff_list", staff_list_str);
			request.setAttribute("output", output);
			request.setAttribute("userid", userid);
			request.setAttribute("name", name);
			request.getRequestDispatcher("admin/student/index.jsp").forward(request, response);	
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
