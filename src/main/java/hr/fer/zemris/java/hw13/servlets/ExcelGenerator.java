package hr.fer.zemris.java.hw13.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * This class represnets an excel generator servlet. This servlet will generate
 * the appropriate excel document based on the data it acquired. Then the
 * document will be downloaded to the user's computer.
 * 
 * @author Dinz
 *
 */
@WebServlet(urlPatterns = { "/powers" })
public class ExcelGenerator extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8816468004582216970L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("a");
		String b = req.getParameter("b");
		String n = req.getParameter("n");

		if (!checkParameters(a, b, n)) {
			req.getRequestDispatcher("/WEB-INF/pages/excelInvalid.jsp").forward(req, resp);
			return;
		}

		int numA = Integer.parseInt(a);
		int numB = Integer.parseInt(b);
		int numN = Integer.parseInt(n);

		if (numA > numB) {
			numA = numA ^ numB;
			numB = numA ^ numB;
			numA = numA ^ numB;
		}

		createExcelFile(numA, numB, numN, resp);
	}

	/**
	 * Method which creates a new excel file with numbers of the limits acquired
	 * from the user.
	 * 
	 * @param numA
	 *            Number from where the generation of numbers starts.
	 * @param numB
	 *            Number which marks the end of the number generation.
	 * @param numN
	 *            Number of sheets the document has to produce.
	 * @param resp
	 *            Servlet reposnse.
	 * @throws IOException
	 */
	private void createExcelFile(int numA, int numB, int numN, HttpServletResponse resp) throws IOException {
		HSSFWorkbook hwb = new HSSFWorkbook();

		for (int i = 1; i <= numN; i++) {
			HSSFSheet sheet = hwb.createSheet("Sheet number" + i);
			for (int j = numA, k = 0; j <= numB; j++, k++) {
				HSSFRow row = sheet.createRow(k);
				row.createCell(0).setCellValue(j);
				row.createCell(1).setCellValue(Math.pow(j, i));
			}
		}
		resp.setHeader("Content-Disposition", "attachment; filename=\"tablica.xls\"");
		hwb.write(resp.getOutputStream());
		hwb.close();

	}

	/**
	 * Method which checks if the entered parameters are valid.
	 * 
	 * @param a
	 *            Starting number.
	 * @param b
	 *            Ending number.
	 * @param n
	 *            Number of document sheets.
	 * @return True if the parameters are valid, false otherwise.
	 */
	private boolean checkParameters(String a, String b, String n) {
		if (!isInteger(a) || !isInteger(b) || !isInteger(n)) {
			return false;
		}
		int numA = Integer.parseInt(a);
		int numB = Integer.parseInt(b);
		int numN = Integer.parseInt(n);

		if (numA > 100 || numA < -100 || numB > 100 || numB < -100 || numN > 5 || numN < 1) {
			return false;
		}

		return true;
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
}
