
<%@page import="in.co.train.reservation.sys.controller.BookingCtl"%>
<%@page import="in.co.train.reservation.sys.model.TrainModel"%>
<%@page import="in.co.train.reservation.sys.bean.TrainBean"%>
<%@page import="in.co.train.reservation.sys.controller.TrainCtl"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
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
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Seat Booking
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="wrapper rounded bg-white">
		<div class="h3">Booking</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.BOOKING_CTL%>">

			<jsp:useBean id="bean" class="in.co.train.reservation.sys.bean.BookingBean"
				scope="request"></jsp:useBean>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
		
				<% 
					TrainBean tBean=new TrainModel().findByPK(DataUtility.getLong(String.valueOf(session.getAttribute("tId"))));
				%>
		
			<div class="form">
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Name<font color="red">*</font></label> 
						<p><%=userbean.getName()%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Train No<font color="red">*</font></label>
						<p><%=tBean.getTrainNo()%></p>
					</div>
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Train Name<font color="red">*</font></label> <p><%=tBean.getName()%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label>From<font color="red">*</font></label> <p><%=tBean.getFrom()%></p>
					</div>

					<div class="col-md-6 mt-md-0 mt-3">
						<label>To<font color="red">*</font></label><p><%=tBean.getTo()%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Adult<font color="red">*</font></label> <input
							type="text" placeholder="Enter Adult here..." name="adult"
							class="form-control"
							value="<%=(bean.getAdult()>0)?bean.getAdult():""%>">
						<font color="red"><%=ServletUtility.getErrorMessage("adult", request)%></font>
					</div>
					
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Child<font color="red">*</font></label> <input
							type="text" placeholder="Enter Child here..." name="child"
							class="form-control"
							value="<%=(bean.getChild()>0)?bean.getChild():""%>">
						<font color="red"><%=ServletUtility.getErrorMessage("child", request)%></font>
					</div>
				</div>
				
				<input type="submit" name="operation"
					value="<%=BookingCtl.OP_SAVE%>"
					class="btn btn-primary mt-3" />
					or<input type="submit" name="operation"
					value="<%=BookingCtl.OP_CANCEL%>"
					class="btn btn-primary mt-3" />
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>