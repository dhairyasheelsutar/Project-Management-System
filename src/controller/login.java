package controller;

import models.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Functions functions = new Functions();
    public login() {
        super();
    }
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sql = "select * from user where username = '"+ username +"' and password = '"+ password +"'";
		System.out.println("SQL: " + sql);
		List<List<Object>> user = new ArrayList<>();
		user = Crud.find_this_query(sql);
		
		if(user.isEmpty() == false) {
			String userid = (String) user.get(0).get(0);
			String name = (String) user.get(0).get(3);
			String Username = (String) user.get(0).get(1);
			String Password = (String) user.get(0).get(2);
			String email = (String) user.get(0).get(4);
			Long mobileno = (Long) user.get(0).get(5);
			
			
			HttpSession session = request.getSession(true);
			session.invalidate();
			
			session = request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("name", name);
			session.setAttribute("username", Username);
			session.setAttribute("password", Password);
			session.setAttribute("email", email);
			session.setAttribute("mobno", mobileno);
			
			
			sql = "{call check_group_form('"+ userid +"')}";
			List<List<Object>> grp_form = Crud.execute_procedures(sql);
			String group_id = grp_form.get(0).get(0).toString();
			
			if(group_id != null) {
				session.setAttribute("group_id", group_id);
			}
			
			if(userid.substring(0, 2).equals("st")) {
				request.getRequestDispatcher("index_student").forward(request, response);
			}else if(userid.substring(0, 2).equals("em")) {
				sql = "select cid from teacher_capability where tid in (select tid from teacher join user on teacher.userid = user.userid and user.userid = '"+ userid +"')";
				List<List<Object>> cid = new ArrayList<>();
				cid = Crud.find_this_query(sql);
				if(cid.size() == 1) {
					int empid = (int) cid.get(0).get(0);
					if(empid == 1000) {
						request.getRequestDispatcher("index_coordinator").forward(request, response);
					}else if(empid == 1001) {
						request.getRequestDispatcher("index_member").forward(request, response);
					}else if(empid == 1002) {
						request.getRequestDispatcher("index_assistant").forward(request, response);
					}else if(empid == 1003) {
						request.getRequestDispatcher("index_guide").forward(request, response);
					}else if(empid == 1004) {
						request.getRequestDispatcher("index_reviewer").forward(request, response);
					}
				}else {
					String html = "";
					
					html += "<form method='post' class='card' action='redirect_to_panel'>";
					html += "<div class='p-3 bg-primary'><h3 class='card-title text-white text-center text-uppercase' style='transform: translateY(15px)'>Login</h3></div>";
					html += "<div class = 'p-3'>";
					for(int i=0;i<cid.size();i++) {
						int capability = (int)cid.get(i).get(0);
						if(capability == 1000) {
							html += functions.print_radio_for_login(capability, "co-ordinator");
						}else if(capability == 1001) {
							html += functions.print_radio_for_login(capability, "commitee-member");
						}else if(capability == 1002) {
							html += functions.print_radio_for_login(capability, "commitee-assistant");
						}else if(capability == 1003) {
							html += functions.print_radio_for_login(capability, "guide");
						}else if(capability == 1004) {
							html += functions.print_radio_for_login(capability, "reviewer");
						}
					}
					
					html += "<div class='text-center'>";
					html += "<input type='submit' class='btn btn-warning' name='submit' value= 'Send' />";
					html += "</div>";
					html += "</div>";
					html += "</form>";
					
					request.setAttribute("html", html);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}else {
			String error = functions.set_output_message("Incorrect username and password combination", false);
			request.setAttribute("error", error);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
			
	}
	

}
