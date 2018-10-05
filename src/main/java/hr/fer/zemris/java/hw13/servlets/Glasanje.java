package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class represents a voting servlet. This servlet showcases the user a
 * list of the bends to choose from and when the favorite band is selected, the
 * user is redirected to the page with voting results.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/glasanje" })
public class Glasanje extends HttpServlet {

	/**
	 * Serial for the voting servlet.
	 */
	private static final long serialVersionUID = 3371767972485888786L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		List<BandViewModel> bands = new ArrayList<>();

		for (String line : lines) {
			String[] split = line.split("\\t+");
			int id = Integer.parseInt(split[0].trim());
			String bandName = split[1].trim();
			String bandSong = split[2].trim();

			bands.add(new BandViewModel(id, bandName, bandSong));

		}

		req.setAttribute("bands", bands);
		req.getRequestDispatcher("/WEB-INF/pages/glasanjeIndex.jsp").forward(req, resp);
	}

	/**
	 * Class that represents a band view model. This model stores the data that
	 * describes one band which is then showcased on the web app.
	 * 
	 * @author Dinz
	 *
	 */
	public static class BandViewModel {

		/**
		 * Band identification number.
		 */
		int id;

		/**
		 * Band name
		 */
		String bandName;

		/**
		 * Youtube link to one of the band's songs.
		 */
		String bandSong;

		/**
		 * Constructs a new band view model
		 * 
		 * @param id
		 *            Band identification number.
		 * @param bandName
		 *            Band name.
		 * @param bandSong
		 *            Youtube link to one of the band's songs.
		 */
		public BandViewModel(int id, String bandName, String bandSong) {
			super();
			this.id = id;
			this.bandName = bandName;
			this.bandSong = bandSong;
		}

		/**
		 * Gets the band id.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the band name.
		 * 
		 * @return
		 */
		public String getBandName() {
			return bandName;
		}

		/**
		 * Gets the youtube link to one of the band's songs.
		 * 
		 * @return Youtube link in a string format.
		 */
		public String getBandSong() {
			return bandSong;
		}

	}

}
