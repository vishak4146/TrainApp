<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="in.co.train.reservation.sys.controller.AboutCtl"%>
<%@page import="in.co.train.reservation.sys.model.BookingModel"%>
<%@page import="in.co.train.reservation.sys.model.TrainModel"%>
<%@page import="in.co.train.reservation.sys.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.train.reservation.sys.bean.TrainBean"%>
<%@page import="in.co.train.reservation.sys.util.ServletUtility"%>
<!DOCTYPE html>
<html>
    <head>
        <title>About Us</title>
        
        <meta name="viewport" content ="width=device-width, initial-scale=1.0">
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
        <style>
            h1{
                color: #1d5550;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #1d5550;
              }

              li {
                float: left;
              }

              li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
              }

            li a:hover {
              background-color: #111;
            }
            
           
           
            h2{
               
               color: #1d5550;
               font-size: 28px;
            }
            
            p{
                
                font-size: 18px;
                color: black;
            }
           
            
        </style>
    </head>
    <body>
    <%@ include file="header.jsp" %>
        <link rel="stylesheet" type="text/css" media="all" href="css/login.css">
        <link rel="stylesheet" type="text/css" media="all" href="css/style.css">
       
		
	</nav>
       
        <h1>About Us!</h1>
        <br>
        <h2>An Encompassing Insightful Experience- Give Power to Your Train Travel Decision-Making</h2>

<p>Travel is fun and when it comes to train travel, we go into the bypaths and
    un-trodden depths of wilderness and travel explorations to tell the world the glories of our journey!</p><br>

<h2>What makes us drive through?</h2>

<p>Our Motto: Simplifying Train Travels
With over 23.9 million passengers traveling and commuting daily by train, we aim to serve
its passengers with inclusive and comprehensive information that helps those making informed decisions,
thus simplifying their train travels.
Ours is a premier portal for train travelers. 
We strive to be the fastest, mobile-friendly site that answers all your train travel questions in a few taps. 
A comprehensive hub of train travel information that dedicatedly works to give its users insightful information,
all under one roof, to help them plan and take their train travel decisions in a better way..
</p><br>

<h2>How and Why We Started?</h2>

<p>Our journey started two weeks back when a handful of technology experts got together to remove
the information black-box that shrouds information regarding Railways. 
Railways is the preferred transportation mode for a vast majority of the population, 
yet pertinent information is not readily available and the information rather available is scattered. 
It is then, we stepped in to tackle such worries of its railway passengers. 
We have always aimed to be the capable train-travel advisor, helping passengers make informed decisions 
about their train travel.</p>
	<%@ include file="footer.jsp" %>
</body>
</html>