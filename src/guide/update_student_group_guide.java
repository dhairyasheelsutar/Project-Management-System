package guide;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Functions;
import models.Group;
import models.Sponsorship;

@WebServlet("/update_student_group_guide")
public class update_student_group_guide extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public update_student_group_guide() {
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

			String output = "";
			Functions functions = new Functions();
			String groupid = request.getParameter("groupid");
			String pbs_id = request.getParameter("pbs_id");

			List<List<Object>> pbs = Crud.find_this_query("SELECT pbs_id from stdgroup where grpid = " + groupid);

			if(!pbs.get(0).get(0).toString().equals("0") && !pbs.get(0).get(0).toString().equals(pbs_id)){
				output = functions.set_output_message("PBS ID already assigned", false);
				request.setAttribute("output", output);
				request.getRequestDispatcher("student_group_single_guide?grpid=" + groupid).forward(request, response);
			}else{
				String project_title = request.getParameter("project_title");
				String project_domain = request.getParameter("project_domain");
				String guide = request.getParameter("guide");
				String company_name = request.getParameter("company_name");
				String external_guide = request.getParameter("external_guide");


				String sql = "select sponsor_id from stdgroup where grpid = " + groupid;
				List<List<Object>> result = Crud.find_this_query(sql);

				String sponsor_id = ((List<Object>) result.get(0)).get(0).toString();

				sql = "select name from user, teacher where user.userid = teacher.userid and teacher.tid = " + guide;
				List<List<Object>> guideinf = Crud.find_this_query(sql);

				String guidename = ((List<Object>) guideinf.get(0)).get(0).toString();


				Group group = new Group(project_title, project_domain, sponsor_id,  guidename, guide, pbs_id);
				group.grpid = groupid;

				if(group.update()) {
					if(!company_name.equals("") && !external_guide.equals("")) {
						Sponsorship sponsor = new Sponsorship(company_name, external_guide);
						sponsor.sponsor_id = sponsor_id;
						if(sponsor.update()) {
							output = functions.set_output_message("Changes Updated", true);
						}else {
							output = functions.set_output_message("Sponsorship not updated", false);
						}
					}
				}else {
					output = functions.set_output_message("Something went wrong", false);
				}

				String referer = request.getHeader("referer");
				System.out.println(referer);

				request.setAttribute("output", output);
				request.getRequestDispatcher("student_group_single_guide?grpid=" + groupid).forward(request, response);
			}


		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
