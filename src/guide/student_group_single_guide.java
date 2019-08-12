package guide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;

@WebServlet("/student_group_single_guide")
public class student_group_single_guide extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public student_group_single_guide() {
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
			
			String project_title = (String) ((List<Object>) grpinfo.get(0)).get(1);
			String pbs_id = ((List<Object>) grpinfo.get(0)).get(7).toString();
			String project_domain = (String) ((List<Object>) grpinfo.get(0)).get(2);
			String guidename = (String) ((List<Object>) grpinfo.get(0)).get(4);
			sql = "select dname, domain.did from group_domain, domain where group_domain.did = domain.did and group_domain.grpid = " + grpid;
			List<List<Object>> grp_domain = Crud.find_this_query(sql);
			
			String select_domain = "<select name='project_domain'  class='form-control' required>";
			for(int i=0;i<grp_domain.size();i++) {
				
				String submitted_domain = (String) ((List<Object>) grp_domain.get(i)).get(0);
				if(project_domain.equals(submitted_domain)) {
					select_domain += "<option value='"+ ((List<Object>) grp_domain.get(i)).get(0) +"' selected>"+ ((List<Object>) grp_domain.get(i)).get(0) +"</option>";
				}else {
					select_domain += "<option value='"+ ((List<Object>) grp_domain.get(i)).get(0) +"'>"+ ((List<Object>) grp_domain.get(i)).get(0) +"</option>";
				}
				
				
			}
			select_domain += "</select>";
			
			sql = "select username, name, email, mobileno, sid from user, student where user.userid = student.userid and student.grpid = " + grpid;
			List<List<Object>>student_info = Crud.find_this_query(sql);
			
			String group_members = "";
			
			int participants = student_info.size();
			for(int i=0;i<student_info.size();i++) {
				group_members += "<tr>";
				group_members += "<td>"+ student_info.get(i).get(0) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(1) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(2) +"</td>";
				group_members += "<td>"+ student_info.get(i).get(3) +"</td>";
				group_members += "</tr>";
			}
			
			sql = "select tid from teacher where userid = '"+ userid +"'";
			List<List<Object>>rs1 = Crud.find_this_query(sql);
			
			int guide = (int)rs1.get(0).get(0);
			
		
			
			String company_name = (String) ((List<Object>) grpinfo.get(0)).get(9);
			String external_guide = (String) ((List<Object>) grpinfo.get(0)).get(10);
			
			if(company_name == null || company_name.equals("null")) {
				company_name = "N/A";
			}
			
			if(external_guide == null || external_guide.equals("null")) {
				external_guide = "N/A";
			}
			
			sql = "select * from abstract where grpid = "+ grpid +" limit 1";
			List<List<Object>>rs = Crud.find_this_query(sql);
			
			String abstract_project = "";
			String modal_footer = "";
			if(rs.isEmpty()) {
				abstract_project = "Abstract not submitted";
				
			}else {
				abstract_project += "<input type='hidden' value='"+ rs.get(0).get(1) +"' name='grpid'/>";
				abstract_project += "<p class='lead'>Background</p>";
				abstract_project += rs.get(0).get(2);
				abstract_project += "<p class='lead'>Purpose</p>";
				abstract_project += rs.get(0).get(3);
				abstract_project += "<p class='lead'>Method</p>";
				abstract_project += rs.get(0).get(4);
				abstract_project += "<p class='lead'>Result</p>";
				abstract_project += rs.get(0).get(5);
				abstract_project += "<p class='lead'>Conclusion</p>";
				abstract_project += rs.get(0).get(6);
				modal_footer = "<input type='submit' name='reject' class='btn btn-danger' value='reject' />";
			}
			
			sql = "select logdate, log_staff from grplog where grpid = " + grpid;
			List<List<Object>> grplog = new ArrayList<>();
			grplog = Crud.find_this_query(sql);
			
