package in.co.train.reservation.sys.controller;

public interface TRSView {

	public String APP_CONTEXT = "/Train-Reservation-System";

	public String PAGE_FOLDER = "/jsp";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/userRegistration.jsp";
	

	
	public String TRAIN_TYPE_VIEW = PAGE_FOLDER + "/trainType.jsp";
	public String TRAIN_TYPE_LIST_VIEW = PAGE_FOLDER + "/trainTypeList.jsp";
	
	public String TRAIN_VIEW = PAGE_FOLDER + "/train.jsp";
	public String TRAIN_LIST_VIEW = PAGE_FOLDER + "/trainList.jsp";
	
	public String BOOKING_VIEW = PAGE_FOLDER + "/booking.jsp";
	public String BOOKING_LIST_VIEW = PAGE_FOLDER + "/bookingList.jsp";
	
	public String SEARCH_TRAIN_VIEW = PAGE_FOLDER + "/searchTrain.jsp";
	public String ABOUT_VIEW = PAGE_FOLDER + "/About.jsp";
	public String CONTACT_VIEW = PAGE_FOLDER + "/contact.jsp";
	
	public String SUCCESS_VIEW = PAGE_FOLDER + "/success.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/login.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/changePassword.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/myProfile.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/forgetPassword.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String TRAIN_TYPE_CTL = APP_CONTEXT + "/ctl/trainType";
	public String TRAIN_TYPE_LIST_CTL = APP_CONTEXT + "/ctl/trainTypeList";
	
	public String TRAIN_CTL = APP_CONTEXT + "/ctl/train";
	public String TRAIN_LIST_CTL = APP_CONTEXT + "/ctl/trainList";
	
	public String SEARCH_TRAIN_CTL = APP_CONTEXT + "/searchTrain";
	public String ABOUT_CTL = APP_CONTEXT + "/About";
	public String CONTACT_CTL = APP_CONTEXT + "/Contact";

	
	public String BOOKING_CTL = APP_CONTEXT + "/ctl/booking";
	public String BOOKING_LIST_CTL = APP_CONTEXT + "/ctl/bookingList";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/registration";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";

}
