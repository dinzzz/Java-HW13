package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents a funny story servlet. This servlet produces a funny
 * story whose font has a randomly chosen color. The color which randomizer
 * chooses from are statically set in a list.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/funnystory" })
public class FunnyStory extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1963975325100604893L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Random random = new Random();

		List<String> colors = new ArrayList<>() {

			/**
			 * Serial of the color list.
			 */
			private static final long serialVersionUID = 5912426812144598599L;

			{
				add("AliceBlue");
				add("AntiqueWhite");
				add("Aqua");
				add("Azure");
				add("Black");
				add("Brown");
				add("BurlyWood");
				add("Chartreuse");
				add("Cornsilk");
				add("DarkBlue");
				add("DarkCyan");
				add("Chocolate");
				add("DarkOrange");
				add("DarkOrchid");
				add("DarkSalmon");
				add("Magenta");
				add("MediumTurqoise");
				add("Moccasin");
				add("Pink");
				add("Plum");
				add("RebeccaPurple");
				add("SkyBlue");
				add("Tomato");
				add("YellowGreen");
			}
		};

		int randInt = random.nextInt();

		req.getSession().setAttribute("fontColor", colors.get(Math.abs(randInt % colors.size())));
		req.getRequestDispatcher("/WEB-INF/pages/stories/funny.jsp").forward(req, resp);
	}

}
