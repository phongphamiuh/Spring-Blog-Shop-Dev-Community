<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE HTML>    
<html >    
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>AdmissionPage</title>  
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/bootstrap.min.css" />"></link>
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jqgrid/css/ui.jqgrid.css" />"></link>	
 		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/header.css" />"></link> 		 
    </head>    
<body>    
		<div class="container">
			<h4>hello</h4>
	        <div >
	        	<tiles:insertAttribute name="header" />
	        </div>    
	        <div class="row">
	        	<div class="col-lg-3">
		        	<tiles:insertAttribute name="menu" />		           
	        	</div> 
	        	<div class="col-lg-7">
	        		<tiles:insertAttribute name="body" />
	        	</div> 
	       	</div>
	       	<div class="col-lg-12">
	       		<tiles:insertAttribute name="footer" />
	       	</div> 
	      </div>  	       		 
</body> 
<script type="text/javascript" src='<c:url value="/resources/script/jquery-3.5.1.js"/>'></script> 
<script type="text/javascript" src='<c:url value="/resources/script/bootstrap.js"/>'></script>  
<script type="text/javascript" src='<c:url value="/resources/jqgrid/js/jquery.jqGrid.min.js"/>'></script>  

</html>    