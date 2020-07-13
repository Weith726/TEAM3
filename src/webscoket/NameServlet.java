package webscoket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NameServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");
		System.out.println(userName);
		HttpSession session = req.getSession();
		session.setAttribute("userName", userName);
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/Puppy/chat.jsp");
//		dispatcher.forward(req, res);
	}
}
