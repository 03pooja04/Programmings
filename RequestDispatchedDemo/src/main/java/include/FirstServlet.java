package include;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/firstLink")
public class FirstServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.getWriter().print("<h1> MESSAGE FROM FIRST SERVLET</h1>");
		RequestDispatcher rd=req.getRequestDispatcher("secondServlet");
		rd.include(req, resp);
		
		req.getRequestDispatcher("thirdLink").include(req, resp);
		
	}
	

}
