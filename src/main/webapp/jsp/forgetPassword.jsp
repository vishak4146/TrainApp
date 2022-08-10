
<%@page import="in.co.train.reservation.sys.controller.ForgetPasswordCtl"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ForgetPassword</title>
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
					<i class="fa fa-arrow-right" aria-hidden="true"></i> ForgetPassword
				</li>
			</ol>
		</nav>
	</div>
	<hr>

	<div class="wrapper rounded bg-white">
		<div class="h3">ForgetPassword</div>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>
		<form method="post" action="<%=TRSView.FORGET_PASSWORD_CTL%>">

			
			<div class="form">
				<div class="row">
					<div class="col-md-12 mt-md-0 mt-3">
						<label>UserName<font color="red">*</font></label> <input
							type="text" placeholder="Enter UserName here..."
							name="userName" class="form-control"
							value="<%=ServletUtility.getParameter("userName", request)%>">
						<font color="red"><%=ServletUtility.getErrorMessage("userName", request)%></font>
					</div>
				</div>


				<input type="submit" name="operation"
					value="<%=ForgetPasswordCtl.OP_GO%>"
					class="btn btn-primary mt-3" />
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>