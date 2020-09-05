<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src='<c:url value="/webjars/jquery/2.0.3/jquery.min.js" />' ></script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
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
	     		<li><a href='<c:url value="/blog/delete/${blogContent.id}"/>'>Delete This Post</a></li>
				<li><a href='<c:url value="/blog/edit" />'>Edit This Post</a></li> 			
		</ul>
	</div>
</div>

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

