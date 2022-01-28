<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:partial>
<div>
<h1>Art work</h1>
<p>Art: ${art.artName}</p>
<p>Artist: ${art.artist.firstName}</p>
<p>Description: ${art.description}</p>

<h5>Ratings for this art:</h5>
<c:forEach items="${art.ratings}" var="rating">
 <p>
 Rated By:  ${rating.ratedBy.firstName} , Rating: ${rating.rating} 
 </p>
</c:forEach>

<form:form action="/art/addRating" method="post" modelAttribute="newRating">
      <div class="form-group">
	        <form:label path="rating">Rating</form:label>
	        <form:errors class="text-danger" path="rating"/>
	        <form:input class="form-control" path="rating"/>
	    </div>
	      <!-- Hidden Input for userId -->
	     <form:input type="hidden" value="${userId}" path="ratedBy"/>
	       <!-- Hidden Input for ArtId -->
	     <form:input type="hidden" value="${art.id}" path="ratedArt"/>
	     <button>Rate it!!</button>
</form:form>
<c:if test="${art.artist.id==userId}">
	<a href="/arts/${art.id}/edit">Edit</a>
	<a href="/arts/${art.id}/delete">Delete</a>
</c:if>
</div>
</t:partial>
