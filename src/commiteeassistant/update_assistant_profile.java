package commiteeassistant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Functions;
import models.User;

@WebServlet("/update_assistant_profile")
public class update_assistant_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public update_assistant_profile() {
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
			request.getRequestDispatcher("admin/commitee-assistant/profile.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String output = "";
			if(user.update()){
				output = functions.set_output_message("Profile Updated!", true);
				request.getSession().setAttribute("password", password);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("mobno", mobileno);
				request.getSession().setAttribute("name", name);
				
			}else {
				output = functions.set_output_message("Something went wrong", false);
			}
			
			request.setAttribute("result", output);
			request.getRequestDispatcher("student_profile").forward(request, response);
		}
		
			
	}

}
