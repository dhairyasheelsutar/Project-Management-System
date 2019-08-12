package controller;

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
import models.User;

@WebServlet("/update_staff_profile_guide")
public class update_staff_profile_guide extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public update_staff_profile_guide() {
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
			String username = (String)request.getParameter("username");
			String password = (String)request.getParameter("password");
			String email = (String)request.getParameter("email");
			String mobileno = (String)request.getParameter("mobno");
			String name = (String)request.getParameter("name");
			User user = new User(userid, username, password, name, email, mobileno);
			Functions functions = new Functions();
			Database database = new Database();
			String output = "";
			if(user.update()){
				output = functions.set_output_message("Profile Updated!", true);
				request.getSession().setAttribute("password", password);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("mobno", mobileno);
				request.getSession().setAttribute("name", name);
				String[] domains = request.getParameterValues("domains");
				String sql = "select tid from teacher where userid = '"+ userid +"'";
				List<List<Object>> result = Crud.find_this_query(sql);
				int tid = (int) result.get(0).get(0);
				
				sql = "select did from teacher_domain where tid = "+ tid;
				List<List<Object>>Present_domains = Crud.find_this_query(sql);
				
				if(domains == null){
					sql = "delete from teacher_domain where tid = " + tid;
					database.dml(sql);
				}
				
				
				System.out.println(Present_domains);
				
				for(int i=0;i<domains.length;i++) {
					System.out.println(i + " => " + domains[i]);
				}
				
				if(Present_domains.isEmpty() == false) {
					if(domains != null) {
						String query_insert = "insert into teacher_domain (tid, did) values ";
						String query_delete = "delete from teacher_domain where tid = " + tid + "";
						
						database.dml(query_delete);
						
						for(int i=0;i<domains.length;i++) {
							query_insert += "("+ tid +", "+ domains[i] +"),";
						}
						
						if(query_insert.equals("insert into teacher_domain (tid, did) values ") == false){
							query_insert = query_insert.substring(0, query_insert.length() - 1);
							database.dml(query_insert);
						}
					}
				}else {
					if(domains != null) {
						sql = "insert into teacher_domain (tid, did) values ";
						for(int i=0;i<domains.length;i++){
							sql  += "("+ tid +", "+ domains[i] +")";
							if( i < domains.length - 1 ){
								sql += ", ";
							}
						}
						database.dml(sql);
					}
				}		
				
			}else {
				output = functions.set_output_message("Something went wrong", false);
			}
			
			request.setAttribute("result", output);
			request.getRequestDispatcher("staff_profile_guide").forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
