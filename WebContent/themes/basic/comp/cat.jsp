<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="comp container">
  <div class="comp_header">分类</div>
  <div class="comp_body">
  <ul>
  <c:forEach items="${cats}" var="o">
    <li><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
  </c:forEach>
  </ul>
  </div>
</div>