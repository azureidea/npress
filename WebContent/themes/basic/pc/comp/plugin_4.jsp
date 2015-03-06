<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${modules}" var="o">
  <c:choose>
    <c:when test="${4 eq o.pos}">
      <c:choose>
        <c:when test="${0 eq o.type}"><a target="_self" href="${PATH}/${o.url}">${o.name}</a></c:when>
        <c:when test="${1 eq o.type}"><a target="_blank" href="${PATH}/${o.url}">${o.name}</a></c:when>
        <c:when test="${2 eq o.type}"><a target="_self" href="${o.url}">${o.name}</a></c:when>
        <c:when test="${3 eq o.type}"><a target="_blank" href="${o.url}">${o.name}</a></c:when>
        <c:when test="${4 eq o.type}"><a target="_self" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
        <c:when test="${5 eq o.type}"><a target="_blank" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
        <c:when test="${6 eq o.type}">${fn:replace(o.content,'${id}',id)}</c:when>
      </c:choose>
    </c:when>
  </c:choose>
</c:forEach>