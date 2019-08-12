package coordinator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

import models.Crud;
import models.Functions;

@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public search() {
        super();
    }
    
    private String generate_header_html(int size, String th) {
    	String output = "";
    	output += "<form action='print' method='post'>";
		output += "<br><div class='d-flex flex-wrap justify-content-between px-3'>";
		output += "<p>"+ size +" results found</p>";
		output += "<input type='submit' class='btn btn-success' value='Download' />";
		output += "</div><br>";
		output += "<table class='table table-flush'>";
		output += "<thead>";
		output += "<tr class='text-center'>";
		output += th;
		output += "</tr>";
		output += "</thead>";
		output += "<tbody>";
    	return output;
    }
    
    private String generate_footer_html(String print_data, String filename) {
    	String output = "";
    	output += "</tbody>";
		output += "</table>";
		output += "<input type='hidden' name='activity' value='1' />";
		output += "<input type='hidden' name='filename' value='"+ filename +"' />";
		output += "<textarea style='display: none' name='content'>"+ print_data +"</textarea>";
		output += "</form>";
    	return output;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("activity");
		String output = "";
		String th = "";
		String form = "";
		String query = "";
		Functions functions = new Functions();
		String sql = "";
		List<List<Object>>result = new ArrayList<>();
		List<List<Object>>check = new ArrayList<>();
		
		if(value.equals("null")) {
			output = "<br>";
			output += functions.set_output_message("Please select option and then submit", false);
		}else {
			
			switch(value) {
				case "1":
					sql = "select name, username from user, student where student.userid = user.userid and student.grpid = 0";
					query = "select count(sid) from student";
					result = Crud.find_this_query(sql);
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No students registered</p>";
					}else {
						if(result.isEmpty()) {
							output = "<p class='text-center'>All students have formed group</p>";
						}else {
							
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Roll No\tName\n";
							output += generate_header_html(result.size(), th);
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "student_not_formed_group.xls");
							
						}
					}
					
					
					
					break;
				
				case "2":
					query = "select count(grpid) from stdgroup";
					check = Crud.find_this_query(query);
					if(check.isEmpty()) {
						output = "<p class='text-center'>No groups formed yet</p>";
					}else {
						sql = "{call check_submit_domain()}";
						result = Crud.execute_procedures(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All groups have submitted project details</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "group_not_submitted_domain.xls");
						}
					}
					
					
					
					
					
					break;
					
				case "3":
					query = "select count(grpid) from stdgroup";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No groups formed yet</p>";
					}else {
						sql = "{call check_submit_guide_preferences()}";
						result = Crud.execute_procedures(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All groups have submitted guide preferences</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "group_not_submitted_guide_preferences.xls");
						}
					}
					break;
					
				case "4":
					query = "select count(grpid) from stdgroup";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No groups formed yet</p>";
					}else {
						sql = "{call check_submit_synopsis()}";
						result = Crud.execute_procedures(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All groups have submitted guide preferences</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "group_not_submitted_synopsis.xls");
						}
					}
					break;
					
				case "5":
					query = "select count(sid) from student";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No student registered yet.</p>";
					}else {
						sql = "select grpid, username, name from student, user, review_1 where student.userid = user.userid and student.sid = review_1.sid and review_1.total = 0 and grpid <> 0 union select grpid, name, username from user, student where student.userid = user.userid and student.grpid = 0";
						result = Crud.find_this_query(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All students appeared for Review-I</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "student_not_reviewed_I.xls");
						}
					}
					break;
					
				case "6":
					query = "select count(sid) from student";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No student registered yet.</p>";
					}else {
						sql = "select grpid, username, name from student, user, review_2 where student.userid = user.userid and student.sid = review_2.sid and review_2.total = 0 and grpid <> 0 union select grpid, username, name from user, student where student.userid = user.userid and student.grpid = 0";
						result = Crud.find_this_query(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All students appeared for Review-II</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "student_not_reviewed_II.xls");
						}
					}
					break;
					
				case "7":
					query = "select count(sid) from student";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No student registered yet.</p>";
					}else {
						sql = "select grpid, username, name from student, user, review_3 where student.userid = user.userid and student.sid = review_3.sid and review_3.total = 0 and grpid <> 0 union select grpid, username, name from user, student where student.userid = user.userid and student.grpid = 0";
						result = Crud.find_this_query(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All students appeared for Review-III</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "student_not_reviewed_III.xls");
						}
					}
					break;
					
				case "8":
					query = "select count(sid) from student";
					check = Crud.find_this_query(query);
					
					if(check.isEmpty()) {
						output = "<p class='text-center'>No student registered yet.</p>";
					}else {
						sql = "select grpid, username, name from student, user, review_4 where student.userid = user.userid and student.sid = review_4.sid and review_4.total = 0 and grpid <> 0";
						result = Crud.find_this_query(sql);
						if(result.isEmpty()) {
							output = "<p class='text-center'>All students appeared for Review-IV</p>";
						}else {
							th += "<th>Group ID</th>";
							th += "<th>Roll No</th>";
							th += "<th>Name</th>";
							
							form += "Group ID\tRoll No\tName\n";
							output += generate_header_html(result.size(), th);
							
							for(int i=0;i<result.size();i++) {
								output += "<tr class='text-center'>";
								output += "<td>"+ result.get(i).get(0) +"</td>";
								form += result.get(i).get(0) + "\t";
								output += "<td>"+ result.get(i).get(1) +"</td>";
								form += result.get(i).get(1) + "\t";
								output += "<td>"+ result.get(i).get(2) +"</td>";
								form += result.get(i).get(2) + "\n";
								output += "</tr>";
							}
							output += generate_footer_html(form, "student_not_reviewed_IV.xls");
						}
					}
					break;
			}
				
		}
		
		sql = "select count(sid) from student";
		System.out.println(sql);
		List<List<Object>>student = Crud.find_this_query(sql);
		
		System.out.println(student);
		
		sql = "select count(tid) from teacher";
		List<List<Object>>teacher = Crud.find_this_query(sql);
		
		System.out.println(teacher);
		
		sql = "select count(grpid) from stdgroup";
		List<List<Object>> stdgroup = Crud.find_this_query(sql);
		
		sql = "select count(did) from domain";
		List<List<Object>> domain = Crud.find_this_query(sql);
		
		Functions functions1 = new Functions();
		String menu1 = functions1.generate_sidebar_menu("btn btn-sm btn-warning", "text-white", "<i class='ni ni-tv-2 text-white'></i> Dashboard", "index_coordinator");
		String menu2 = functions1.generate_sidebar_menu("", "", "<i class='fa fa-users text-blue'></i> Student groups", "student_group");
		String menu3 = functions1.generate_sidebar_menu("", "", "<i class='fa fa-clock-o text-orange'></i> Schedule", "schedule");
		String menu4 = functions1.generate_sidebar_menu("", "", "<i class='fa fa-list text-success'></i> Staff list", "staff_list");
		String menu5 = functions1.generate_sidebar_menu("", "", "<i class='fa fa-address-book text-danger'></i> Students list", "student_list");
		
		request.setAttribute("menu1", menu1);
		request.setAttribute("menu2", menu2);
		request.setAttribute("menu3", menu3);
		request.setAttribute("menu4", menu4);
		request.setAttribute("menu5", menu5);
		request.setAttribute("student", student.get(0).get(0));
		request.setAttribute("staff", teacher.get(0).get(0));
		request.setAttribute("group", stdgroup.get(0).get(0));
		request.setAttribute("domain", domain.get(0).get(0));
		request.setAttribute("output", output);
		request.getRequestDispatcher("admin/co-ordinator/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
