package in.co.train.reservation.sys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.train.reservation.sys.bean.BaseBean;
import in.co.train.reservation.sys.bean.UserBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.model.UserModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.DataValidator;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;



/**
 * Servlet implementation class MyProfileCtl
 */

/**
 * MyProfile functionality Controller. Performs operation for Add, update
 * operations of MyProfile
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
@WebServlet(name = "MyProfileCtl", urlPatterns = { "/ctl/myProfile" })
public class MyProfileCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	private static Logger log = Logger.getLogger(MyProfileCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {
			return pass;
		}

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "User Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", " Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", " Email"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "DOB"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "ContactNo"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.invalid", "ContactNo"));
			pass = false;
		}

		log.debug("MyProfileCtl Method validate Ended");
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MyProfileCtl Method PopulateBean Started ");
		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));

		populateDTO(bean, request);

		log.debug("MyProfileCtl Method PopulateBean End ");
		return bean;
	}

	/**
	 * Display Concept for viewing profile page view
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyProfileCTl Method doGet Started");

		HttpSession session = request.getSession(true);

		UserBean userBean = (UserBean) session.getAttribute("user");

		long id = userBean.getId();

		String op = DataUtility.getString(request.getParameter("operation"));
		// get Model

		UserModel model = new UserModel();

		if (id > 0 || op != null) {
			System.out.println("in id>0 condition");
			UserBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MyProfileCtl Method doGet Ended");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyprofileCtl Method doPost Started");

		HttpSession session = request.getSession(true);
		UserBean userBean = (UserBean) session.getAttribute("user");
		long id = userBean.getId();
		String op = DataUtility.getString(request.getParameter("operation"));
		UserModel model = new UserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				if (id > 0) {
					userBean.setName(bean.getName());
					userBean.setEmail(bean.getEmail());
					userBean.setContactNo(bean.getContactNo());
					userBean.setUserName(bean.getUserName());
					userBean.setDob(bean.getDob());
					model.update(userBean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.CHANGE_PASSWORD_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MyProfileCtl doPost method end");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return TRSView.MY_PROFILE_VIEW;
	}

}
