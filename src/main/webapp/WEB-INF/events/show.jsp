<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Event</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background-color:#F3F3F3">
	<div class = "container">
	<h1 style="font-weight:bold; font-decoration:italic; color:#132E73">Evently.</h1>
		
	<div style="margin-top:60px; margin-left:120px;" class="row">
		<div class="col">
			<h2> GoT Party  </h2>
			<br><br>
			<p> Host: <c:out value="${event.user.firstname}" /> </p>
			<p> Date: <c:out value="${event.date}" /> </p>
			<p> Location: <c:out value="${event.location}" /> </p>
			<p> People who are attending this event: </p>
			<br>
			<table class="table">
			    <thead>
			        <tr>
			            <th scope="col">Name</th>
			            <th scope="col">Location</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${event.users}" var="us">
	        			<tr>
				            <td><c:out value="${us.firstname}"/></td>
				          	<td><c:out value="${us.location}"/></td>
	       			 	</tr>
	        	</c:forEach>
			       
			    </tbody>
			</table>
		</div>
		
		<div class="col">
			<h2> Message Wall  </h2>
			
		</div>
	</div>
	</div>
</body>
</html>