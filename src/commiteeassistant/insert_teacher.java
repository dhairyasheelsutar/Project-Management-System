package commiteeassistant;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;

@WebServlet("/insert_teacher")
public class insert_teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Functions functions = new Functions();
    
    public insert_teacher() {
        super();
       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    	String sessionid = (String)request.getSession().getAttribute("userid");
		String name = (String)request.getSession().getAttribute("name");
		
		if(sessionid == null) {
			response.sendRedirect("index.jsp");
		}else if(sessionid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			request.setAttribute("name", name);
			request.setAttribute("userid", sessionid);
			request.getRequestDispatcher("admin/commitee-assistant/insert_teacher.jsp").forward(request, response);
		}
		
			
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String sessionid = (String)request.getSession().getAttribute("userid");
		
		if(sessionid == null) {
			response.sendRedirect("index.jsp");
		}else if(sessionid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String name = (String)request.getSession().getAttribute("name");
			request.setAttribute("name", name);
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String Name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobileno = request.getParameter("mobileno");
			
			String sql = "select * from user where username = '"+ username +"'";
			List<List<Object>> userFound = new ArrayList<>();
			userFound = Crud.find_this_query(sql);
			
			String error = "";
			String []capabilities = request.getParameterValues("teacher");
			String Capability_code = "";
			
			for(String cap : capabilities) {
				Capability_code += cap;
			}
			
			
			if(userFound.isEmpty() == true) {
				String userid = "em" + Year.now().getValue() + username + Capability_code;
				User user = new User(userid, username, password, Name, email, mobileno);
				if(user.create()) {
					error = functions.set_output_message("Successfully Registered!", true);
				}else {
					error = functions.set_output_message("User cannot be registered", false);
				}
				
			}else {
				error = functions.set_output_message("Username already exists", false);
			}
			
			request.setAttribute("error", error);
			request.setAttribute("userid", sessionid);
			request.getRequestDispatcher("admin/commitee-assistant/insert_teacher.jsp").forward(request, response);
		}
		
		
		
	}

}
