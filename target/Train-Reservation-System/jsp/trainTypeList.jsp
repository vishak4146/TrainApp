<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Type</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item linkSize"><i
					class="fas fa-tachometer-alt"></i> <a class="link-dark"
					href="">Home</a></li>
				<li class="breadcrumb-item linkSize active" aria-current="page">
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Train Type
				</li>
			</ol>
		</nav>
	</div>
	<hr>
	<form method="post" action="">
				
				<b><font color="red" size="2px"> <%=ServletUtility.getErrorMessage(request)%>
					</font></b> <b><font color="Green" size="2px"> <%=ServletUtility.getSuccessMessage(request)%>
					</font></b>
				<br>
				

				<table class="table table-bordered border-primary" >
					<thead>
						<tr>
								<th scope="col"><input type="checkbox" id="selectall">Select
									All</th>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Email Id</th>
							<th scope="col">Mobile No</th>
							<th scope="col">DOB</th>
							<th scope="col">Qualification</th>
							<th scope="col">Experience</th>
							<th scope="col">Profile</th>
							<th scope="col">Joining Date</th>
							<th scope="col">Address</th>
								<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
							<tr>
									<td><input type="checkbox" class="case" name="ids"
										value=""></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>
								<td scope="row"></td>

									<td><a href="${editUrl} ${em.id}"><i
											class="fas fa-edit"></i></a></td>
							</tr>
					</tbody>
				</table>

				<div class="clearfix">
						<input type="submit" name="operation"
							class="btn btn-sm btn-danger float-start"
							value="Delete">


					<nav aria-label="Page navigation example float-end">
						<ul class="pagination justify-content-end" style="font-size: 13px">
							<li class="page-item"><input type="submit" name="operation"
								class="page-link"
								
								value="Previous"></li>
									<li class="page-item active"><a class="page-link activate"
										href="${addSearch}?pageNo=${i}">1</a></li>
									<li class="page-item"><a class="page-link"
										href="${addSearch}?pageNo=${i}">2</a></li>
							<li class="page-item"><input type="submit" name="operation"
								class="page-link"
								
								value="Next"></li>
						</ul>
					</nav>
				</div>


	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>