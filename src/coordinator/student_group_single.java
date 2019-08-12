package coordinator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/student_group_single")
public class student_group_single extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public student_group_single() {
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
			
			String grpid = (String)request.getParameter("grpid");
			
			String sql = "select * from stdgroup left outer join sponsorship on sponsorship.sponsor_id = stdgroup.sponsor_id where stdgroup.grpid = " + grpid;
			List<List<Object>> grpinfo = Crud.find_this_query(sql);
			System.out.println(grpinfo.toString());
			String pbs_id = grpinfo.get(0).get(7).toString();
			
			String project_title = "";

			
			if(grpinfo.get(0).get(1) == null) {
				project_title = "N/A";
			}else {
				project_title = (String)grpinfo.get(0).get(1);
			}
			
			int sponsor_id = (int)grpinfo.get(0).get(3);
			
			String project_domain = (String)grpinfo.get(0).get(2);
			String guidename = "";
			
			if(grpinfo.get(0).get(4) == null) {
				guidename = "N/A";
			}else {
				guidename = (String)grpinfo.get(0).get(4);
			}
			
			System.out.println(guidename);
			
			sql = "select dname, domain.did from group_domain, domain where group_domain.did = domain.did and group_domain.grpid = " + grpid;
			List<List<Object>> grp_domain = Crud.find_this_query(sql);
			
			String select_domain = "<select name='project_domain'  class='form-control' required>";
			select_domain += "<option value='NULL'>Select Project Domain</option>";
			for(int i=0;i<grp_domain.size();i++) {
				
				String submitted_domain = (String) grp_domain.get(i).get(0);
				if(project_domain.equals(submitted_domain)) {
					select_domain += "<option value='"+ grp_domain.get(i).get(0) +"' selected>"+ grp_domain.get(i).get(0) +"</option>";
				}else {
					select_domain += "<option value='"+ grp_domain.get(i).get(0) +"'>"+ grp_domain.get(i).get(0) +"</option>";
				}
				
				
			}
			select_domain += "</select>";
			
			sql = "select username, name, email, mobileno from user, student where user.userid = student.userid and student.grpid = " + grpid;
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
	
			String select_guide = "<select name='guide' id='guide' class='form-control' required>";
			select_guide += "<option value='NULL'>Select Guide</option>";
			
			for(int i=0;i<guide_preferences.size();i++) {
				
				String pref = (String)guide_preferences.get(i).get(0);
				
				if(pref.equals(guidename)) {
					select_guide += "<option  value='"+ guide_preferences.get(i).get(1) +"' selected>"+ guide_preferences.get(i).get(0) +"</option>";
				}else {
					select_guide += "<option value='"+ guide_preferences.get(i).get(1) +"'>"+ guide_preferences.get(i).get(0) +"</option>";
				}
			}
			
			select_guide += "</select>";

			String company_name = (String) grpinfo.get(0).get(9);
			String external_guide = (String) grpinfo.get(0).get(10);
			
			if(company_name == null || company_name.equals("null")) {
				company_name = "N/A";
			}
			
			if(external_guide == null || external_guide.equals("null")) {
				external_guide = "N/A";
			}
			
			
			String tid = "select tid from grp_reviewer where grpid = " + grpid;
			List<List<Object>> teacher_id = Crud.find_this_query(tid);
			
			sql = "select name, teacher_capability.tid from teacher, user, teacher_capability where user.userid = teacher.userid and teacher.tid = teacher_capability.tid and teacher_capability.cid = 1004";
			List<List<Object>>reviewer = Crud.find_this_query(sql);
			String select_reviewer1 = "";
			String select_reviewer2 = "";
			if(reviewer.isEmpty()) {
				select_reviewer1 = "Currently no data available";
				select_reviewer2 += select_reviewer1;
			}else {
				
				
				if(teacher_id.isEmpty()) {
					select_reviewer1 += "<select name='review1' class='form-control'>";
					select_reviewer2 += "<select name='review2' class='form-control'>";
					select_reviewer1 += "<option value='NULL'>Select Reviewer</option>";
					select_reviewer2 += "<option value='NULL'>Select Reviewer</option>";
					for(int i=0;i<reviewer.size();i++) {
						select_reviewer1 += "<option value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
						select_reviewer2 += "<option value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
					}
					select_reviewer1 += "</select>";
					select_reviewer2 += "</select>";
					
				}else {
					int tid1 = (int)teacher_id.get(0).get(0);
					int tid2 = (int)teacher_id.get(1).get(0);
					select_reviewer1 += "<select name='review1' class='form-control'>";
					select_reviewer1 += "<option value='NULL'>Select Reviewer</option>";
					select_reviewer2 += "<select name='review2' class='form-control'>";
					select_reviewer2 += "<option value='NULL'>Select Reviewer</option>";
					for(int i=0;i<reviewer.size();i++) {
						if(tid1 == (int)reviewer.get(i).get(1)) {
							select_reviewer1 += "<option selected value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
						}else {
							select_reviewer1 += "<option value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
						}
						
						if(tid2 == (int)reviewer.get(i).get(1)) {
							select_reviewer2 += "<option selected value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
						}else {
							select_reviewer2 += "<option value='"+ reviewer.get(i).get(1) +"'>"+ reviewer.get(i).get(0) +"</option>";
						}
						
					}
					select_reviewer1 += "</select>";
					select_reviewer2 += "</select>";
					
				}
					
			}
			
			Functions functions = new Functions();
			String menu1 = functions.generate_sidebar_menu("", "", "<i class='ni ni-tv-2'></i> Dashboard", "index_coordinator");
			String menu2 = functions.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='fa fa-users text-white'></i> Student groups", "student_group");
			String menu3 = functions.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
			String menu4 = functions.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
			String menu5 = functions.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
			
			request.setAttribute("menu1", menu1);
			request.setAttribute("menu2", menu2);
			request.setAttribute("menu3", menu3);
			request.setAttribute("menu4", menu4);
			request.setAttribute("menu5", menu5);
			
			request.setAttribute("grpid", grpid);
			request.setAttribute("project_title", project_title);
			request.setAttribute("select_domain", select_domain);
			request.setAttribute("group_members", group_members);
			request.setAttribute("pbs_id", pbs_id);
			request.setAttribute("select_guide", select_guide);
			request.setAttribute("company_name", company_name);
			request.setAttribute("external_guide", external_guide);
			request.setAttribute("guidename", guidename);
			request.setAttribute("sponsor_id", sponsor_id);
			request.setAttribute("select_reviewer1", select_reviewer1);
			request.setAttribute("select_reviewer2", select_reviewer2);
			request.getRequestDispatcher("admin/co-ordinator/student_group_single.jsp").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
