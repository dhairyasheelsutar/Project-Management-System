package reviewer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Comment;
import models.Functions;

@WebServlet("/review_3_add_comment")
public class review_3_add_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public review_3_add_comment() {
        super();
       
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
			String grpid = request.getParameter("groupid");
			
			String review_3 = request.getParameter("comment3");
			
			String query = "select tid from teacher where userid = '"+ userid +"'";
			
			List<List<Object>> teacher_id = Crud.find_this_query(query);
			String tid = teacher_id.get(0).get(0).toString();
			Functions functions = new Functions();
			
			String query1 = "select * from grp_reviewer where tid = "+ tid +"";
			
			List<List<Object>> temp = Crud.find_this_query(query1);
			
			System.out.println(temp);
			
			System.out.println(query1);
			System.out.println(temp.get(0).size());
			
			String temp_store2 = "";
			String temp_store1 = "";
			String temp_store4 = "";
			
			if(temp.get(0).get(3) != null) {
				temp_store2 = temp.get(0).get(3).toString();
			}
			
			if(temp.get(0).get(2) != null) {
				temp_store1 = temp.get(0).get(2).toString();
			}
			
			if(temp.get(0).get(5) != null) {
				temp_store4 = temp.get(0).get(5).toString();
			}
			
			Comment comment = new Comment(grpid, tid, temp_store1 , temp_store2 , review_3 , temp_store4 );
			String error = "";	
			
			if(comment.update()) {
				error = functions.set_output_message("Successfully Updated", true);
			}else {
				error = functions.set_output_message("Something went wrong", false);
			}
			
			request.setAttribute("comment3", error);
			request.getRequestDispatcher("review_comment?grpid="+ grpid +"").forward(request, response);
		}
		
		
			
	}

}

