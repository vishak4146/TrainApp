
<%@page import="in.co.train.reservation.sys.bean.BookingBean"%>
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
<title>Success Booking</title>
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
					<i class="fa fa-arrow-right" aria-hidden="true"></i>Booking Invoice
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="wrapper rounded bg-white">
		<div class="h3">Booking Invoice</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.TRAIN_CTL%>">

			
			
		
				<% 
					BookingBean tBean=(BookingBean)session.getAttribute("booking");
				
				    TrainBean trBean=new TrainModel().findByPK(tBean.getTrainId());
				%>
		
			<div class="form">
			
			<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label><b>Invoice No </b></label> 
						<p><%=tBean.getInvoiceNo()%></p>
					</div>
				</div>
			
			
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label><b>Name</b></label> 
						<p><%=tBean.getName()%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Train No</b></label>
						<p><%=trBean.getTrainNo()%></p>
					</div>
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Train Name</b></label> <p><%=tBean.getTrainName()%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>From</b></label> <p><%=trBean.getFrom()%></p>
					</div>

					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>To</b></label><p><%=trBean.getTo()%></p>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Travel Date</b></label> 
						<p><%=tBean.getTravelDate()%></p>
					</div>
					
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Booking Date</b></label> 
						<p><%=DataUtility.getDateString(tBean.getBookingDate())%></p>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Adult</b></label> 
						<p><%=tBean.getAdult()%></p>
					</div>
					
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Child</b></label> 
						<p><%=tBean.getChild()%></p>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Total Member</b></label> 
						<p><%=tBean.getTotalMember()%></p>
					</div>
					
					<div class="col-md-6 mt-md-0 mt-3">
						<label><b>Total Amount</b></label> 
						<p><%=tBean.getTotalAmount()%></p>
					</div>
				</div>
				
				
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>