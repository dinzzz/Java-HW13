package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class that represents a servlet which is responsible for executing a
 * calculation for trigonometric operations. This servlet takes two numbers as
 * parameters that represent upper and lower limits of numbers that are used of
 * operation domain. The servlet then calculates the sine and cosine of those
 * numbers, store them into list of view model and redirects them to the .jsp
 * page meant to showcase them to the user.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/trigonometric" })
public class Trigonometry extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2508064716111373558L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("a");
		String b = req.getParameter("b");

		if (!isInteger(a)) {
			a = "0";
		}
		if (!isInteger(b)) {
			b = "360";
		}

		int numA = Integer.parseInt(a);
		int numB = Integer.parseInt(b);

		if (numA > numB) {
			numA = numA ^ numB;
			numB = numA ^ numB;
			numA = numA ^ numB;
		}

		if (numB > numA + 720) {
			numB = numA + 720;
		}

		List<Result> results = new ArrayList<>();
		for (int i = numA; i <= numB; i++) {
			results.add(new Result(i, Math.sin(Math.toRadians(i)), Math.cos(Math.toRadians(i))));
		}

		req.setAttribute("results", results);
		req.getRequestDispatcher("/WEB-INF/pages/trigonometric.jsp").forward(req, resp);

	}

	/**
	 * Method which checks if the given string is an integer.
	 * 
	 * @param s
	 *            String to be checked.
	 * @return True if the string is an integer, false otherwise.
	 */
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	/**
	 * Class that represents a view model of a result that has to be showcased on
	 * the web app page.
	 * 
	 * @author Dinz
	 *
	 */
	public static class Result {

		/**
		 * Domain number used in operation.
		 */
		int number;

		/**
		 * Calculated sine of the number.
		 */
		double sine;

		/**
		 * Calculated cosine of the number.
		 */
		double cosine;

		/**
		 * Constructs a new result view model.
		 * 
		 * @param number
		 *            Domain number.
		 * @param sine
		 *            Sine of the number.
		 * @param cosine
		 *            Cosine of the number.
		 */
		public Result(int number, double sine, double cosine) {
			super();
			this.number = number;
			this.sine = sine;
			this.cosine = cosine;
		}

		/**
		 * Gets the domain number.
		 * 
		 * @return Domain number.
		 */
		public int getNumber() {
			return number;
		}

		/**
		 * Gets the sine of the number.
		 * 
		 * @return Sine of the number.
		 */
		public double getSine() {
			return sine;
		}

		/**
		 * Gets the cosine of the number.
		 * 
		 * @return Cosine of the number.
		 */
		public double getCosine() {
			return cosine;
		}

	}

}
