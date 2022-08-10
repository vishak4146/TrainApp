package in.co.train.reservation.sys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.train.reservation.sys.bean.BaseBean;
import in.co.train.reservation.sys.bean.UserBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.RecordNotFoundException;
import in.co.train.reservation.sys.model.UserModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.DataValidator;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;




/**
 * Servlet implementation class ForgetPasswordCtl
 */
/**
 * ForgetPassword functionality Controller. Performs operation for Authentication, 
 * and Email send to Get Password
 *
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@WebServlet(name = "ForgetPasswordCtl", urlPatterns = { "/forgetPassword" })
public class ForgetPasswordCtl extends BaseCtl {
private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
       
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ForgetPasswordCtl validate  Method Started");

        boolean pass = true;

        String login = request.getParameter("userName");

        if (DataValidator.isNull(login)) {
            request.setAttribute("userName",
                    PropertyReader.getValue("error.require", "User Name"));
            pass = false;
        } 
        log.debug("ForgetPasswordCtl  validate Method Ended");

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
		 log.debug("ForgetPasswordCtl Method populatebean Started");

	        UserBean bean = new UserBean();

	        bean.setUserName(DataUtility.getString(request.getParameter("login")));

	        log.debug("ForgetPasswordCtl Method populatebean Ended");

	        return bean;
	}

		
	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ForgetPasswordCtl Method doGet Started");

        ServletUtility.forward(getView(), request, response);
	}
	/**
	 * Contains submit logic
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ForgetPasswordCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        UserBean bean = (UserBean) populateBean(request);

        // get model
        UserModel model = new UserModel();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                model.forgetPassword(bean.getUserName());
                
                ServletUtility.setSuccessMessage(
                        "Password has been sent to your email id.", request);
            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage(e.getMessage(), request);
                log.error(e);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
               
            } 
            ServletUtility.forward(getView(), request, response);
        }

        log.debug("ForgetPasswordCtl Method doPost Ended");
	}
	@Override
	protected String getView() {
		 return TRSView.FORGET_PASSWORD_VIEW;
	}

}
