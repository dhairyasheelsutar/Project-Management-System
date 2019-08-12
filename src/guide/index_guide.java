package guide;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/index_guide")
public class index_guide extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public index_guide() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String) request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String sql = "select * from stdgroup where guideid in (select tid from teacher where userid = '"+ userid +"')";
			List<List<Object>> result = Crud.find_this_query(sql);
			
			String output = "";
			
			if(result.isEmpty()) {
				output += "Currently you are not alloted to any group";
			}else {
				for(int i=0;i<result.size();i++) {
					output += "<tr class='text-center'>";
					output += "<td>"+ ((List<Object>) result.get(i)).get(0) +"</td>";
					output += "<td>"+ ((List<Object>) result.get(i)).get(1) +"</td>";
					output += "<td>"+ ((List<Object>) result.get(i)).get(2) +"</td>";
					output += "<td><a href='student_group_single_guide?grpid="+ ((List<Object>) result.get(i)).get(0) +"'>View</a></td>";
					output += "<tr>";
				}
			}
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/guide/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
