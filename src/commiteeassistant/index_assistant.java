package commiteeassistant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;
import models.Domain;

@WebServlet("/index_assistant")
public class index_assistant extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public index_assistant() {
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
			request.setAttribute("userid", userid);
			String name = (String)request.getSession().getAttribute("name");
			request.setAttribute("name", name);
			
			List<List<Object>> domain = new ArrayList<>();
			Domain domain_read = new Domain();
			domain = domain_read.read();
			String output = "";
			
			if(domain.isEmpty() == false) {
				for(int i=0;i<domain.size();i++) {
					output += "<tr class='text-center'>";
					output += "<td>"+ domain.get(i).get(0) +"</td>";
					output += "<td>"+ domain.get(i).get(1) +"</td>";
					output += "<td><a class='text-primary open_domain_modal' data-toggle='modal' data-target='#update_domain' style='cursor: pointer' data-val='"+ domain.get(i).get(1) +"' data-id = '"+ domain.get(i).get(0) +"'>Edit</a> | <a href='delete_domain?did="+ domain.get(i).get(0) +"'>Delete</a></td>";
					output += "</tr>";
				}
			}else {
				output += "Currently no data available";
			}
			
			String sql = "select name, email, mobileno from teacher, user where teacher.userid = user.userid";
			List<List<Object>> result = Crud.find_this_query(sql);
			String output1 = "";
			if(result.isEmpty()) {
				output1 += "Currently no data available";
			}else {
				
				for(int i=0;i<result.size();i++) {
					output1 += "<tr class='text-center'>";
					output1 += "<td>"+ result.get(i).get(0) +"</td>";
					output1 += "<td>"+ result.get(i).get(1) +"</td>";
					output1 += "<td>"+ result.get(i).get(2) +"</td>";
					output1 += "</tr>";
				}
				
			}
			
			request.setAttribute("output", output1);
			request.setAttribute("domain", output);
			request.getRequestDispatcher("admin/commitee-assistant/index.jsp").forward(request, response);;
		}
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
