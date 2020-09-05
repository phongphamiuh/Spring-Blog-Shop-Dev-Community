
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

          
 <script type="text/javascript" src='<c:url value="/webjars/sockjs-client/0.3.4/sockjs.min.js" />' ></script>
 	<script type="text/javascript" src='<c:url value="/webjars/stomp-websocket/2.3.0/stomp.min.js" />' ></script>
  	<script type="text/javascript" src='<c:url value="//webjars/jquery/2.0.3/jquery.min.js" />' ></script>
</head>

<body>
<button id="stop">Stop</button>
 
    <script src="<c:url value="/resources/chatapp.js"/>">
      
      </script>
    
    <div id="output"></div>
</body>
</html>