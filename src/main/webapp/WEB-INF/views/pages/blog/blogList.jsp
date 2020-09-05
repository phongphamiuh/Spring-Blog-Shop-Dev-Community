<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<div class="table-responsive">
              <table class="table table-striped">
              <caption>Products List</caption>
              		<thead>
              			<tr>
              				<td>ID</td>
              				<td>Header</td>
              				<td>Title</td>
              				<td>Author</td>
              				<td>Content</td>
              				<td>Time create</td>
              				<td>Image blog</td>
              			</tr>
              		</thead>
              		<tbody>          			
              				<c:forEach items="${blogList}" var="blogList">
              				<tr>
              					<td><c:out value="${blogList.id}"></c:out></td>
              					<td><a href='<c:url value="/blog/show/${blogList.id}" />'><c:out value="${blogList.header}" /></a></td>
              					<td><c:out value="${blogList.title}"></c:out></td>
              					<td><c:out value="${blogList.author}"></c:out></td>
              					<td><c:out value="${blogList.content}"></c:out></td>
              					<td><c:out value="${blogList.timeCreate}"></c:out></td>
              					
              					<td><img src="${pageContext.request.contextPath}/blog/photo/${blogList.id}"></img></td>
              				</tr>   
              				</c:forEach>
              			      			
              		</tbody>
              </table>
        </div>