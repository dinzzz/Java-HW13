package hr.fer.zemris.java.hw13.servlets;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents a servlet responsible for the action of voting for the
 * selected favorite band. This servlet creates a results file if there is no
 * such - This action is set statically for the 17 bands that my survey consist
 * of. When the voting occurs, the servlet fetches the mentioned file, and
 * increments the number of votes of the selected's band ID and then rewrites
 * the file. After the voting, this servlet redirects the user to the results
 * page.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/glasanje-glasaj" })
public class GlasanjeGlasaj extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -8276437641245429771L;

	@Override
	protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String voteId = req.getParameter("id");
		String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		if (!Files.exists(Paths.get(fileName))) {
			FileWriter writer = new FileWriter(fileName);
			int newLineCounter = 0;
			for (int i = 1; i <= 17; i++) {
				writer.write(i + "\t" + "0");
				if (newLineCounter < 16) {
					writer.write("\n");
				}
				newLineCounter++;
			}
			writer.close();
		}

		List<String> lines = Files.readAllLines(Paths.get(fileName));

		String line = lines.get(Integer.parseInt(voteId) - 1);
		int numOfVotes = Integer.parseInt(line.split("\\s+")[1].trim());
		numOfVotes++;
		String newLine = voteId + "\t" + numOfVotes;

		lines.set(Integer.parseInt(voteId) - 1, newLine);

		FileWriter writer = new FileWriter(fileName);
		int newLineCounter = 0;
		for (String str : lines) {
			writer.write(str);
			if (newLineCounter < lines.size() - 1) {
				writer.write("\n");
			}
			newLineCounter++;
		}
		writer.close();

		resp.sendRedirect(req.getContextPath() + "/glasanje-rezultati");

	}

}
