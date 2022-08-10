<%@page import="in.co.train.reservation.sys.controller.TrainTypeCtl"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page import="in.co.train.reservation.sys.controller.LoginCtl"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Type</title>

<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>

	<br>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item linkSize"><i
					class="fas fa-tachometer-alt"></i> <a class="link-dark" href="#">Home</a></li>
				<li class="breadcrumb-item linkSize active" aria-current="page">
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Train Type
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="container">
		<div class="row">
			
			<div class="col-6">
				<div class="card shadow  mb-5 bg-body rounded">
					<h5 class="card-header bgcolor" style="color: white; height: 60px; font-size: 25px; padding-top: 17px"  align="center">Train Type</h5>
					<b><font color="red" size="2px"> <%=ServletUtility.getErrorMessage(request)%>
					</font></b> <b><font color="Green" size="2px"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b>
					<div class="card-body">
						<form method="post" action="<%=TRSView.TRAIN_TYPE_CTL%>">

							<jsp:useBean id="bean"
								class="in.co.train.reservation.sys.bean.TrainTypeBean"
								scope="request"></jsp:useBean>

						 <input
								type="hidden" name="id" value="<%=bean.getId()%>"> <input
								type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
							<input type="hidden" name="modifiedBy"
								value="<%=bean.getModifiedBy()%>"> <input type="hidden"
								name="createdDatetime"
								value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
							<div class="card-body">

								<div class="mb-3">
									<label style="margin-bottom: 6px;">Name</label> <input
										type="text" name="name" class="form-control" placeholder="Enter Name Here..."
										value="<%=DataUtility.getStringData(bean.getName())%>">
									<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
								</div>
								<div class="mb-3">
									<label style="margin-bottom: 6px;">Description</label> <textarea
										 name="description" class="form-control" rows="4" cols="4"  placeholder="Enter Description Here.."
										><%=DataUtility.getStringData(bean.getDescription())%></textarea>
									<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
								</div>

								<input type="submit" name="operation"
									value="<%=TrainTypeCtl.OP_SAVE%>"
									class="btn btn-primary btn btn-info" />&nbsp;&nbsp;&nbsp;<input type="submit" name="operation"
									value="<%=TrainTypeCtl.OP_RESET%>"
									class="btn btn-primary btn btn-info" />

							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-6"><figure class="figure">
					<img src="<%=TRSView.APP_CONTEXT%>/images/maxresdefault.jpg" style="height: 400px;" class="figure-img img-fluid rounded" alt="...">
				</figure></div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>