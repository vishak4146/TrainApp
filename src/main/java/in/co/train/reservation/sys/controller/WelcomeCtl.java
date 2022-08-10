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

@WebServlet(name = "WelcomeCtl", urlPatterns = { "/welcome" })
public class WelcomeCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(WelcomeCtl.class.getName());

	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("WelcomeCtl doGet method start");
				ServletUtility.forward(getView(), request, response);
		log.info("WelcomeCtl doGet method end");

	}

	
	@Override
	protected String getView() {
		return TRSView.WELCOME_VIEW;
	}

}
