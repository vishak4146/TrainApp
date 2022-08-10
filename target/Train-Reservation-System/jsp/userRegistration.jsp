<%@page import="in.co.train.reservation.sys.controller.UserRegistrationCtl"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link href="<%=TRSView.APP_CONTEXT%>/css/login.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="wrapper rounded bg-white">
		<div class="h3">Registration Form</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.USER_REGISTRATION_CTL%>">
		 
		 <jsp:useBean id="bean" class="in.co.train.reservation.sys.bean.UserBean"
            scope="request"></jsp:useBean>
            
				<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
		<div class="form">
			<div class="row">
				<div class="col-md-12 mt-md-0 mt-3">
					<label>Full Name*</label> <input type="text" name="name" class="form-control" value="<%=DataUtility.getStringData(bean.getName())%>">
					<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-md-6 mt-md-0 mt-3">
					<label>User Name*</label> <input type="text" name="userName" class="form-control" value="<%=DataUtility.getStringData(bean.getUserName())%>">
					<font  color="red"><%=ServletUtility.getErrorMessage("userName", request)%></font>
				</div>
				<div class="col-md-6 mt-md-0 mt-3">
					<label>Password*</label> <input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword())%>">
					<font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6 mt-md-0 mt-3">
					<label>Birthday*</label> <input type="text" id="datepicker" name="dob" class="form-control" value="<%=DataUtility.getDateString(bean.getDob())%>">
					<font
                        color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
				</div>
				<div class="col-md-6 mt-md-0 mt-3">
					<label>Gender*</label>
					<div class="d-flex align-items-center mt-2">
						<label class="option"> <input type="radio" name="gender" value="Male">Male
							<span class="checkmark"></span>
						</label> <label class="option ms-4"> <input type="radio"
							name="gender" value="Female">Female <span class="checkmark"></span>
						</label>
						<font  color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 mt-md-0 mt-3">
					<label>Email Id*</label> <input type="text" name="email" class="form-control" value="<%=DataUtility.getStringData(bean.getEmail())%>">
					<font  color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
				</div>
				<div class="col-md-6 mt-md-0 mt-3">
					<label>Contact Number*</label> <input type="text" name="contactNo" class="form-control" value="<%=DataUtility.getStringData(bean.getContactNo())%>">
					<font  color="red"><%=ServletUtility.getErrorMessage("contactNo", request)%></font>
				</div>
			</div>
			
			<input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>" class="btn btn-primary mt-3"  />
		</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>