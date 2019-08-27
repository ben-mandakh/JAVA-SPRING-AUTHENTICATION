<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="background-color:#F3F3F3">
	<div class = "container">
		<div class="row">
			<div class="col">
				<h1 style="font-weight:bold; font-decoration:italic; color:#132E73">Evently.</h1>
				
			</div>
			<div class="col">
				<h4 style="float:right; font-style:italic"> Welcome, <c:out value="${user.firstname}" />  <a href="/logout">| Logout</a></h4>
			</div>
		</div>
		<br><br>
		
		<!-- Events in your state table  -->
	
		<p>Here are some of the events in your state. </p>
		
		<table class="table">
	    <thead>
	        <tr>
	            <th scope="col">Name</th>
	            <th scope="col">Date</th>
	            <th scope="col">Location</th>
	            <th scope="col">Host</th>
	            <th scope="col">Action/Status</th>
	        </tr>
	    </thead>
		<tbody>
	        <c:forEach items="${allEvents}" var="event">
        	<c:choose>
        	<c:when test = "${event.state == user.state}">
	        
	        <tr>
	            <td> <a href="/events/${event.id}/show"> <c:out value="${event.name}"/> </a> </td> 
	            <td><fmt:formatDate pattern="MMMM' 'dd, yyyy" value="${event.date}" /></td>
	            <td><c:out value="${event.location}"/></td>
	            <td><c:out value="${event.user.firstname}"/></td>
	            <td>
		            <c:choose>
					    <c:when test = "${user.id == event.user.id}">
					        <a class ="btn btn-primary" href="/events/${event.id}/edit"> Edit </a>                   <!-- Update button -->
	            			<form style="display:inline-block" action="/events/${event.id}/delete" method="post">
		            			<input type="hidden" name="_method" value="delete" />
		            			<input class ="btn btn-warning" type="submit" value="delete" />   <!-- Delete button -->
	            			</form>
					    </c:when>
					    <c:otherwise>
					        
						        <form:form action ="/${event.id}/join" model ="post">
									<input type="submit" value="Join" class="btn btn-info">
								</form:form>
					         
					    </c:otherwise>
					</c:choose>
	            </td>
	        </tr>
	        </c:when>
	        </c:choose>
	        </c:forEach>
	    </tbody>
		</table>
		<br><br>
		
		<!-- Events in other state table  -->
		
		<p>Here are some of the events in other states. </p>
		
		<table class="table">
	    <thead>
	        <tr>
	            <th scope="col">Name</th>
	            <th scope="col">Date</th>
	            <th scope="col">Location</th>
	            <th scope="col">State</th>
	            <th scope="col">Host</th>
	            <th scope="col">Action</th>
	        </tr>
	    </thead>
		<tbody>
	        <c:forEach items="${allEvents}" var="event">
        	<c:choose>
        	<c:when test = "${event.state != user.state}">
	        <tr>
	            <td><a href="/events/${event.id}/show"> <c:out value="${event.name}"/> </a> </td>
	            <td><fmt:formatDate pattern="MMMM' 'dd, yyyy" value="${event.date}" /></td>
	            <td><c:out value="${event.location}"/></td>
	            <td><c:out value="${event.state}"/></td>
	          	<td><c:out value="${event.user.firstname}"/></td>
	            <td> 
	             <form:form action ="/${event.id}/join" model ="post">
					<input type="submit" value="Join" class="btn btn-info">
				</form:form>                   
	        </tr>
	       	</c:when>
	        </c:choose>
	        </c:forEach>
	    </tbody>
		</table>
	
	<!-- Create new event function  -->
		<br><br>
		<h1> Create New Event  </h1>
	<p> <form:errors class="alert alert-danger" path="newEventObj"/>   </p>
	
	<form:form action="/events/new" method = "post" modelAttribute="newEventObj">
		<p>
			<form:label path="name">Name</form:label>
			<form:input path="name" class="form-control col-4"/>
		</p>
		<p>
			<form:label path="date">Date</form:label>
			<form:input path="date" class="form-control col-4"/>
		</p>
		 <p>
            <form:label path="location">Location:</form:label><br>
            <form:input type="text" path="location" class = "form-control col-4" style="display:inline-block"/>
            <form:select path= "state" class="form-control col-1" style="display:inline-block">
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
		<input class="btn btn-info" type="submit" value="Create an Event" />
	</form:form>
		
	</div>
</body>
</html>