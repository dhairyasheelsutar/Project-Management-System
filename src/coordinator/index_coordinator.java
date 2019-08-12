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

@WebServlet("/index_coordinator")
public class index_coordinator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public index_coordinator() {
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
			request.setAttribute("name", name);
			request.setAttribute("userid", userid);
			
			String sql = "select count(sid) from student";
			System.out.println(sql);
			List<List<Object>>student = Crud.find_this_query(sql);
			
			System.out.println(student);
			
			sql = "select count(tid) from teacher";
			List<List<Object>>teacher = Crud.find_this_query(sql);
			
			System.out.println(teacher);
			
			sql = "select count(grpid) from stdgroup";
			List<List<Object>> stdgroup = Crud.find_this_query(sql);
			
			sql = "select count(did) from domain";
			List<List<Object>> domain = Crud.find_this_query(sql);
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='ni ni-tv-2 text-white'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("", "", "<i class='fa fa-users text-blue'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			request.setAttribute("student", student.get(0).get(0));
			request.setAttribute("staff", teacher.get(0).get(0));
			request.setAttribute("group", stdgroup.get(0).get(0));
			request.setAttribute("domain", domain.get(0).get(0));
			request.getRequestDispatcher("admin/co-ordinator/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
