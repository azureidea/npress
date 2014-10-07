<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/themes/basic/mobile/include.jsp" />
<script type="text/javascript" src="${PATH}/js/share.js"></script>
<title>${art.title} - ${WEB_NAME}</title>
</head>
<body>
  <section>
    <header>
      <nav><a class="fa fa-arrow-circle-left" href="${PATH}/"></a></nav>
      ${art.title}
      <nav>
        <a class="fa fa-bars" data-aside="right"></a>
      </nav>
    </header>
    <article class="padding">
      <ul>
        <li>
          <strong><a href="${PATH}/action-Article/detail?id=${art.id}" target="_self">${art.title}</a></strong>
          <small class="art_tag">
            <i class="fa fa-calendar">&nbsp;${art.day}</i>
            <i class="fa fa-tags">
            <c:forEach items="${art.tags}" var="tag" varStatus="index">
              <c:if test="${index.index>0}">|</c:if>
              <a class="tag" href="${PATH}/action-Cat/page?id=${tag.id}" target="_self">${tag.name}</a>
            </c:forEach>
            <c:if test="${empty art.tags}">无标签</c:if>
            </i>
          </small>
          <small>${art.content}</small>
        </li>
      </ul>
    </article>
    <jsp:include page="/themes/basic/mobile/footer_home.jsp" />
  </section>
  <jsp:include page="/themes/basic/mobile/include_cat.jsp" />
</body>
</html>