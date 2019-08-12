package coordinator;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Functions;
import models.Schedule;

@WebServlet("/insert_schedule")
public class insert_schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public insert_schedule() {
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
			String fileInFolder = "";
			String relativePath = "/admin/student/templates";
			String absPath = getServletContext().getRealPath(relativePath);
			File file = new File(absPath);
	        File[] files = file.listFiles();
	        for(File f: files){
	        	fileInFolder += "<p style='cursor: pointer;' class='schedule_template' data-file = '"+ f.getName() +"'>"+ f.getName() +"</p>";
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
	        
			request.setAttribute("files", fileInFolder);
			request.getRequestDispatcher("admin/co-ordinator/insert_schedule.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String template = request.getParameter("template_file_src");
			String start_date = request.getParameter("start_date");
			String expiry_date = request.getParameter("end_date");
			String desc = request.getParameter("message");
			
			System.out.println(start_date);
			
			String output = "";
			Functions functions = new Functions();
			
			Schedule sch = new Schedule(start_date, expiry_date, desc, template, "0");
			
			if(sch.create()) {
				output = functions.set_output_message("Schedule has been created", true);
			}else {
				output = functions.set_output_message("Something went wrong", false);
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
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/co-ordinator/insert_schedule.jsp").forward(request, response);
		}
		
			
		
	}

}
