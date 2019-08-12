package coordinator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Database;
import models.Functions;


@WebServlet("/active_schedule_form")
public class active_schedule_form extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public active_schedule_form() {
        super();
        // TODO Auto-generated constructor stub
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
			String sch_id = request.getParameter("sch_id");
			Database database = new Database();
			Functions functions = new Functions();
			String output = "";
			
			String sql = "select count(sch_id) from schedule where is_active = 1";
			List<List<Object>>rs = Crud.find_this_query(sql);
			String sch = rs.get(0).get(0).toString();
			if(sch.equals("0")){
				sql = "update schedule set is_active = 1 where sch_id = "+ sch_id +"";
				int result = database.dml(sql);
				
				if(result != -1) {
					output = functions.set_output_message("Task "+ sch_id +" is activated", true);
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}
				
			}else {
				output = functions.set_output_message("Only 1 task can be activated at a time", false);
			}
			
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
				
			request.setAttribute("error_msg", output);
			request.getRequestDispatcher("schedule").forward(request, response);
		}
		
			
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
