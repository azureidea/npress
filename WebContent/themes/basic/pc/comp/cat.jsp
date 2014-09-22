<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="comp container">
  <div class="comp_header">分类</div>
  <div class="comp_body">
  <ul>
  <c:forEach items="${cats}" var="o">
    <c:if test="${1 eq o.type}">
    <li><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
    </c:if>
  </c:forEach>
  </ul>
  </div>
</div>