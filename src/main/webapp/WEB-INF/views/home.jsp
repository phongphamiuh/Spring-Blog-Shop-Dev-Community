<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Home</title>
    <script type="text/javascript" src='<c:url value="/webjars/sockjs-client/0.3.4/sockjs.min.js" />' ></script>
 	<script type="text/javascript" src='<c:url value="/webjars/stomp-websocket/2.3.0/stomp.min.js" />' ></script>
  	<script type="text/javascript" src='<c:url value="/webjars/jquery/2.0.3/jquery.min.js" />' ></script>
  </head>
  <body>
  	<p>
  	Try opening this app in two separate browsers.
  	
  	</p>
  
  	<form  id="spittleForm">
  		<textarea rows="4" cols="60" name="text"></textarea>
  		<input type="submit"/>
  	</form>   
    <div id="output"></div>
  </body>
  
 <script type="text/javascript">	
  $(document).ready(function(){
  	$('#spittleForm').submit(function(e){
  		e.preventDefault();
  		var text = $('#spittleForm').find('textarea[name="text"]').val();
  		sendSpittle(text);
  	});

  	var url = 'http://' + window.location.host + '/SpringBlogShopDevCommunity/spittr';
  	var sock = new SockJS(url);
  	var stomp = Stomp.over(sock);

    stomp.connect('guest', 'guest', function(frame) {
      console.log('*****  Connected  *****');
      stomp.subscribe("/topic/spittlefeed", handleSpittle);
      stomp.subscribe("/user/queue/notifications", handleNotification);
    });
    
    function handleSpittle(message) {
  	  console.log('Spittle:', message);
  	  $('#output').append("<b>Received message :: " + JSON.parse(message.body).message + "</b><br/>");
    }
   
    function handleNotification(message) {
      console.log('Notification: ', message);
      $('#output').append("<b>Received: " + 
          JSON.parse(message.body).message + "</b><br/>")
    }
    
    function sendSpittle(text) {
      console.log('Sending Spittle');
      stomp.send("/app/spittle", {}, 
          JSON.stringify({ 'text': text }));
    }

    $('#stop').click(function() {sock.close()});
  })
    
   </script>
</html>