package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/staff_profile_guide")
public class staff_profile_guide extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public staff_profile_guide() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String userid = (String)request.getSession().getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("index.jsp");
		}else if(userid.substring(0, 2).equals("st")){
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			List<List<Object>> domain = new ArrayList<>();
			String sql = "select tid from teacher where userid = '"+ userid +"'";
			List<List<Object>> result = Crud.find_this_query(sql);
			int tid = (int) result.get(0).get(0);
			
			sql = "select did from teacher_domain where tid = " + tid;
			List<List<Object>> Present_domains = Crud.find_this_query(sql);
			domain = Crud.find_this_query("select * from domain");
			
			String output = "";
			int count = 0;
			
			if(domain.isEmpty()){
				output = "No data available currently";
			}else if(Present_domains.isEmpty() == false) {
			
				output = "<div class='d-flex flex-wrap'>";
				for(int i=0;i<domain.size();i++){
					output += "<div class='mx-2'>";
					
					int domain_list = (int)((List<Object>) domain.get(i)).get(0);
					int present_list;
					if(count <= Present_domains.size() - 1) {
						present_list = (int) ((List<Object>) Present_domains.get(count)).get(0);
					}else {
						present_list = 0;
					}
					
					if(domain_list == present_list) {
						output += "<input type='checkbox' checked name='domains' value='"+ ((List<Object>) domain.get(i)).get(0) +"' />";
						count++;
					}else {
						output += "<input type='checkbox' name='domains' value='"+ ((List<Object>) domain.get(i)).get(0) +"' />";
					}
					
					output += "<label class='mx-1'>"+ ((List<Object>) domain.get(i)).get(1) +"</label>";
					output += "</div>";
					
				}
				output += "</div>";
				
			}else {
				
				output = "<div class='d-flex flex-wrap'>";
				for(int i=0;i<domain.size();i++) {
					output += "<div class='mx-2'>";
					output += "<input type='checkbox' name='domains' value='"+ ((List<Object>) domain.get(i)).get(0) +"' />";
					output += "<label class='mx-1'>"+ ((List<Object>) domain.get(i)).get(1) +"</label>";
					output += "</div>";
				}
				output += "</div>";
			}
			
			request.setAttribute("domain_check", output);
			output = "";
			
			request.setAttribute("subdomain_check", output);
			request.getRequestDispatcher("admin/guide/profile.jsp").forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
