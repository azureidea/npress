<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<footer>
  <nav>
    <c:forEach items="${modules}" var="o">
      <c:choose>
        <c:when test="${0 eq o.pos}">
          <c:choose>
            <c:when test="${0 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_self" href="${PATH}/${o.url}">${o.name}</a></c:when>
            <c:when test="${1 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_blank" href="${PATH}/${o.url}">${o.name}</a></c:when>
            <c:when test="${2 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_self" href="${o.url}">${o.name}</a></c:when>
            <c:when test="${3 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_blank" href="${o.url}">${o.name}</a></c:when>
            <c:when test="${4 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_self" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
            <c:when test="${5 eq o.type}"><a <c:if test="${1 eq o.home}">class="active"</c:if> target="_blank" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
          </c:choose>
        </c:when>
      </c:choose>
    </c:forEach>
  </nav>
</footer>