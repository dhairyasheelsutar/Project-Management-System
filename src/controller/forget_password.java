package controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;
import models.JavaEmail;

@WebServlet("/forget_password")
public class forget_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public forget_password() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		String sql = "select password from user where email = '"+ email +"' limit 1";
		List<List<Object>> result = Crud.find_this_query(sql);
		String password = result.get(0).get(0).toString();
		
		System.out.println(password);
		
		Functions functions = new Functions();
		String output = "";
		JavaEmail mail = new JavaEmail();
		try {
			mail.setMailServerProperties();
			mail.createEmailMessage(email, password);
			mail.sendEmail();
			output = functions.set_output_message("Password is sent to your email address", true);
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("error", output);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
