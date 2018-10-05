package hr.fer.zemris.java.hw13.servlets;

import java.io.FileWriter;
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
 * This class represents a servlet responsible for showing of voting results on
 * the web app. This servlet extracts the desired information about the band and
 * their vote results and stores them into appropriate view models which are
 * forward to the result .jsp page.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/glasanje-rezultati" })
public class GlasanjeRezultati extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2610438894597902268L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileNameResults = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		String fileNameBands = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");
		
		if (!Files.exists(Paths.get(fileNameResults))) {
			FileWriter writer = new FileWriter(fileNameResults);
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

		List<String> results = Files.readAllLines(Paths.get(fileNameResults));
		List<String> bands = Files.readAllLines(Paths.get(fileNameBands));

		List<BandViewModel> bandsModel = new ArrayList<>();
		List<ResultsViewModel> resultsModel = new ArrayList<>();

		for (String line : bands) {
			String[] split = line.split("\\t+");
			int id = Integer.parseInt(split[0].trim());
			String bandName = split[1].trim();
			String bandSong = split[2].trim();

			bandsModel.add(new BandViewModel(id, bandName, bandSong));
		}

		for (String line : results) {
			String[] split = line.split("\\s+");
			int id = Integer.parseInt(split[0].trim());
			int numberOfVotes = Integer.parseInt(split[1].trim());

			resultsModel.add(new ResultsViewModel(id, numberOfVotes));
		}

		List<FinalViewModel> viewModels = new ArrayList<>();
		List<FinalViewModel> maxViewModels = new ArrayList<>();
		int max = 0;
		for (int i = 0; i < resultsModel.size(); i++) {
			int id = resultsModel.get(i).getId();
			int numOfVotes = resultsModel.get(i).getNumberOfVotes();
			if (numOfVotes > max) {
				max = numOfVotes;
			}
			String bandName = bandsModel.get(i).getBandName();
			String bandSong = bandsModel.get(i).getBandSong();

			viewModels.add(new FinalViewModel(id, numOfVotes, bandName, bandSong));
		}
		for (FinalViewModel model : viewModels) {
			if (model.getNumberOfVotes() == max) {
				maxViewModels.add(model);
			}
		}
		viewModels.sort((s2, s1) -> Integer.compare(s1.numberOfVotes, s2.numberOfVotes));
		req.setAttribute("viewModels", viewModels);
		req.setAttribute("maxViewModels", maxViewModels);

		req.getRequestDispatcher("/WEB-INF/pages/glasanjeRez.jsp").forward(req, resp);
	}

	/**
	 * Class that represents a view model which holds all the necessary information
	 * for the web app page to show.
	 * 
	 * @author Dinz
	 *
	 */
	public static class FinalViewModel {
		/**
		 * Band identification number.
		 */
		int id;

		/**
		 * Number of votes band received.
		 */
		int numberOfVotes;

		/**
		 * Band name.
		 */
		String bandName;

		/**
		 * Youtube link to one of the band's songs.
		 */
		String bandSong;

		/**
		 * Constructs a new view model
		 * 
		 * @param id
		 *            Band identification number.
		 * @param numberOfVotes
		 *            Number of votes band received.
		 * @param bandName
		 *            Band name.
		 * @param bandSong
		 *            Youtube link to one of the band's songs.
		 */
		public FinalViewModel(int id, int numberOfVotes, String bandName, String bandSong) {
			super();
			this.id = id;
			this.numberOfVotes = numberOfVotes;
			this.bandName = bandName;
			this.bandSong = bandSong;
		}

		/**
		 * Gets a band ID.
		 * 
		 * @return Band ID.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the number of votes band received.
		 * 
		 * @return Number of votes band received.
		 */
		public int getNumberOfVotes() {
			return numberOfVotes;
		}

		/**
		 * Gets band name.
		 * 
		 * @return Band name.
		 */
		public String getBandName() {
			return bandName;
		}

		/**
		 * Gets a youtube link to one of the band's songs.
		 * 
		 * @return Youtube link.
		 */
		public String getBandSong() {
			return bandSong;
		}
	}

	/**
	 * Class that represents a view model which shows information from the results
	 * file.
	 * 
	 * @author Dinz
	 *
	 */
	public static class ResultsViewModel {

		/**
		 * Band identification number.
		 */
		int id;

		/**
		 * Number of votes band received.
		 */
		int numberOfVotes;

		/**
		 * Constructs a new result view model.
		 * 
		 * @param id
		 *            Band identification number.
		 * @param numberOfVotes
		 *            Number of votes band received.
		 */
		public ResultsViewModel(int id, int numberOfVotes) {
			super();
			this.id = id;
			this.numberOfVotes = numberOfVotes;
		}

		/**
		 * Gets the band ID.
		 * 
		 * @return Band ID:
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets a number of votes band received.
		 * 
		 * @return Number of votes band received.
		 */
		public int getNumberOfVotes() {
			return numberOfVotes;
		}

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
