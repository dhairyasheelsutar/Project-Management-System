package coordinator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/student_group")
public class student_group extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public student_group() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession(false).getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {

			String name = (String)request.getSession(false).getAttribute("name");
			String sql = "select distinct stdgroup.grpid, project_title, username, guidename, is_active from stdgroup, user, student where stdgroup.grpid = student.grpid and student.userid = user.userid";
			
			List<List<Object>> grpform = new ArrayList<>();
			grpform = Crud.find_this_query(sql);
			String output = "";
			if (grpform.isEmpty() == false) {
				int k = 1;
				int count = 0;
				int is_active = 0;
				String guidename = "";
				for(int i=0;i<grpform.size();i++) {
					List<Object> grp = grpform.get(i);
					if(k == (int)grp.get(0) && count == 0) {
						output += "<tr class='text-center'>";
						output += "<td>"+ grp.get(0) +"</td>";
						
						if(grp.get(1) == null) {
							output += "<td>N/A</td>";
						}else {
							output += "<td>"+ grp.get(1) +"</td>";
						}
						
						output += "<td>";
						output += "<ul class='d-flex flex-wrap justify-content-center'>";
						output += "<li class='px-1'>"+ grp.get(2) +"</li>";
						if(grp.get(3) == null) {
							guidename = "N/A";
						}else {
							guidename = (String)grp.get(3);
						}
						
						is_active = (int)grp.get(4);
					}
	
					if(count != 0) {
						output += "<li class='px-1'>"+ grp.get(2) +"</li>";
					}
					
					count++;
				
					if(grpform.size() != (i+1)) {
						
						if((int)grpform.get(i).get(0) != (int)grpform.get(i+1).get(0)) {
							output += "</ul>";
							output += "<td>"+ guidename +"</td>";
							output += "</td>";
							output += "<td>";
							output += "<ul class='d-flex flex-wrap justify-content-center'><li style='margin: 0 5px;'><a href='student_group_single?grpid="+ k +"'>View</a></li>";
							if(is_active == 0) {
								output += "<li style='margin: 0 5px;'><a href='approve_student_group?grpid="+ k +"'> | Approve</a></li>";
							}
							output += "</tr>";
							k++;
							count = 0;
						}
							
					}else {
						output += "</ul>";
						output += "<td>"+ guidename +"</td>";
						output += "</td>";
						output += "<td>";
						output += "<ul class='d-flex flex-wrap justify-content-center'><li style='margin: 0 5px;'><a href='student_group_single?grpid="+ k +"'>View</a></li>";
						if(is_active == 0) {
							output += "<li style='margin: 0 5px;'><a href='approve_student_group?grpid="+ k +"'> | Approve</a></li>";
						}
						output += "</tr>";
						k++;
						count = 0;
					}
				}
			}else {
				output += "<p>No any group requests yet.</p>";
			}
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("", "", "<i class='ni ni-tv-2'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='fa fa-users text-white'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			
			request.setAttribute("output", output);
			request.setAttribute("name", name);
			request.setAttribute("userid", userid);
			request.getRequestDispatcher("admin/co-ordinator/student_group.jsp").forward(request, response);
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
