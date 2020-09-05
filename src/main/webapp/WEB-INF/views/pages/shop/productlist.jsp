<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>   	
        <div class="table-responsive">
              <table class="table table-striped">
              <caption>Products List</caption>
              		<thead>
              			<tr>
              				<td>STT</td>
              				<td>Book Name</td>
              				<td>Author</td>
              				<td>Price</td>
              				<td>PublishDate</td>
              				<td>Description</td>
              				<td>Page</td>
              				<td>Image</td>
              			</tr>
              		</thead>
              		<tbody>          			
              				<c:forEach items="${productList}" var="products">
              				<tr>
              					<td><c:out value="${products.bookID}"></c:out></td>
              					<td><c:out value="${products.bookName}"></c:out></td>
              					<td><c:out value="${products.author}"></c:out></td>
              					<td><c:out value="${products.price}"></c:out></td>
              					<td><c:out value="${products.publishDate}"></c:out></td>
              					<td><c:out value="${products.description}"></c:out></td>
              					<td><c:out value="${products.page}"></c:out></td>
              					<td><img src="${pageContext.request.contextPath}/shop/photo/${products.bookID}"></img></td>
              				</tr>   
              				</c:forEach>
              			      			
              		</tbody>
              </table>
        </div>
  