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

@WebServlet("/updates")
public class updates extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public updates() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else {
			String sql = "select template from schedule where is_active = 1 limit 1";
			List<List<Object>> result = new ArrayList<>();
			result = Crud.find_this_query(sql);
			
			String output = "";
			
			if(result.isEmpty()) {
				output = "Currently no updates";
				request.setAttribute("output", output);
				request.getRequestDispatcher("admin/student/updates.jsp").forward(request, response);
			}else {
				List<Object> template = (List<Object>)result.get(0);
				String file_template =(String) template.get(0);
				file_template = file_template.substring(0, file_template.length() - 4);
				
				sql = "{call check_group_form('"+ userid +"')}";
				List<List<Object>> grp_form = Crud.execute_procedures(sql);
				String group_id = grp_form.get(0).get(0).toString();
				
				sql = "{call check_sequence("+ group_id +", '"+ file_template +"')}";
				grp_form = Crud.execute_procedures(sql);
				String url_to_redirect = (String)grp_form.get(0).get(0);
				request.getRequestDispatcher(url_to_redirect).forward(request, response);
			}
		}
		
			
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
