<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="header container">
    <div class="title">${WEB_NAME}</div>
    <ul class="module">
	<c:forEach items="${modules}" var="o">
	   <c:choose>
	   <c:when test="${o.id eq id}">
	   <li class="current">
	   </c:when>
	   <c:when test="${1 eq o.home}">
     <li class="home">
     </c:when>
     <c:otherwise><li></c:otherwise>
	   </c:choose>
	   <c:choose>
		  <c:when test="${0 eq o.type}"><a target="_self" href="${PATH}/${o.url}">${o.name}</a></c:when>
		  <c:when test="${1 eq o.type}"><a target="_blank" href="${PATH}/${o.url}">${o.name}</a></c:when>
		  <c:when test="${2 eq o.type}"><a target="_self" href="${o.url}">${o.name}</a></c:when>
		  <c:when test="${3 eq o.type}"><a target="_blank" href="${o.url}">${o.name}</a></c:when>
		  <c:when test="${4 eq o.type}"><a target="_self" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
		  <c:when test="${5 eq o.type}"><a target="_blank" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
	   </c:choose>
	   </li>
	</c:forEach>
	</ul>
</div>