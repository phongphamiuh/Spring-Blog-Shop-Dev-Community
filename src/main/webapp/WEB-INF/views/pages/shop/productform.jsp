<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>   	
        <div >
               <form:form modelAttribute="bookForm" method="POST"  enctype="multipart/form-data"
                class="form"
               >    <div class="form-group">
               			<label class="col-lg-3">Book</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="bookName"/>
               				<form:errors path="bookName" cssStyle="error" />            			
               			</div>
               		</div> 
               		  
               		<div class="form-group">
               			<label class="col-lg-3">Author</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="author"/>
               				     
               			</div>
               		</div>   
               		
               		<div class="form-group">
               			<label class="col-lg-3">Price</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="price"/>
               				
               			</div>
               		</div>   
               		
               		<div class="form-group">
               			<label class="col-lg-3">Publish Date</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="publishDate"/>
               				
               			</div>
               		</div>   
               		
               		<div class="form-group">
               			<label class="col-lg-3">Description</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="description"/>
               				
               			</div>
               		</div>   
           			                		 			               		           			                     			
               		<div class="form-group">
               			<label class="col-lg-3">Page</label>
               			<div class="col-lg-9">
               				<form:input class="form-control" path="page"/>   
               						
               			</div>
               		</div> 
               		
               		<div class="form-group">
               			<label class="col-lg-3">File</label>
               			<div class="col-lg-9">
               				<form:input type="file" class="form-control" path="image"/>
               				<span class="erImage">*</span>
               			</div>
               		</div> 
               		               		       			              		       			      			
               		<button class="btn btn-primary" type="submit">Submit</button>
               </form:form>
        </div>
  