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
import in.co.train.reservation.sys.bean.TrainBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.model.TrainModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;

/**
 * Servlet implementation class TrainListCtl
 */
@WebServlet(name = "TrainListCtl", urlPatterns = { "/ctl/trainList" })
public class TrainListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(TrainListCtl.class);

	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("TrainListCtl populateBean method start");
		TrainBean bean = new TrainBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		log.debug("TrainListCtl populateBean method end");
		return bean;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TrainModel model = new TrainModel();
		TrainBean bean = (TrainBean) populateBean(request);
		
		long dId=DataUtility.getLong(request.getParameter("dId"));
		
		try {
			
			
			
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
		log.debug("TrainListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TrainBean bean = (TrainBean) populateBean(request);

		TrainModel model = new TrainModel();
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
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.TRAIN_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				TrainBean deletebean = new TrainBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.TRAIN_LIST_CTL, request, response);
			return;

		}

		try {

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
		return TRSView.TRAIN_LIST_VIEW;
	}

}
