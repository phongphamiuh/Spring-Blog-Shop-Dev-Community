<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<h1>Register</h1>
	
	<form:form method="POST" id="registerForm" class="form" modelAttribute="registrationForm"  enctype="multipart/form-data">
		<div class="form-group" >
			<label>Username:</label>
			<form:input type="text" class="form-control" path="username"
				placeholder="Username"
			/> 
		</div>
		<div class="form-group">
			<label>Password</label>
			<form:input type="text" class="form-control" path="password"
				placeholder="Password"
			/> 
		</div>
		<div class="form-group">
			<label>Email</label>
			<form:input type="text" class="form-control" path="email"
				placeholder="Email"
			/> 
		</div>
		<div class="form-group">
			<label>Phone Number</label>
			<form:input type="text" class="form-control" path="phoneNumber"
				placeholder="Phone Number"
			/> 
		</div>
		<div class="form-group">
			<label>City</label>
			<form:input type="text" class="form-control" path="city"
				placeholder="City"
			/> 
		</div>
		<div class="form-group">
				<label>Image Blog</label> 
				<form:input type="file" path="imageUserNew" class="form-control" 
					placeholder="imageUser"/>
			</div>
		<input type="submit" value="Register"/>
	</form:form>