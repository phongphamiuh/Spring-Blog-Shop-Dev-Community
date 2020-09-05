<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<h1><c:out value="${user.username}" /></h1>
	
	<form:form action="${pageContext.request.contextPath}/blog" method="POST"  
			modelAttribute="blogForm" class="form" enctype="multipart/form-data">
		<c:if test="${blogForm.id != null}">
			<div class="form-group">
				<label>ID</label> 
				<form:hidden path="id" class="form-control" 
					placeholder="ID"/>
				${blogForm.id}
			</div>
		</c:if>
		
		<div class="form-group">
			<label>Header</label>
			<form:input type="text" class="form-control" path="header"
				placeholder="Header"
			/> 
		</div>
		
		<div class="form-group">
			<label>Title</label> 
			<form:input type="text" class="form-control" path="title"
				placeholder="Title"
			/> 
		</div>
		
		<div class="form-group">
			<label>Author</label> 
			<form:input type="text" class="form-control" path="author"
				placeholder="author"
			/> 
		</div>
		
		<div class="form-group">
			<label>Content</label> 
			<form:textarea class="form-control" path="content"
				placeholder="Title" />
		</div>
		
		<div class="form-group">
			<label>Time Create</label> 
			<form:input type="date" path="timeCreate" class="form-control" 
				placeholder="timeCreate"/>
		</div>
			
		<c:if test="${empty blogForm.id}">
			<div class="form-group">
				<label>Image Blog</label> 
				<form:input type="file" path="imageBlogNew" class="form-control" 
					placeholder="imageBlog"/>
			</div>
		</c:if>
		
		<c:if test="${blogForm.id != null}">
			<img src="${pageContext.request.contextPath}/blog/photo/${blogForm.id}"></img>
			
			<div class="form-group">
				<label>Image Blog</label> 		
				<form:input type="file" path="imageBlogNew" class="form-control" 
					/>
			</div>
		</c:if>
		
		
		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>