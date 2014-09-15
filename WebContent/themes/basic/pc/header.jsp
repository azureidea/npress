<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="header">
  <div class="topbar">
    <div class="pull-right">
      <a class="weixin">微信扫一扫
        <ol>
          <li><img src="${PATH}/themes/basic/pc/images/weixin.jpg" width="128" height="128" /></li>
        </ol>
      </a>
      &nbsp;|&nbsp;
      <a href="http://weibo.com/liat6" target="_blank">新浪微博</a>
    </div>
  </div>
  <form id="searchForm" class="search pull-right" method="get" action="${PATH}/">
    <input name="s" type="text" placeholder="输入关键字" x-webkit-speech />
    <a href="javascript:void(0);" onclick="document.getElementById('searchForm').submit();">搜索</a>
  </form>
  <a class="logo" href="${PATH}/"></a>
  <ul class="module">
  <c:forEach items="${modules}" var="o">
    <c:choose>
      <c:when test="${0 eq o.pos}">
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
      </c:when>
    </c:choose>
    </li>
  </c:forEach>
  </ul>
</div>