
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
	<div class="row">
		<div  class="col-lg-4">
			<h4><a href='<c:url value="/"/>'>Hello Blog Shop</a></h4>
		</div>
		<div class="col-lg-4">
			 <a href="${pageContext.request.contextPath}/shop/?language=en">English</a>    
     				 <a href="${pageContext.request.contextPath}/shop/?language=vn">VietNam</a> 
		</div>
		
		<c:choose>
  			<c:when test="${pageContext.request.userPrincipal.name == null}">
  				<div class="col-lg-4">
					<a href="<c:url value="/login" />">Login</a>
				</div>
  			</c:when>
 			<c:otherwise>
 				<div>
 					Hello :<c:out value="${pageContext.request.userPrincipal.name}"/>
 				</div>
 			</c:otherwise>
		</c:choose>
		
	</div>	    