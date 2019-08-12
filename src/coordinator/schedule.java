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

@WebServlet("/schedule")
public class schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public schedule() {
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

			String name = (String)request.getSession().getAttribute("name");
			
			String output = "";
			List<List<Object>> schedule = new ArrayList<>();
			schedule = Crud.find_this_query("select * from schedule");
			
			if(schedule.isEmpty() == false) {
				for(int i=0;i<schedule.size();i++) {
			
					output += "<tr class='text-center'>";
					output += "<td>"+ schedule.get(i).get(0) +"</td>";
					output += "<td>"+ schedule.get(i).get(1) +"</td>";
					output += "<td>"+ schedule.get(i).get(2) +"</td>";
					output += "<td>"+ schedule.get(i).get(3) +"</td>";
					output += "<td>"+ schedule.get(i).get(4) +"</td>";
					
					int is_active = (int)schedule.get(i).get(5);
					
					if(is_active == 0) {
						output += "<td><a href='active_schedule_form?sch_id="+ schedule.get(i).get(0) +"'>Activate</a></td>";
					}else {
						output += "<td><a href='deactivate_schedule_form?sch_id="+ schedule.get(i).get(0) +"'>Deactivate</a></td>";
					}
					
					
					output += "</tr>";
				}
				
			}else {
				output += "Schedule is not available";
			}
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("", "", "<i class='ni ni-tv-2'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("", "", "<i class='fa fa-users text-blue'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='fa fa-clock-o text-white'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			
			request.setAttribute("userid", userid);
			request.setAttribute("name", name);
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/co-ordinator/schedule.jsp").forward(request, response);
		}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
