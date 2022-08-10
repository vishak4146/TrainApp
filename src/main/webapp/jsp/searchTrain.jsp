
<%@page import="in.co.train.reservation.sys.model.BookingModel"%>
<%@page import="in.co.train.reservation.sys.model.TrainModel"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.train.reservation.sys.bean.TrainBean"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Train</title>
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
					<i class="fa fa-arrow-right" aria-hidden="true"></i> Trains
				</li>
			</ol>
		</nav>
	</div>
	<hr>
	<form method="post" action="<%=TRSView.SEARCH_TRAIN_CTL%>">
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Trains</h5>
			<div class="card-body">
				<div class="row g-3">

					<div class="col">
						<input type="text" placeholder="Search By From here..."
							name="from" class="form-control"
							value="<%=ServletUtility.getParameter("from", request)%>">
					</div>

					<div class="col">
						<input type="text" placeholder="Search By To here..."
							name="to" class="form-control"
							value="<%=ServletUtility.getParameter("to", request)%>">
					</div>
					
				

					<div class="col">
						<input type="submit" class="btn  btn-outline-primary"
							name="operation" value="Search"></input> or <input type="submit"
							class="btn  btn-outline-secondary" name="operation"
							value="Reset">
					</div>
				</div>
				<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font></b><br>

				<table class="table table-bordered border-primary">
					<thead>
						<tr>
							
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Train No</th>
							<th scope="col">Rate</th>
							<th scope="col">Date</th>
							<th scope="col">From</th>
							<th scope="col">To</th>
							<th scope="col">AvilableSeat</th>
							
							
							<%if(userbean != null){ %>
							<th scope="col">Book Seat</th>
							<%} %>
						</tr>
					</thead>
					<tbody>
						<%
						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;
						int size = ServletUtility.getSize(request);
						TrainBean bean = null;
						BookingModel model=new BookingModel();
						List list = ServletUtility.getList(request);
						Iterator<TrainBean> iterator = list.iterator();
					
						while (iterator.hasNext()) {
							bean = iterator.next();
						%>
						<tr>
							<td scope="row"><%=index++%></td>
							<td scope="row"><%=bean.getName()%></td>
							<td scope="row"><%=bean.getTrainNo()%></td>
							<td scope="row"><%=bean.getRate()%></td>
							<td scope="row"><%=DataUtility.getDateString(bean.getDate())%></td>
							<td scope="row"><%=bean.getFrom()%></td>
							<td scope="row"><%=bean.getTo()%></td>
							<td scope="row"><%=(DataUtility.getLong(bean.getMaxSeat())- model.countMember(bean.getId())>0)?DataUtility.getLong(bean.getMaxSeat())- model.countMember(bean.getId()):0%></td>
							<% if(userbean!=null){ %>
							<td><a class="btn btn-info btn-sm" href="<%=TRSView.BOOKING_CTL%>?tId=<%=bean.getId()%>">Book Seat</a></td>
							<%} %>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

				<div class="clearfix">
				
					<nav aria-label="Page navigation example float-end">
						<ul class="pagination justify-content-end" style="font-size: 13px">
							<li class="page-item"><input type="submit" name="operation"
								class="page-link" <%=(pageNo == 1) ? "disabled" : ""%> value="Previous"></li>
							
							<li class="page-item"><input type="submit" name="operation"
								class="page-link"
								<%=((list.size() < pageSize) || size == pageNo * pageSize) ? "disabled" : ""%>
								value="Next"></li>
						</ul>
					</nav>
				</div>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">

			</div>

		</div>
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>