package in.co.train.reservation.sys.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.train.reservation.sys.bean.BaseBean;
import in.co.train.reservation.sys.bean.TrainBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.model.TrainModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.DataValidator;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;


/**
 * Servlet implementation class TrainCtl
 */
@WebServlet(name="TrainCtl",urlPatterns={"/ctl/train"})
public class TrainCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(TrainCtl.class);

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("TrainCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("trainNo"))) {
            request.setAttribute("trainNo",
                    PropertyReader.getValue("error.require", "Train No"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("rate"))) {
            request.setAttribute("rate",
                    PropertyReader.getValue("error.require", "Rate"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("date"))) {
            request.setAttribute("date",
                    PropertyReader.getValue("error.require", "Date"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("from"))) {
            request.setAttribute("from",
                    PropertyReader.getValue("error.require", "From"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("to"))) {
            request.setAttribute("to",
                    PropertyReader.getValue("error.require", "To"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("maxSeat"))) {
            request.setAttribute("maxSeat",
                    PropertyReader.getValue("error.require", "Max Seat"));
            pass = false;
        }

        log.debug("TrainCtl validate method end");
        return pass;
    }
	
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("TrainCtl populateBean method start");
		TrainBean bean=new TrainBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setTrainNo(DataUtility.getString(request.getParameter("trainNo")));
		bean.setRate(DataUtility.getString(request.getParameter("rate")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setFrom(DataUtility.getString(request.getParameter("from")));
		bean.setTo(DataUtility.getString(request.getParameter("to")));
		bean.setMaxSeat(DataUtility.getString(request.getParameter("maxSeat")));
		populateDTO(bean, request);
		log.debug("TrainCtl populateBean method end");
		return bean;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       TrainModel model = new TrainModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            TrainBean bean;
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
	        log.debug("TrainCtl doGet method end");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		TrainModel model=new TrainModel();
		long id=DataUtility.getLong(request.getParameter("id"));
	
		
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			TrainBean bean=(TrainBean)populateBean(request);
				try {
					if(id>0){
						
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						ServletUtility.setSuccessMessage("Data is successfully Saved", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(TRSView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
		TrainBean bean=	(TrainBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(TRSView.TRAIN_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.TRAIN_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(TRSView.TRAIN_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("Train doPost method end");
	}
	
   
	@Override
	protected String getView() {
		return TRSView.TRAIN_VIEW;
	}

}
