<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src='<c:url value="/webjars/jquery/2.0.3/jquery.min.js" />' ></script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<script type="text/javascript" src='<c:url value="/webjars/sockjs-client/0.3.4/sockjs.min.js" />' ></script>
<script type="text/javascript" src='<c:url value="/webjars/stomp-websocket/2.3.0/stomp.min.js" />' ></script>
<script type="text/javascript" src='<c:url value="/webjars/jquery/2.0.3/jquery.min.js" />' ></script>

<div>
	
	<br/>
	<div>
		<div id="output">
			<c:forEach items="${listNotification}" var="notification">
				<b>Notification is: <c:out value="${notification.message}"/> From <c:out value="${notification.user.username}" /></b>
			</c:forEach>
		</div>
		
		 <%--
		  <form  id="blogForm">
	  			<textarea rows="4" cols="60" name="text"></textarea>
	  		<input type="submit"/>
	  	</form> 
	  	 --%>

	  	
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){	
  	var url = 'http://' + window.location.host + '/SpringBlogShopDevCommunity/spittr';
  	var sock = new SockJS(url);
  	var stomp = Stomp.over(sock);
    
    stomp.connect('guest', 'guest', function(frame) {
      console.log('*****  Connected  *****');
      stomp.subscribe("/topic/blogfeed/${username}", handleSpittle);
    
    });
    
    function handleSpittle(message) {
  	  console.log('Spittle:', message);
  	  $('#output').append("<b> Notification is : " + JSON.parse(message.body).message +"</b> ||"
  			  +"<b> From :"+JSON.parse(message.body).user+ "</b><br/>"
  			  );
    }  
    $('#stop').click(function() {sock.close()});
  })
</script> 

