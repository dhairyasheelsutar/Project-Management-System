package guide;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Crud;

@WebServlet("/review_student")
public class review_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public review_student() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		String user = (String)request.getSession().getAttribute("userid");
		
		if(user == null) {
			response.sendRedirect("index.jsp");
		}else if(user.substring(0, 2).equals("st")) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
	//review1
			String userid = request.getParameter("userid");
			String sql = "select * from review_1 where sid in (select sid from student where userid = '"+ userid +"')";
			List<List<Object>> result = Crud.find_this_query(sql);
			System.out.println(result); 
			int topic = 0;
		     int scope = 0;
		     int survey = 0;
		     int planning = 0;
		     int skills = 0;
		     int qa = 0;
	
			if(result.isEmpty() == false) {
				topic = (int)result.get(0).get(2);
				scope = (int)result.get(0).get(3);
				survey = (int)result.get(0).get(4);
				planning = (int)result.get(0).get(5);
				skills = (int)result.get(0).get(6);
				qa = (int)result.get(0).get(7);
				
			}
	
			request.setAttribute("topic", topic);
			request.setAttribute("scope", scope);
			request.setAttribute("survey", survey);
			request.setAttribute("planning", planning);
			request.setAttribute("skills", skills);
			request.setAttribute("qa", qa);
			
	//review2
			
			String sql2 = "select * from review_2 where sid in (select sid from student where userid = '"+ userid +"')";
			List<List<Object>> result2 = Crud.find_this_query(sql2);
	
		     String survey2 = "0";
		     int design = 0;
		     int features = 0;
		     int planning2 = 0;
		     int basic = 0;
		     int skills2 = 0;
		     int qa2 = 0;
		     String summarize = "0";
	
			if(result2.isEmpty() == false) {
				survey2 = result2.get(0).get(2).toString();
				design = (int)result2.get(0).get(3);
				features = (int)result2.get(0).get(4);
				planning2 = (int)result2.get(0).get(5);
				basic = (int)result2.get(0).get(6);
				skills2 = (int)result2.get(0).get(7);
				qa2 = (int)result2.get(0).get(8);
				summarize = result2.get(0).get(9).toString();
				
			}
	
			request.setAttribute("survey2", survey2);
			request.setAttribute("design", design);
			request.setAttribute("features", features);
			request.setAttribute("planning2", planning2);
			request.setAttribute("basic", basic);
			request.setAttribute("skills2", skills2);
			request.setAttribute("qa2", qa2);
			request.setAttribute("summarize", summarize);
			
	//review3
			
			String sql3 = "select * from review_3 where sid in (select sid from student where userid = '"+ userid +"')";
			List<List<Object>> result3 = Crud.find_this_query(sql3);
			
			System.out.println(result3);
	
		     String design3 = "0";
		     int implementation = 0;
		     int results = 0;
		     int skills3 = 0;
		     int qa3 = 0;
		     String summarize3 = "0";
	
			if(result3.isEmpty() == false) {
				design3 = result3.get(0).get(2).toString();
				implementation = (int)result3.get(0).get(3);
				results = (int)result3.get(0).get(4);
				skills3 = (int)result3.get(0).get(5);
				qa3 = (int)result3.get(0).get(6);
				summarize3 = result3.get(0).get(7).toString();
				
				
			}
	
			
			request.setAttribute("design3", design3);	
			request.setAttribute("implementation", implementation);
			request.setAttribute("results",results);
			request.setAttribute("skills3", skills3);
			request.setAttribute("qa3", qa3);
			request.setAttribute("summarize3", summarize3);
			
	//review4
			
			String sql4 = "select * from review_4 where sid in (select sid from student where userid = '"+ userid +"')";
			List<List<Object>> result4 = Crud.find_this_query(sql4);
		     
		     int implementation4 = 0;
		     int testing = 0;
		     int report = 0;
		     int publications = 0;
		     int skills4 = 0;
		     int qa4 = 0;
		     
	
			if(result4.isEmpty() == false) {
				implementation4 = (int)result4.get(0).get(2);
				testing = (int)result4.get(0).get(3);
				report = (int)result4.get(0).get(4);
				publications = (int)result4.get(0).get(5);
				skills4 = (int)result4.get(0).get(6);
				qa4 = (int)result4.get(0).get(7);
				
				
			}
	
			
			request.setAttribute("implementation4", implementation4);
			request.setAttribute("testing", testing);	
			request.setAttribute("report",report);
			request.setAttribute("publications", publications);
			request.setAttribute("skills4", skills4);
			request.setAttribute("qa4", qa4);
			
			
	
			request.getRequestDispatcher("admin/guide/review_student.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
