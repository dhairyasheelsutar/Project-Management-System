package commiteeassistant;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/staff_members")
public class staff_members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public staff_members() {
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
			String sql = "select name, email, mobileno from teacher, user where teacher.userid = user.userid";
			List<List<Object>> result = Crud.find_this_query(sql);
			String output = "";
			if(result.isEmpty()) {
				output += "Currently no data available";
			}else {
				
				for(int i=0;i<result.size();i++) {
					output += "<tr>";
					output += "<td>"+ result.get(i).get(0) +"</td>";
					output += "<td>"+ result.get(i).get(1) +"</td>";
					output += "<td>"+ result.get(i).get(2) +"</td>";
					output += "<td><a href='#'>Delete</a></td>";
					output += "</tr>";
				}
				
			}
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/commitee-assistant/staff_members.jsp").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
