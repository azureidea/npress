<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/themes/basic/mobile/include.jsp" />
<script type="text/javascript" src="${PATH}/js/share.js"></script>
<title>${module.name} - ${WEB_NAME}</title>
</head>
<body>
  <section id="aside" data-transition="slide" data-aside="right">
    <header data-title="${module.name}" data-back="chevron-left">
        <nav class="on-right">
            <button data-view-aside="right" data-icon="menu"></button>
        </nav>
    </header>
    <article id="listview" class="list active scroll">
      <ul>
      <li>
      <strong><a href="${PATH}/action-Module/detail?id=${module.id}">${module.name}</a></strong>
      <div class="space"></div>
      <small>${module.content}</small>
      </li>
      </ul>
    </article>
    <footer>
      <nav>
        <c:forEach items="${modules}" var="o">
          <c:choose>
            <c:when test="${0 eq o.pos}">
              <c:choose>
                <c:when test="${0 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_self" href="${PATH}/${o.url}">${o.name}</a></c:when>
                <c:when test="${1 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_blank" href="${PATH}/${o.url}">${o.name}</a></c:when>
                <c:when test="${2 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_self" href="${o.url}">${o.name}</a></c:when>
                <c:when test="${3 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_blank" href="${o.url}">${o.name}</a></c:when>
                <c:when test="${4 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_self" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
                <c:when test="${5 eq o.type}"><a <c:if test="${o.id eq id}">class="active"</c:if> target="_blank" href="${PATH}/action-Module/detail?id=${o.id}">${o.name}</a></c:when>
              </c:choose>
            </c:when>
          </c:choose>
        </c:forEach>
      </nav>
    </footer>
  </section>
  <aside id="right" data-transition="right">
    <header data-title="分类"></header>
    <article class="list active">
        <ul>
        <c:forEach items="${cats}" var="o">
          <li data-view-aside="right"><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
        </c:forEach>
        </ul>
    </article>
  </aside>
  <script type="text/javascript" src="${PATH}/themes/${theme}/mobile/js/quo.js"></script>
  <script type="text/javascript" src="${PATH}/themes/${theme}/mobile/js/lungo.js"></script>
  <script type="text/javascript">
     Lungo.init({});
     I.want(function(){
       I.listen(I.$('aside','tag','a')[0],'click',function(m,e){
         self.location = '${PATH}/';
       });
     });
  </script>
</body>
</html>