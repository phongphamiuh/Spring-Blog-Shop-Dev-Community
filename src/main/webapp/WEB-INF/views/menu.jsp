<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>	
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   	
<div>
	<ul>
<%-- 		<li><a href="${pageContext.request.contextPath}/shop">Product Form</a></li>		
		<li><a href='<c:url value="/shop/productlist"/>'>Product List</a></li> --%>
		
		<li><a href='<c:url value="/blog/bloglist"></c:url>'>Blog list</a></li>
			
		<security:authorize access="hasAnyRole('ROLE_USER')">
			<li><a href='<c:url value="/blog"></c:url>'>Post</a></li>
		</security:authorize>
		
		<security:authorize  access="hasAnyRole('ROLE_USER')">
     		<a href="${pageContext.request.contextPath}/blog/blogpost">
        	 Blog Post</a>
    	</security:authorize>  
    	
    	<security:authorize  access="hasAnyRole('ROLE_USER')">
     		<a href="${pageContext.request.contextPath}/blog/bookmarklist">
        	 Book Mark</a>
    	</security:authorize>  
    	
    	<security:authorize  access="hasAnyRole('ROLE_USER')">
     		<a href="${pageContext.request.contextPath}/blog/notification?username=${pageContext.request.userPrincipal.name}">
        	 Notification</a>
    	</security:authorize>  
		
		<fm:form method="POST" action="${pageContext.request.contextPath}/logout" id="logoutForm">
      		<input type="submit" value="Logout"/>
   		</fm:form>	
	</ul>
</div>