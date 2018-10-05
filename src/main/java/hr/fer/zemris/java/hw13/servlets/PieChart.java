package hr.fer.zemris.java.hw13.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class that represents a servlet which is responsible to create an example of
 * pie chart being showcased in a web application. This servlet loads desired
 * information to the dataset, and constructs a new pie chart which is then
 * presented on a web page that is accessible by getting it's source link.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/reportImage" })
public class PieChart extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6529269490140317073L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("image/png");
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, "Usage of operation systems");
		BufferedImage bim = chart.createBufferedImage(400, 400);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bim, "png", bos);

		resp.getOutputStream().write(bos.toByteArray());
	}

	/**
	 * Creates a sample dataset
	 */
	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Windows", 113);
		result.setValue("Mac", 123);
		result.setValue("Linux", 24);
		result.setValue("Android", 51);
		result.setValue("IOS", 51);
		return result;

	}

	/**
	 * Creates a pie chart from the given dataset and with a given title.
	 * 
	 * @param dataset
	 *            Dataset.
	 * @param title
	 *            Title.
	 * @return Newly created pie chart.
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
