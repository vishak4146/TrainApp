
<%@page import="in.co.train.reservation.sys.controller.TrainCtl"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Edit</title>
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
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Train
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="wrapper rounded bg-white">
		<div class="h3">Add Train</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.TRAIN_CTL%>">

			<jsp:useBean id="bean" class="in.co.train.reservation.sys.bean.TrainBean"
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
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Name<font color="red">*</font></label> <input
							type="text" placeholder="Enter Name here..."
							name="name" class="form-control"
							value="<%=DataUtility.getStringData(bean.getName())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
					</div>

					<div class="col-md-6 mt-md-0 mt-3">
						<label>Train No<font color="red">*</font></label> <input
							type="text" placeholder="Enter Train No here..." name="trainNo"
							class="form-control"
							value="<%=DataUtility.getStringData(bean.getTrainNo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("trainNo", request)%></font>
					</div>

				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Rate<font color="red">*</font></label> <input
							type="text" placeholder="Enter Rate here..."
							name="rate" class="form-control"
							value="<%=DataUtility.getStringData(bean.getRate())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("rate", request)%></font>
					</div>
					<div class="col-md-6 mt-md-0 mt-3">
						<label>Date<font color="red">*</font></label> <input
							type="text" placeholder="Enter Date here..." id="datepicker" name="date"
							class="form-control"
							value="<%=DataUtility.getDateString(bean.getDate())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 mt-md-0 mt-3">
						<label>From<font color="red">*</font></label> <input
							type="text" placeholder="Enter From here..."
							name="from" class="form-control"
							value="<%=DataUtility.getStringData(bean.getFrom())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("from", request)%></font>
					</div>

					<div class="col-md-6 mt-md-0 mt-3">
						<label>To<font color="red">*</font></label> <input
							type="text" placeholder="Enter To here..." id="datepicker" name="to"
							class="form-control"
							value="<%=DataUtility.getStringData(bean.getTo())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("to", request)%></font>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>Max Seat<font color="red">*</font></label> <input
							type="text" placeholder="Enter MaxSeat here..." name="maxSeat"
							class="form-control"
							value="<%=DataUtility.getStringData(bean.getMaxSeat())%>">
						<font color="red"><%=ServletUtility.getErrorMessage("maxSeat", request)%></font>
					</div>
				</div>
				
				<input type="submit" name="operation"
					value="<%=TrainCtl.OP_SAVE%>"
					class="btn btn-primary mt-3" />or<input type="submit" name="operation"
					value="<%=TrainCtl.OP_RESET%>"
					class="btn btn-primary mt-3" />
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>