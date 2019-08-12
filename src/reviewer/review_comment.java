package reviewer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/review_comment")
public class review_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public review_comment() {
        super();
       
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String grpid = (String)request.getParameter("grpid");
		String userid = (String)request.getSession().getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {

			String sql = "select * from stdgroup where grpid = "+ grpid +"";
			List<List<Object>> grpinfo = Crud.find_this_query(sql);
			
			String project_title = "N/A";
			
			if(grpinfo.get(0).get(1) != null) {
				project_title = (String) grpinfo.get(0).get(1);
			}
			
			String project_domain = "N/A";
			
			if(grpinfo.get(0).get(2) != null) {
				project_domain = (String) grpinfo.get(0).get(2);
			}
			
			String guidename = "N/A";
			
			if(grpinfo.get(0).get(4) != null) {
				guidename = (String)grpinfo.get(0).get(4);
			}
		
			sql = "select dname, domain.did from group_domain, domain where group_domain.did = domain.did and group_domain.grpid = " + grpid;
			List<List<Object>> grp_domain = Crud.find_this_query(sql);
			
			String select_domain = "<select name='project_domain'  class='form-control' disabled required>";
			for(int i=0;i<grp_domain.size();i++) {
				
				String submitted_domain = (String) ((List<Object>) grp_domain.get(i)).get(0);
				if(project_domain.equals(submitted_domain)) {
					select_domain += "<option value='"+ ((List<Object>) grp_domain.get(i)).get(0) +"' selected>"+ ((List<Object>) grp_domain.get(i)).get(0) +"</option>";
				}else {
					select_domain += "<option value='"+ ((List<Object>) grp_domain.get(i)).get(0) +"'>"+ ((List<Object>) grp_domain.get(i)).get(0) +"</option>";
				}
				
				
			}
			select_domain += "</select>";
			
			sql = "select username, name, email, mobileno, user.userid from user, student where user.userid = student.userid and student.grpid = " + grpid;
			List<List<Object>>student_info = Crud.find_this_query(sql);
			
			String group_members = "";
			
			for(int i=0;i<student_info.size();i++) {
				group_members += "<tr>";
				group_members += "<td>"+ student_info.get(i).get(0) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(1) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(2) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(3) +"</td>";
				group_members += "</tr>";
			}
			
			sql = " select name, teacher.tid from user, teacher, guide_preferences where user.userid = teacher.userid and teacher.tid = guide_preferences.guideid and guide_preferences.grpid = " + grpid;
			List<List<Object>>guide_preferences = Crud.find_this_query(sql);
	
			String select_guide = "<select name='guide' id='guide' class='form-control' disabled required>";
			select_guide += "<option value='NULL'>Select Guide</option>";
			
			for(int i=0;i<guide_preferences.size();i++) {
				
				String pref = (String)((List<Object>) guide_preferences.get(i)).get(0);
				
				if(pref.equals(guidename)) {
					select_guide += "<option  value='"+ ((List<Object>) guide_preferences.get(i)).get(1) +"' selected>"+ ((List<Object>) guide_preferences.get(i)).get(0) +"</option>";
				}else {
					select_guide += "<option value='"+ ((List<Object>) guide_preferences.get(i)).get(1) +"'>"+ ((List<Object>) guide_preferences.get(i)).get(0) +"</option>";
				}
			}
			
			select_guide += "</select>";
			
			sql = "select * from grp_reviewer where tid in (select tid from teacher where userid = '"+ userid +"') and grpid = "+ grpid +"";
			System.out.println(sql);
			List<List<Object>> comments = Crud.find_this_query(sql);
			
			String review1 = ""; 
			String review2 = ""; 
			String review3 = ""; 
			String review4 = "";
			
			
			if(comments.isEmpty() == false) {
				
				if(comments.get(0).get(2) != null) {
					review1 = comments.get(0).get(2).toString();
				}
				
				if(comments.get(0).get(3) != null) {
					review2 = comments.get(0).get(3).toString();
				}
				
				if(comments.get(0).get(4) != null) {
					review3 = comments.get(0).get(4).toString();
				}
				
				if(comments.get(0).get(5) != null) {
					review4 = comments.get(0).get(5).toString();
				}
				
				
			}
			
			request.setAttribute("grpid", grpid);
			request.setAttribute("project_title", project_title);
			request.setAttribute("select_domain", select_domain);
			request.setAttribute("group_members", group_members);
			request.setAttribute("select_guide", select_guide);
			request.setAttribute("guidename", guidename);
			request.setAttribute("review1", review1);
			request.setAttribute("review2", review2);
			request.setAttribute("review3", review3);
			request.setAttribute("review4", review4);
			
			request.getRequestDispatcher("admin/reviewer/review_comment.jsp").forward(request, response);
		}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
