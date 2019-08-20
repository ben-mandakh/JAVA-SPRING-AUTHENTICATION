<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registration Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background-color:#F3F3F3">
	<div class = "container">
		<div class="row">
			<div class="col">
				<h1 style="font-weight:bold; font-decoration:italic; color:#132E73">Reglog.</h1>
				
			</div>
			<div class="col">
				<h4 style="float:right; font-style:italic"> Welcome, <c:out value="${user.firstname}" />  <a href="/logout">| Logout</a></h4>
			</div>
		</div>
	</div>
</body>
</html>