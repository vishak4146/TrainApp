

<%@page import="in.co.train.reservation.sys.controller.MyProfileCtl"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Profile</title>
<link href="<%=TRSView.APP_CONTEXT%>/css/login.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>

	<br>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item linkSize"><i
					class="fas fa-tachometer-alt"></i> <a class="link-dark"
					href="<%=TRSView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item linkSize active" aria-current="page">
					<i class="fa fa-arrow-right" aria-hidden="true"></i>Profile
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="wrapper rounded bg-white">
		<div class="h3">Profile</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.MY_PROFILE_CTL%>">

			<jsp:useBean id="bean" class="in.co.train.reservation.sys.bean.UserBean"
				scope="request"></jsp:useBean>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			<div class="form">
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Name<font color="red">*</font></label> <input
							type="text" placeholder="Enter Name here..."
							name="name" class="form-control"
							value="<%=DataUtility.getStringData(bean.getName())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>User Name<font color="red">*</font></label> <input
							type="text" placeholder="Enter User Name here..."
							name="userName" class="form-control"
							value="<%=DataUtility.getStringData(bean.getUserName())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("userName", request)%></font>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Email<font color="red">*</font></label> <input
							type="text" placeholder="Enter Email Id here..."
							name="email" class="form-control"
							value="<%=DataUtility.getStringData(bean.getEmail())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Dob<font color="red">*</font></label> <input
							type="text" id="datepicker" placeholder="Enter Dob here..."
							name="dob" class="form-control"
							value="<%=DataUtility.getDateString(bean.getDob())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Contact Number<font color="red">*</font></label> <input
							type="text" placeholder="Enter Contact No here..."
							name="contactNo" class="form-control"
							value="<%=DataUtility.getStringData(bean.getContactNo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("contactNo", request)%></font>
					</div>
				</div>
				
				<input type="submit" name="operation"
					value="<%=MyProfileCtl.OP_SAVE%>"
					class="btn btn-primary mt-3" />or<input type="submit" name="operation"
					value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"
					class="btn btn-primary mt-3" />
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>