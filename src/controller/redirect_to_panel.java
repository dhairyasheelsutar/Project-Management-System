package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect_to_panel")
public class redirect_to_panel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public redirect_to_panel() {
        super();
    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("radiobtn");
		
		if(id.equals("1000")) {
			request.getRequestDispatcher("index_coordinator").forward(request, response);
		}else if(id.equals("1001")) {
			request.getRequestDispatcher("admin/commitee-member/index.jsp").forward(request, response);
		}else if(id.equals("1002")) {
			request.getRequestDispatcher("index_assistant").forward(request, response);
		}else if(id.equals("1003")) {
			request.getRequestDispatcher("index_guide").forward(request, response);
		}else if(id.equals("1004")) {
			request.getRequestDispatcher("index_reviewer").forward(request, response);
		}
	}

}
