<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="in.co.train.reservation.sys.controller.ContactCtl"%>
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
        <title>Contact</title>
        
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
        <link rel="stylesheet" type="text/css" media="all" href="css/card.css">
        <link rel="stylesheet" type="text/css" media="all" href="css/social.css">
        
	</nav>
        
        <h1>Contact Us!</h1>
        <br>
        <div class="row">
            <div class="column">
              <div class="card">
                  <h2>Emergencies</h2><br>
                  <p>To report signal malfunctions or other safety concerns, 
                      contact our Emergency Department. 
                      The number below is a 24/7 service.</p>
                  <br>
                  <h2>1-800-465-9239</h2>
              </div>
            </div>
            <div class="column">
                <div class="card">
                    <h2>Public Inquiries</h2><br>
                  <p>To learn about our involvement in the community, 
                      what it is like to live near the railway or 
                      for general inquiries call:</p>
                  <br>
                  <h2>1-888-888-5909</h2>
                </div>
            </div>
            <div class="column">
                <div class="card">
                    <h2>Human Resources</h2><br>
                  <p>For inquiries regarding your current or 
                      future job at our's, email the Recruitment Team.</p>
                  <br>
                  <h2><a href = "career.rail@gmail.com">career.rail@gmail.com</a></h2>
                </div>
            </div>
            <div class="column">
                <div class="card">
                    <h2>Headquarters</h2><br>
                    <p>Ontario (Headquarters)<br>
                        941 Notre Dame Ave<br>
                        Greater Sudbury<br>
                        ON P3A 2T7<br>
                        Canada</p>
                  <br>
                  
                </div>
            </div>
        </div><br>
        
        <div class="social">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
            <div class="socialmedia">
        <!-- Add font awesome icons -->
        <a href="#" class="fa fa-facebook"></a>
        <a href="#" class="fa fa-twitter"></a>
        <a href="#" class="fa fa-linkedin"></a>
        <a href="#" class="fa fa-instagram"></a>
        <a href="#" class="fa fa-google"></a>
            </div>
        </div>
        

    </body>
</html>