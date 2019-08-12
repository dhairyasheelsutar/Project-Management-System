package commiteeassistant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Domain;
import models.Functions;

@WebServlet("/update_domain")
public class update_domain extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public update_domain() {
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
			String did = request.getParameter("did");
			String dname = request.getParameter("dname");
			Domain domain = new Domain(dname);
			domain.did = did;
			
			Functions functions = new Functions();
			String output = "";
			
			if(domain.update()) {
				output = functions.set_output_message("Values updated!", true);
			}else {
				output = functions.set_output_message("Something went wrong", false);
			}
			request.setAttribute("domain_result", output);
			request.getRequestDispatcher("staff_domain").forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
