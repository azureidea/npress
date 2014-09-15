<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div>友情链接</div>
<c:forEach items="${links}" var="o">
<li><a href="${o.url}" target="_blank">${o.name}</a></li>
</c:forEach>