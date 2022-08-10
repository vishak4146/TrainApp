package in.co.train.reservation.sys.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.train.reservation.sys.util.ServletUtility;

/**
 * Servlet implementation class WelcomeCtl
 */

@WebServlet(name = "About", urlPatterns = { "/About" })
public class AboutCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(AboutCtl.class.getName());

	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("AboutCtl doGet method start");
				ServletUtility.forward(getView(), request, response);
		log.info("AboutCtl doGet method end");

	}

	
	@Override
	protected String getView() {
		return TRSView.ABOUT_VIEW;
	}

}
