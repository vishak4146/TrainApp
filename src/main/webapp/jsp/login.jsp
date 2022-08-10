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
<title>Login</title>

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
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Login
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="container">
		<div class="row">
			<div class="col-3">
				<figure class="figure">
					<img src="<%=TRSView.APP_CONTEXT%>/images/74787ea3c8ccf4ee641e1b5065d7069b.jpg" style="height: 360px;" class="figure-img img-fluid rounded" alt="...">
				</figure>
			</div>
			<div class="col-6">
				<div class="card shadow  mb-5 bg-body rounded">
					<h5 class="card-header bgcolor" style="color: white; height: 80px; font-size: 30px; padding-top: 17px"  align="center">Login</h5>
					<b><font color="red" size="2px"> <%=ServletUtility.getErrorMessage(request)%>
					</font></b> <b><font color="Green" size="2px"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b>
					<div class="card-body">
						<form method="post" action="<%=TRSView.LOGIN_CTL%>">

							<jsp:useBean id="bean"
								class="in.co.train.reservation.sys.bean.UserBean"
								scope="request"></jsp:useBean>

							<%
							String uri = (String) request.getAttribute("uri");
							%>

							<input type="hidden" name="uri" value="<%=uri%>"> <input
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
									<label style="margin-bottom: 6px;">User Name</label> <input
										type="text" name="userName" class="form-control"
										value="<%=DataUtility.getStringData(bean.getUserName())%>">
									<font color="red"><%=ServletUtility.getErrorMessage("userName", request)%></font>
								</div>
								<div class="mb-3">
									<label style="margin-bottom: 6px;">Password</label> <input
										type="password" name="password" class="form-control"
										value="<%=DataUtility.getStringData(bean.getPassword())%>">
									<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
								</div>

								<input type="submit" name="operation"
									value="<%=LoginCtl.OP_SIGN_IN%>"
									class="btn btn-primary btn btn-info" /> 

							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-3"><figure class="figure">
					<img src="<%=TRSView.APP_CONTEXT%>/images/maxresdefault.jpg" style="height: 360px;" class="figure-img img-fluid rounded" alt="...">
				</figure></div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>