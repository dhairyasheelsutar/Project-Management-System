package student;

import models.*;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_student")
public class register_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Functions functions = new Functions();
  
    public register_student() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("register_student.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobileno = request.getParameter("mobileno");
		
		String error = "";
		
		if(username.substring(0, 2).equals("40") || username.substring(0, 2).equals("49")) {
			String sql = "select * from user where username = '"+ username +"' or email = '"+ email +"'";
			List<List<Object>> userFound = new ArrayList<>();
			userFound = Crud.find_this_query(sql);
			
			
			
			if(userFound.isEmpty() == true) {
				String userid = "st" + Year.now().getValue() + username;
				User user = new User(userid, username, password, name, email, mobileno);
				if(user.create()) {
					password = name = email = mobileno = "";
					error = functions.set_output_message("Successfully Registered!", true);
				}else {
					error = functions.set_output_message("User cannot be registered", false);
				}
				
			}else {
				
				error = functions.set_output_message("Username or Email already exists", false);
			}
		}else {
			error = functions.set_output_message("Enter valid roll number", false);
		}
		
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("mobileno", mobileno);
		request.setAttribute("error", error);
		request.getRequestDispatcher("./register_student.jsp").forward(request, response);
	}

}
