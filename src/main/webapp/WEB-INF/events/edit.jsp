<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Events</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background-color:#F3F3F3">
	<div class = "container">
	<h1 style="font-weight:bold; font-decoration:italic; color:#132E73">Evently.</h1>
		
	<!-- Create new event function  -->
		<br><br>
		<h1> Edit Event  </h1>
	<p> <form:errors class="alert alert-danger" path="editEventObj"/>   </p>
	
	<form:form action="/events/${editEventObj.id}" method = "post" modelAttribute="editEventObj">
		<p>
			<form:label path="name">Name</form:label>
			<form:input path="name" class="form-control col-6"/>
		</p>
		<p>
			<form:label path="date">Date</form:label>
			<form:input path="date" class="form-control col-6"/>
		</p>
		 <p>
            <form:label path="location">Location:</form:label><br>
            <form:input type="text" path="location" class = "form-control col-4" style="display:inline-block"/>
            <form:select path= "state" class="form-control col-2" style="display:inline-block">
							<form:option value="AL"/>
							<form:option value="AK"/>
							<form:option value="AZ"/>
							<form:option value="AR"/>/
							<form:option value="CA"/>
							<form:option value="CO"/>
							<form:option value="CT"/>
							<form:option value="DE"/>
							<form:option value="DC"/>
							<form:option value="FL"/>
							<form:option value="GA"/>
							<form:option value="HI"/>
							<form:option value="ID"/>
							<form:option value="IL"/>
							<form:option value="IN"/>
							<form:option value="KS"/>
							<form:option value="KY"/>
							<form:option value="LA"/>
							<form:option value="ME"/>
							<form:option value="MD"/>
							<form:option value="MA"/>
							<form:option value="MI"/>
							<form:option value="MN"/>
							<form:option value="MS"/>
							<form:option value="MO"/>
							<form:option value="MT"/>
							<form:option value="NE"/>
							<form:option value="NV"/>
							<form:option value="NH"/>
							<form:option value="NJ"/>
							<form:option value="NM"/>
							<form:option value="NY"/>
							<form:option value="NC"/>
							<form:option value="ND"/>
							<form:option value="OH"/>
							<form:option value="OK"/>
							<form:option value="OR"/>
							<form:option value="PA"/>
							<form:option value="RI"/>
							<form:option value="SC"/>
							<form:option value="SD"/>
							<form:option value="TN"/>
							<form:option value="TX"/>
							<form:option value="UT"/>
							<form:option value="VT"/>
							<form:option value="VA"/>
							<form:option value="WA"/>
							<form:option value="WV"/>
							<form:option value="WI"/>
							<form:option value="WY"/>
		</form:select>
        </p>
        <form:hidden path="user" value="${user.id }"/>
		<input class="btn btn-info" type="submit" value="Edit Event" />
	</form:form>
		
	</div>
</body>
</html>