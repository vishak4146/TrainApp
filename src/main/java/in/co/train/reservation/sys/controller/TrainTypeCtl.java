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
import in.co.train.reservation.sys.bean.TrainTypeBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.model.TrainTypeModel;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.DataValidator;
import in.co.train.reservation.sys.util.PropertyReader;
import in.co.train.reservation.sys.util.ServletUtility;


/**
 * Servlet implementation class TrainTypeCtl
 */
@WebServlet(name="TrainTypeCtl",urlPatterns={"/ctl/trainType"})
public class TrainTypeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log=Logger.getLogger(TrainTypeCtl.class);

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("TrainTypeCtl validate method start");
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name",
                    PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }

        log.debug("TrainTypeCtl validate method end");
        return pass;
    }
	
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("TrainTypeCtl populateBean method start");
		TrainTypeBean bean=new TrainTypeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		log.debug("TrainTypeCtl populateBean method end");
		return bean;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainTypeCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       TrainTypeModel model = new TrainTypeModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            TrainTypeBean bean;
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
	        log.debug("TrainTypeCtl doGet method end");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("TrainTypeCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		TrainTypeModel model=new TrainTypeModel();
		long id=DataUtility.getLong(request.getParameter("id"));
	
		
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			
			TrainTypeBean bean=(TrainTypeBean)populateBean(request);
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
		TrainTypeBean bean=	(TrainTypeBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(TRSView.TRAIN_TYPE_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(TRSView.TRAIN_TYPE_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(TRSView.TRAIN_TYPE_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("TrainType doPost method end");
	}
	
   
	@Override
	protected String getView() {
		return TRSView.TRAIN_TYPE_VIEW;
	}

}
