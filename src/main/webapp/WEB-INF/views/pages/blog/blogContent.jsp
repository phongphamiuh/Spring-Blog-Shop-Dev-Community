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
	<h1 id="header"></h1>
	<h2 id="title"></h2>
	<p id="content"></p>
	<a id="author"></a>
	<div id="blog">
	</div>
	<h1><c:out value="${blogContent.header}"></c:out></h1>
	<h2><c:out value="${blogContent.title}" /></h2>
	<p ></p>
	<a ></a>
	<h1><c:out value="${hello}" /></h1>
	<h1><c:out value="${voteSum}" /></h1>
	
	<div>	
		<ul>				
			<li><a id="increase" href='<c:url value="/blog/increase/vote/${id}" />'>Increase Vote</a></li>
			<li><a href='<c:url value="/blog/decrease/vote/${blogContent.id}" />'>Descrease Vote</a></li>
			<li><a href='<c:url value="/blog/bookmark/${blogContent.id}" />'>BookMark This Post</a></li>			   	
			<li><a href='<c:url value="/blog/edit?id=${blogContent.id}" />'>Edit This Post</a></li>
		</ul>
	</div>
	
	<br/>
	<div>
		<div id="output">
			<c:forEach items="${commentList}" var="comment">
				<b>Message : <c:out value="${comment.message}" /> || From <c:out value="${comment.user}" /></b>
				<img src="<c:url value="/blog/image/comment/${comment.user}"/>"/>
		<br/>
	</c:forEach>
		</div>
		
		<form:form id="blogForm"  method="POST" modelAttribute="spittleForm" action="${pageContext.request.contextPath}/blog/comment/${blogContent.id}">
			<form:textarea path="text"/>
			<input type="submit"> 
		</form:form>
		
	
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
	
   	$('#blogForm').submit(function(e){
  		e.preventDefault();
  		var text = $('#blogForm').find('textarea[name="text"]').val();
  		sendSpittle(text);	
  	}); 
  
  	$("#increase").click(function(){
  		
  		sendNotifications();		
	})

  	var url = 'http://' + window.location.host + '/SpringBlogShopDevCommunity/spittr';
  	var sock = new SockJS(url);
  	var stomp = Stomp.over(sock);
    
    stomp.connect('guest', 'guest', function(frame) {
      console.log('*****  Connected  *****');
      stomp.subscribe("/topic/blogfeed/${blogContent.user.username}", handleSpittle);
      stomp.subscribe("/topic/notification", handleNotification);
      stomp.subscribe("/user/queue/notifications", handleNotification);
    });
    
    function handleSpittle(message) {
  	  console.log('Spittle:', message);
  	  $('#output').append("<b> Message : " + JSON.parse(message.body).message +"</b> ||"
  			  +"<b> From :"+JSON.parse(message.body).user + "</b><br/>"
  			  );
    }
   
    function handleNotification(message) {
      console.log('Notification: ', message);
      $('#output').append("<b>Received: " + 
          JSON.parse(message.body).message + "</b><br/>")
    }
    
    function sendSpittle(text) {
      console.log('Sending Spittle');
      stomp.send("/app/blog/show/"+${id}, {}, 
          JSON.stringify({ 'text': text }));
    } 
    function sendNotifications() {
    	console.log('Sending Spittle');
        stomp.send("/app/blog/show/"+${id}, {}, 
           );
	}
    
    $('#stop').click(function() {sock.close()});
  })
</script> 


<script type="text/javascript">
	function increase() {
	    $.ajax({
	    	type:'GET',
	    	url : 'http://localhost:8080/SpringBlogShopDevCommunity/blog/vote/increase/'+${id},
	    	contentType:'application/json',
	        dataType: 'json',
	        success : function(data) {	
	            $('#header').html(data);
	            
	        }      	
	    });
	}	   
</script>

<script type="text/javascript">
   $(document).ready(function(){
	   function crunchifyAjax() {
	        $.ajax({
	        	type:'GET',
	        	url : 'http://localhost:8080/SpringBlogShopDevCommunity/blog/show/'+${id},
	        	contentType:'application/json',
	            dataType: 'json',
	            success : function(data) {       	
	                $('#header').html(data.header);
	                $('#title').html(data.title);
	                $('#content').html(data.content);
	                $('#author').html(data.user.username);
	            }      	
	        });
	    }	   
	  
	   crunchifyAjax();
   })
</script>
