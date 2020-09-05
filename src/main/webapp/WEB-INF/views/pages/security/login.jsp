<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<h1>Login</h1>
	
	 	<c:if test="${param.error != null}">
           <div>
                Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </c:if>
            </div>
        </c:if>
        <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
        <c:if test="${param.logout == null}">
            <div>
                You have been logged out.
            </div>
        </c:if>

     <br/>
	<p>New here? Click
		<a href="<c:url value="/register" />" >Here</a>
		to register
	</p>
	<form method="POST" action="${pageContext.request.contextPath}/spring_secutity_check" id="loginForm">
		<label for="username">Username:</label>
		<input type="text" name="username" id="username"/><br/>
		<label for="password">Password:</label>
		<input type="password" name="password" id="password"/><br/>
		<input type="submit" value="Login"/>
	</form>
