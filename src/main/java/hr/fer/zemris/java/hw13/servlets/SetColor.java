package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class that represents a servlet which is responsible for setting the
 * background color of the application. This servlet sets a desired background
 * color as a session attribute and dispatches it to appropriate .jsp page. The
 * default color is white.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/setcolor" })
public class SetColor extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -315655868494166346L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String colorToSet = req.getParameter("color");

		req.getSession().setAttribute("pickedBgColor", colorToSet);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		;

	}

}
