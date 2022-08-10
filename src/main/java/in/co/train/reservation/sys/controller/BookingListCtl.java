package in.co.train.reservation.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.train.reservation.sys.bean.BaseBean;
import in.co.train.reservation.sys.bean.BookingBean;
import in.co.train.reservation.sys.bean.UserBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.model.BookingModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;

/**
 * Servlet implementation class BookingListCtl
 */
@WebServlet(name = "BookingListCtl", urlPatterns = { "/ctl/bookingList" })
public class BookingListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BookingListCtl.class);

	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookingListCtl populateBean method start");
		BookingBean bean = new BookingBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		
		bean.setTrainName(DataUtility.getString(request.getParameter("tName")));
		bean.setInvoiceNo(DataUtility.getLong(request.getParameter("invoice")));
		log.debug("BookingListCtl populateBean method end");
		return bean;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookingListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		BookingModel model = new BookingModel();
		BookingBean bean = (BookingBean) populateBean(request);
		
		long dId=DataUtility.getLong(request.getParameter("bId"));
		
		try {
			
			if(dId>0) {
				BookingBean cBean=new BookingBean();
				cBean.setId(dId);
				model.delete(cBean);
				ServletUtility.setErrorMessage("Booking Cancel Successfully", request);
			}
			
			UserBean ubean=(UserBean)request.getSession().getAttribute("user");
			if(ubean.getRoleId()==2) {
				bean.setName(ubean.getName());
			}
			
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			request.setAttribute("size",model.search(bean).size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("BookingListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookingListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BookingBean bean = (BookingBean) populateBean(request);

		BookingModel model = new BookingModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		}  else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.BOOKING_LIST_CTL, request, response);
			return;

		}

		try {
			
			UserBean ubean=(UserBean)request.getSession().getAttribute("user");
			if(ubean.getRoleId()==2) {
				bean.setName(ubean.getName());
			}

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			request.setAttribute("size",model.search(bean).size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("RoleListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return TRSView.BOOKING_LIST_VIEW;
	}

}
