package commiteemember;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index_member")
public class index_member extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public index_member() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = (String)request.getSession().getAttribute("userid");
		request.setAttribute("userid", userid);
		String name = (String)request.getSession().getAttribute("name");
		request.setAttribute("name", name);
		request.getRequestDispatcher("admin/commitee-member/index.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
