package coordinator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/student_list")
public class student_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public student_list() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			
			String output = "";
			String sql = "select username, name, email, mobileno from user where userid like 'st%'";
			List<List<Object>> result = Crud.find_this_query(sql);
			
			if(result.isEmpty()) {
				output = "No data available currently";
			}else {
				for(int i=0;i<result.size();i++) {
					output += "<tr>";
					output += "<td>"+ result.get(i).get(0) +"</td>";
					output += "<td>"+ result.get(i).get(1) +"</td>";
					output += "<td>"+ result.get(i).get(2) +"</td>";
					output += "<td>"+ result.get(i).get(3) +"</td>";
					output += "</tr>";
				}
			}
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("", "", "<i class='ni ni-tv-2'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("", "", "<i class='fa fa-users text-blue'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='fa fa-address-book text-white'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/co-ordinator/student_list.jsp").forward(request, response);
		}
		
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
