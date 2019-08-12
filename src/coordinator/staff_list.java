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


@WebServlet("/staff_list")
public class staff_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public staff_list() {
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
			String sql = "select name, email, mobileno, dname from user, teacher, teacher_domain, domain where user.userid = teacher.userid and teacher.tid = teacher_domain.tid and domain.did = teacher_domain.did order by name";
			List<List<Object>> staff_list = new ArrayList<>();
			staff_list = Crud.find_this_query(sql);
			String output = "";
			
			if(staff_list.isEmpty() == false){
				int count = 0;
				String k = "";
				for(int i=0;i<staff_list.size();i++) {
					
					if(k.equals("") && count == 0) {
						output += "<tr class='text-center'>";
						output += "<td>"+ staff_list.get(i).get(0) +"</td>";
						output += "<td>"+ staff_list.get(i).get(1) +"</td>";
						output += "<td>"+ staff_list.get(i).get(2) +"</td>";
						output += "<td>";
						output += "<ul class='d-flex flex-wrap justify-content-center'>";
						output += "<li class='px-1'>"+ staff_list.get(i).get(3) +" | </li>";
						k = (String)staff_list.get(i).get(0);
					}
					
					if(count != 0) {
						output += "<li class='px-1'>"+ staff_list.get(i).get(3) +" | </li>";
					}
					
					count++;
					
					if(staff_list.size() != (i+1)) {
						if(staff_list.get(i).get(0).toString().equals(staff_list.get(i+1).get(0).toString()) == false) {
							output += "</ul>";
							output += "</tr>";
							count = 0;
							k = "";
						}
					}else {
						output += "</ul>";
						output += "</tr>";
						count = 0;
						k = "";
					}
				}
				
			}else {
				output += "<p>No data available yet.</p>";
			}
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("", "", "<i class='ni ni-tv-2'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("", "", "<i class='fa fa-users text-blue'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='fa fa-list text-white'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/co-ordinator/staff_list.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