			String group_logs = "";
			if(grplog.isEmpty() == false) {
				group_logs += "<div class='table-responsive'>";
				group_logs += "<table class='table table-flush'>";
				group_logs += "<thead>";
				group_logs += "<tr>";
				group_logs += "<th>Date</td>";
				group_logs += "<th>Activity log</td>";
				group_logs += "<tr>";
				group_logs += "</thead>";
				group_logs += "<tbody>";
				for(int i=0;i<grplog.size();i++) {
					List<Object> grp = (List<Object>)grplog.get(i);
					
					group_logs += "<tr>";
					group_logs += "<td>"+ grp.get(0) +"</td>";
					group_logs += "<td><i>"+ grp.get(1) +"</i></td>";
					group_logs += "</tr>";
				}
				
				group_logs += "</tbody>";
				group_logs += "</table>";
				group_logs += "</div>";
				
			}else {
				group_logs = "Currently there are no group logs";
			}
		
			
			//defines table head dynamically depending upon 3-4 members in group
			String tbl_head = "";
			
			////defines input type dynamically depending upon 3-4 members in group
			String input_type = "";
			
			if(participants == 3) {
				
				//if 3 members then set 3 input types to get 3 roll numbers. Also colspan must be 3 
				
				tbl_head += "<tr class='text-center'>\r\n" + 
						"<th rowspan='3'>Particulars</th>\r\n" + 
						"<th colspan='4'>Marks(25M)</th>\r\n" + 
						"</tr> \r\n" + 
						"<tr>\r\n" + 
						"<th>"+student_info.get(0).get(0)+"</th>\r\n" + 
						"<th>"+student_info.get(1).get(0)+"</th>\r\n" + 
						"<th>"+student_info.get(2).get(0)+"</th>\r\n" + 
						"</tr>";
				
				input_type += "<input type='hidden' name='grpid' value='"+ grpid +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(0).get(4) +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(1).get(4) +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(2).get(4) +"' >";
				
			}else if(participants == 4) {
				
				//if 4 members then set 4 input types to get 4 roll numbers. Also colspan must be 4
				
				tbl_head += "<tr class='text-center'>\r\n" + 
						"<th rowspan='3'>Particulars</th>\r\n" + 
						"<th colspan='4'>Marks(25M)</th>\r\n" + 
						"</tr> \r\n" + 
						"<tr>\r\n" + 
						"<th>"+student_info.get(0).get(0)+"</th>\r\n" + 
						"<th>"+student_info.get(1).get(0)+"</th>\r\n" + 
						"<th>"+student_info.get(2).get(0)+"</th>\r\n" + 
						"<th>"+student_info.get(3).get(0)+"</th>\r\n" + 
						"</tr>";
				
				input_type += "<input type='hidden' name='grpid' value='"+ grpid +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(0).get(4) +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(1).get(4) +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(2).get(4) +"' >";
				input_type += "<input type='hidden' name='roll' value='"+ student_info.get(3).get(4) +"' >";
				
			}
			
			//get current values in database for review-I(initially all will 0)
			
			Functions functions = new Functions();
			
			sql = "select * from review_1 where sid in (select sid from student where grpid = "+ grpid +")";
			List<List<Object>>review1 = Crud.find_this_query(sql);
			String[] review1_val = {"background1", "scope1", "survey1", "planning1", "skills1", "qa1", "total1"};
			String[] review1_attr = {"Background and Topic (4 M)", "Project Scope and Objectives (4 M)", "Literature Survey (5 M)", "Project planning(4 M)", "Presentation Skills (4 M)", "Question and Answer (4 M)", "<strong>Total</strong>"};
			String review1_tbl = functions.generate_review_html(participants, review1_attr, review1_val, review1);
			
			
			sql = "select * from review_2 where sid in (select sid from student where grpid = "+ grpid +")";
			List<List<Object>>review2 = Crud.find_this_query(sql);
			String[] review2_val = {"survey2", "design2", "features2", "planning2", "basic2", "skills2", "qa2", "summarize2", "total2"};
			String[] review2_attr = {"System Architecture & Literature Survey (Review-I)", "Project Design (5 M)", "Methodology /Algorithms and Project Features (5 M)", "Project planning(2 M)", "Basic details of Implementation (5 M)", "Presentation Skills ( 4 M)", "Question and Answer (4 M)", "Summarization of ultimate findings of the Project", "<strong>Total</strong>"};
			String review2_tbl = functions.generate_review_html(participants, review2_attr, review2_val, review2);
			
