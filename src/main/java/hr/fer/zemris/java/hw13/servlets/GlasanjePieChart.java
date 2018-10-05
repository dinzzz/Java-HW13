package hr.fer.zemris.java.hw13.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * This class represents a servlet which creates a pie chart with the results of
 * user's voting for their favorite bands. The servlet takes appropriate files
 * that store band and vote result information and extracts the desired data for
 * the creation of the pie chart. Afterwards, the chart is created and can be
 * fetched to the pages with an appropriate source link.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/glasanje-grafika" })
public class GlasanjePieChart extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7411944468006490150L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileNameResults = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		String fileNameBands = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");

		List<String> results = Files.readAllLines(Paths.get(fileNameResults));
		List<String> bands = Files.readAllLines(Paths.get(fileNameBands));

		List<Integer> resultNumbers = new ArrayList<>();
		List<String> bandNames = new ArrayList<>();

		for (String line : bands) {
			String[] split = line.split("\\t+");
			String bandName = split[1].trim();
			bandNames.add(bandName);
		}

		for (String line : results) {
			String[] split = line.split("\\s+");
			int numberOfVotes = Integer.parseInt(split[1].trim());
			resultNumbers.add(numberOfVotes);
		}

		resp.setContentType("image/png");
		PieDataset dataset = createDataset(bandNames, resultNumbers);
		JFreeChart chart = createChart(dataset, "Favorite bands");
		BufferedImage bim = chart.createBufferedImage(400, 400);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bim, "png", bos);

		resp.getOutputStream().write(bos.toByteArray());
	}

	/**
	 * Creates a dataset for the pie chart from the given band and results lists.
	 * 
	 * @param bands
	 *            List of bands.
	 * @param results
	 *            List of users votes.
	 * @return Appropriate pie dataset.
	 */
	private PieDataset createDataset(List<String> bands, List<Integer> results) {

		DefaultPieDataset result = new DefaultPieDataset();
		for (int i = 0; i < bands.size(); i++) {
			if (results.get(i) != 0) {
				result.setValue(bands.get(i), results.get(i));
			}
		}
		return result;

	}

	/**
	 * Creates a new pie chart from the given dataset and chart title.
	 * 
	 * @param dataset
	 *            Dataset.
	 * @param title
	 *            Title.
	 * @return New pie chart object.
	 */
	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
}
