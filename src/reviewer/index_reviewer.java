package reviewer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/index_reviewer")
public class index_reviewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public index_reviewer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			String sql = "select stdgroup.grpid, project_title, guidename from user, stdgroup, grp_reviewer, teacher where user.userid = teacher.userid and teacher.tid = grp_reviewer.tid and stdgroup.grpid = grp_reviewer.grpid and grp_reviewer.tid = (select tid from teacher where userid = '"+ userid +"')";
			List<List<Object>> result = Crud.find_this_query(sql);
			String output = "";
			if(result.isEmpty()) {
				output = "Currently no groups allted to you";
			}else {
				
				for(int i=0;i<result.size();i++) {
					output += "<tr>";
					output += "<td>"+ result.get(i).get(0) +"</td>";
					output += "<td>"+ result.get(i).get(1) +"</td>";
					output += "<td>"+ result.get(i).get(2) +"</td>";
					output += "<td><a href='review_comment?grpid="+ result.get(i).get(0) +"'>View</td>";
					output += "</tr>";
				}
				
			}
			
			request.setAttribute("output", output);
			request.getRequestDispatcher("admin/reviewer/index.jsp").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
