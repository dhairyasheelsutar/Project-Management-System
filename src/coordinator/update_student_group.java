package coordinator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Database;
import models.Functions;
import models.Sponsorship;

@WebServlet("/update_student_group")
public class update_student_group extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public update_student_group() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			String output = "";
			Functions functions = new Functions();
			String groupid = request.getParameter("groupid");
			String pbs_id = request.getParameter("pbs_id");

			List<List<Object>> pbs = Crud.find_this_query("SELECT pbs_id from stdgroup where grpid = " + groupid);
			if (!pbs.get(0).get(0).toString().equals("0") && !pbs.get(0).get(0).toString().equals(pbs_id)){
				output = functions.set_output_message("PBS ID already assigned", false);
				request.setAttribute("output", output);
				request.getRequestDispatcher("student_group_single?grpid=" + groupid).forward(request, response);
			}else{
				String project_title = request.getParameter("project_title");
				String project_domain = request.getParameter("project_domain");
				String guide = request.getParameter("guide");
				String company_name = request.getParameter("company_name");
				String external_guide = request.getParameter("external_guide");

				String review1 = request.getParameter("review1");
				String review2 = request.getParameter("review2");

				String sponsor_id = request.getParameter("sponsor_id");
				Database database = new Database();


				Sponsorship sponsor = new Sponsorship(company_name, external_guide);
				sponsor.sponsor_id = sponsor_id;
				sponsor.update();
				String sql = "";
				String guidename = "";
				if(guide.equals("NULL") == false) {
					sql = "select name from teacher, user where teacher.userid = user.userid and teacher.tid = " + guide;
					List<List<Object>> rs = Crud.find_this_query(sql);
					guidename = rs.get(0).get(0).toString();
				}else {
					guidename = "N/A";
				}

				sql = "update stdgroup set project_title = '"+ project_title +"', project_domain = '"+ project_domain +"', guidename = '"+ guidename +"', guideid = "+ guide +", pbs_id = "+ pbs_id +" where grpid = " + groupid;

				if(database.dml(sql) != -1) {
					sql = "delete from grp_reviewer where grpid = " + groupid;
					database.dml(sql);

					if(!review1.equals("NULL") && !review2.equals("NULL")) {
						sql = "insert into grp_reviewer (tid, grpid) values ("+ review1 +", "+ groupid +"), ("+ review2 +", "+ groupid +")";
						database.dml(sql);
					}
					output = functions.set_output_message("Changes saved successfully", true);

				}else {
					output = functions.set_output_message("Something went wrong", false);
				}

				request.setAttribute("output", output);
				request.getRequestDispatcher("student_group_single?grpid=" + groupid).forward(request, response);

			}


		}
		
		
	}

}
