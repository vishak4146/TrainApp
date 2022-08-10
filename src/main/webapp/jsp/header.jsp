<%@page import="in.co.train.reservation.sys.bean.UserBean"%>
<%@page import="in.co.train.reservation.sys.controller.TRSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<%=TRSView.APP_CONTEXT%>/css/style.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<style type="text/css">
.linkSize {
	font-size: 14px;
}

.bgcolor {
	background-color: teal;
}
</style>
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	rel="stylesheet">


<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

</head>
<body>
	<%
	UserBean userbean = (UserBean) session.getAttribute("user");
	%>
	<nav class="navbar navbar-expand-lg trs-header navbar-light"
		style="background-color: teal">
		<div class="container-fluid">
			<a class="herder-text navbar-brand"
				style="color: white; font-size: 26px;" href="#">Train
				Reservation System</a>
		</div>
	</nav>
	<div class="trs-div-header">
		<ul class="nav justify-content-center">

			<li class="nav-item"><a class="nav-link active link-light"
				aria-current="page" href="<%=TRSView.WELCOME_CTL%>">Home</a></li>
			<%
			if (userbean != null) {
			%>
			<%
			if (userbean.getRoleId() == 1) {
			%>


			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle link-light" href="#"
				id="navbarDropdown" role="button" data-bs-toggle="dropdown"
				aria-expanded="false"> Train</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="<%=TRSView.TRAIN_CTL%>">Add
							Train</a></li>
					<li><a class="dropdown-item"
						href="<%=TRSView.TRAIN_LIST_CTL%>">Train List</a></li>

				</ul></li>
				
				<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.BOOKING_LIST_CTL%>">Train Bookings </a></li>


			<%
			} else if (userbean.getRoleId() == 2) {
			%>
			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.SEARCH_TRAIN_CTL%>">Search Train</a></li>
				
				<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.BOOKING_LIST_CTL%>">Booking</a></li>
			<%
			}
			%>

			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.MY_PROFILE_CTL%>">My Profile</a></li>

			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>

			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.LOGIN_CTL%>?operation=logout">Logout</a></li>
			<%
			} else {
			%>

			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.SEARCH_TRAIN_CTL%>">Search Train</a></li>

			
			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.ABOUT_CTL%>">About Us</a></li>
				
			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.CONTACT_CTL%>">Contact</a></li>
			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.LOGIN_CTL%>">Login</a></li>
			<li class="nav-item"><a class="nav-link link-light"
				href="<%=TRSView.USER_REGISTRATION_CTL%>">Sign Up</a></li>
				
				<%
			}
			%>
		</ul>
	</div>
</body>
</html>