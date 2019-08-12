package commiteeassistant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Domain;
import models.Functions;

@WebServlet("/add_domain")
public class add_domain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public add_domain() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			request.getRequestDispatcher("admin/commitee-assistant/add_domain.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String domain_staff = request.getParameter("domain");
			Domain domain = new Domain(domain_staff);
			Functions functions = new Functions();
			String output = "";
			if(domain.create()) {
				output = functions.set_output_message("Domain inserted", true);
			}else {
				output = functions.set_output_message("Something went Wrong", false);
			}
			request.setAttribute("domain", output);
			request.getRequestDispatcher("index_assistant").forward(request, response);
		}
		
			
	}
}
