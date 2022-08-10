package in.co.train.reservation.sys.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class UserRegistrationCtl
 */

/**
 * UserRegistration functionality Controller. Performs operation for Validate
 * and add a User As Student Role
 */

@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/registration" })
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class.getName());

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.info("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("userName");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "UserName"));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", "Password"));
			return false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", "Password"));
			return false;
		}

		if (DataValidator.isNull(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.require", "Contact No"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("contactNo"))) {
			request.setAttribute("contactNo", PropertyReader.getValue("error.invalid", "Contact No"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "Email Id"));
			pass = false;
		}
		log.info("UserRegistrationCtl Method validate Ended");
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
		log.info("UserRegistrationCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(2L);
		bean.setRoleName("User");

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setUserName(DataUtility.getString(request.getParameter("userName")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));


		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setContactNo(DataUtility.getString(request.getParameter("contactNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		populateDTO(bean, request);

		log.info("UserRegistrationCtl Method populatebean Ended");

		return bean;
	}

	private static final long serialVersionUID = 1L;

	public UserRegistrationCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Contains submit logic
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in post method");
		log.info("UserRegistrationCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		UserModel model = new UserModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);
			try {
				long pk = model.registerUser(bean);
				bean.setId(pk);
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.setSuccessMessage("User Successfully Registered", request);
				ServletUtility.forward(TRSView.USER_REGISTRATION_VIEW, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.USER_REGISTRATION_CTL, request, response);
			return;
		}
		log.info("UserRegistrationCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return TRSView.USER_REGISTRATION_VIEW;
	}

}
