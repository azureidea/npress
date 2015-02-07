<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="footer">
  <div class="footer-body">
    <ul class="infos">
    <c:forEach items="${modules}" var="o">
    <c:choose>
      <c:when test="${1 eq o.pos}">
      <li>
      <c:choose>
          <c:when test="${0 eq o.type}"><a target="_self" href="${PATH}/${o.url}">${o.name}</a></c:when>
          <c:when test="${1 eq o.type}"><a target="_blank" href="${PATH}/${o.url}">${o.name}</a></c:when>
          <c:when test="${2 eq o.type}"><a target="_self" href="${o.url}">${o.name}</a></c:when>
          <c:when test="${3 eq o.type}"><a target="_blank" href="${o.url}">${o.name}</a></c:when>
          <c:when test="${4 eq o.type}"><a target="_self" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
          <c:when test="${5 eq o.type}"><a target="_blank" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
        </c:choose>
      </li>
      </c:when>
    </c:choose>
    </c:forEach>
    </ul>
    <ul class="links"></ul>
    <div class="space"></div>
    <div>
      <i class="copyright">${WEB_COPYRIGHT} 蜀ICP备15000307号-1</i>
      <i class="info">基于<a href="https://github.com/6tail/nlf/" target="_blank">NLF框架</a>构建</i>
    </div>
  </div>
</div>
<script type="text/javascript">
I.want(function(){
  I.net.Page.find('action-Link/page',null,I.$('class','links')[0]);
});
</script>