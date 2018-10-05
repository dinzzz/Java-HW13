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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This class represents a servlet responsible for creating an excel file with
 * band voting results. This servlet fetches appropriate files and extracts the
 * bands names and number of votes for each band in the survey. Then, it fills
 * the excel files with each row representing a band and the number of votes it
 * got.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/glasanje-xls" })
public class GlasanjeExcel extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -407911594728171999L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileNameResults = req.getServletContext().getRealPath("/WEB-INF/glasanje-rezultati.txt");
		String fileNameBands = req.getServletContext().getRealPath("/WEB-INF/glasanje-definicija.txt");

		List<String> results = Files.readAllLines(Paths.get(fileNameResults));
		List<String> bands = Files.readAllLines(Paths.get(fileNameBands));

		List<String> resultNumbers = new ArrayList<>();
		List<String> bandNames = new ArrayList<>();

		for (String line : bands) {
			String[] split = line.split("\\t+");
			String bandName = split[1].trim();
			bandNames.add(bandName);
		}

		for (String line : results) {
			String[] split = line.split("\\s+");
			String numberOfVotes = split[1].trim();
			resultNumbers.add(numberOfVotes);
		}

		createExcelFile(bandNames, resultNumbers, resp);

	}

	/**
	 * Method that creates an appropriate excel file.
	 * 
	 * @param bands
	 *            List of bands.
	 * @param results
	 *            Results of the voting.
	 * @param resp
	 *            Servlet response.
	 * @throws IOException
	 */
	private void createExcelFile(List<String> bands, List<String> results, HttpServletResponse resp)
			throws IOException {
		HSSFWorkbook hwb = new HSSFWorkbook();

		HSSFSheet sheet = hwb.createSheet("Vote results");
		for (int i = 0; i < bands.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(bands.get(i));
			row.createCell(1).setCellValue(results.get(i));
		}
		resp.setHeader("Content-Disposition", "attachment; filename=\"results.xls\"");
		hwb.write(resp.getOutputStream());
		hwb.close();

	}

}