			sql = "select * from review_3 where sid in (select sid from student where grpid = "+ grpid +")";
			List<List<Object>>review3 = Crud.find_this_query(sql);
			String[] review3_val = {"design3", "implemention3", "results3", "skills3", "qa3", "summarize3", "total3"};
			String[] review3_attr = {"Architecture / System Design -(if any modification)", "50 % Implementation (10 M)", "Partial results obtained ( 7 M)", "Presentation skills (4 M)", "Question and Answer ( 4 M)", "Summarize the methodologies / Algorithms <br> implemented / to be implemented", "<strong>Total</strong>"};
			String review3_tbl = functions.generate_review_html(participants, review3_attr, review3_val, review3);
			
			sql = "select * from review_4 where sid in (select sid from student where grpid = "+ grpid +")";
			List<List<Object>>review4 = Crud.find_this_query(sql);
			String[] review4_val = {"implemention4", "testing4", "report4", "publications4", "skills4", "qa4", "total4"};
			String[] review4_attr = {"Implementation (100%) (5 M)", "Testing, Results and Performance Evaluation ( 5 M)", "Final Project Report ( 5 M)", "Publications ( 2 M)", "Presentation skills ( 4 M)", "Question and Answer (4 M)", "<strong>Total</strong>"};
			String review4_tbl = functions.generate_review_html(participants, review4_attr, review4_val, review4);
			
			sql = "select question, type, grpid"+grpid+" from checklist";
			String[] values = {"Y", "N", "NA", "NC"};
			List<List<Object>> checklist = Crud.find_this_query(sql);
			String checklist1 = functions.generate_arraylist_html(0, 16, 1, checklist, values, true);
			String checklist2 = functions.generate_arraylist_html(16, 32, 2, checklist, values, true);
			String checklist3 = functions.generate_arraylist_html(32, 39, 3, checklist, values, true);
			String checklist4 = functions.generate_arraylist_html(39, 44, 4, checklist, values, false);
			
			checklist4 += "<div class='px-3 py-2' style='border-top: 1px solid #d3d3d3'>";
			checklist4 += "<span style='font-size: 14px; text-align: justify'>44) Implementation status ( code completion in percentage)?</span>";
			checklist4 += "<input type='text' class='form-control' name='check1list44' value='"+ checklist.get(44).get(2) +"' />";
			checklist4 += "</div>";
			checklist4 += "<div class='px-3 py-2' style='border-top: 1px solid #d3d3d3'>";
			checklist4 += "<span style='font-size: 14px; text-align: justify'>45) Final thesis status( in percentage)?</span>";
			checklist4 += "<input type='text' class='form-control' name='check1list45' value='"+ checklist.get(45).get(2) +"' />";
			checklist4 += "</div>";
			checklist4 += "<div class='mt-3 text-center'><input type='submit' name='submit' value='submit' class='btn btn-warning' /></div>";
			
			request.setAttribute("checklist1", checklist1);
			request.setAttribute("checklist2", checklist2);
			request.setAttribute("checklist3", checklist3);
			request.setAttribute("checklist4", checklist4);
			request.setAttribute("review1_tbl", review1_tbl);
			request.setAttribute("review2_tbl", review2_tbl);
			request.setAttribute("review3_tbl", review3_tbl);
			request.setAttribute("review4_tbl", review4_tbl);
			request.setAttribute("group_logs", group_logs);
			request.setAttribute("tbl_head", tbl_head);
			request.setAttribute("input_types", input_type);
			request.setAttribute("student_info", student_info);
			request.setAttribute("grpid", grpid);
			request.setAttribute("pbs_id", pbs_id);
			request.setAttribute("project_title", project_title);
			request.setAttribute("select_domain", select_domain);
			request.setAttribute("group_members", group_members);
			request.setAttribute("guide", guide);
			request.setAttribute("company_name", company_name);
			request.setAttribute("external_guide", external_guide);
			request.setAttribute("guidename", guidename);
			request.setAttribute("abstract_project", abstract_project);
			request.setAttribute("modal_footer", modal_footer);
			
			request.getRequestDispatcher("admin/guide/student_group_single.jsp").forward(request, response);
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
