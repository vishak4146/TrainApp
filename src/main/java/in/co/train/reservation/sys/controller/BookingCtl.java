package in.co.train.reservation.sys.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.train.reservation.sys.bean.BaseBean;
import in.co.train.reservation.sys.bean.BookingBean;
import in.co.train.reservation.sys.bean.TrainBean;
import in.co.train.reservation.sys.bean.UserBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DatabaseException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.model.BookingModel;
import in.co.train.reservation.sys.model.TrainModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.DataValidator;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;

/**
 * Servlet implementation class BookingCtl
 */
@WebServlet(name = "BookingCtl", urlPatterns = { "/ctl/booking" })
public class BookingCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BookingCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BookingCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("adult"))) {
			request.setAttribute("adult", PropertyReader.getValue("error.require", "Adult"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("child"))) {
			request.setAttribute("child", PropertyReader.getValue("error.require", "Chiild"));
			pass = false;
		}

		log.debug("BookingCtl validate method end");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookingCtl populateBean method start");
		BookingBean bean = new BookingBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAdult(DataUtility.getInt(request.getParameter("adult")));
		bean.setChild(DataUtility.getInt(request.getParameter("child")));
		populateDTO(bean, request);
		log.debug("BookingCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BookingCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		BookingModel model = new BookingModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		long tId = DataUtility.getLong(request.getParameter("tId"));

		if (tId > 0) {
			request.getSession().setAttribute("tId", tId);
		} else {
			ServletUtility.redirect(TRSView.SEARCH_TRAIN_CTL, request, response);
		}

		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BookingBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("BookingCtl doGet method end");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BookingCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		BookingModel model = new BookingModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		HttpSession session = request.getSession();
		TrainModel trainModel = new TrainModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			BookingBean bean = (BookingBean) populateBean(request);
			try {
				UserBean uBean = (UserBean) session.getAttribute("user");
				long tId = DataUtility.getLong(String.valueOf(session.getAttribute("tId")));
				TrainBean tBean = trainModel.findByPK(tId);
				bean.setTrainId(tId);
				bean.setTrainName(tBean.getName());
				bean.setTravelDate(tBean.getDate());
				bean.setBookingDate(new Date());
				bean.setTotalMember(bean.getAdult() + bean.getChild());
				long seat = DataUtility.getLong(tBean.getMaxSeat()) - model.countMember(tBean.getId());
				System.out.println("Total Availble Seat-----------------:"+seat);
				if (seat < 0) {
					ServletUtility.setErrorMessage("Seat is Not Available", request);
					ServletUtility.forward(getView(), request, response);
					return;
				} else {
					if (seat < bean.getTotalMember()) {
						ServletUtility.setErrorMessage("Seat is Not Available", request);
						ServletUtility.forward(getView(), request, response);
						return;
					}
				}
				bean.setTicketPrice(tBean.getRate());
				bean.setTotalAmount(String.valueOf(DataUtility.getLong(tBean.getRate()) * bean.getAdult()));
				bean.setStatus("Booked");
				bean.setName(uBean.getName());
				bean.setInvoiceNo(model.nextInvoiceNo());
				session.setAttribute("booking", bean);
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Booking Successfully Done.", request);
				ServletUtility.forward(TRSView.SUCCESS_VIEW, request, response);

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(TRSView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.SEARCH_TRAIN_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.BOOKING_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("Booking doPost method end");
	}

	@Override
	protected String getView() {
		return TRSView.BOOKING_VIEW;
	}

}
