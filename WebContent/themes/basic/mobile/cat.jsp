<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/themes/basic/mobile/include.jsp" />
<script type="text/javascript" src="${PATH}/js/share.js"></script>
<title>${cat.name} - ${WEB_NAME}</title>
</head>
<body>
  <section>
    <header>
      <nav><a class="fa fa-home" href="${PATH}/"></a></nav>
      ${cat.name}
      <nav>
        <a class="fa fa-bars" data-aside="right"></a>
      </nav>
    </header>
    <article class="padding">
      <c:choose>
        <c:when test="${empty nlfPagingData.data}"><ul><li>没有数据！</li></ul></c:when>
        <c:otherwise>
          <ul>
          <c:forEach items="${nlfPagingData.data}" var="o">
          <li>
            <c:if test="${not empty o.pic}">
              <a class="thumb" href="${PATH}/action-Article/detail?id=${o.id}"><img src="${o.pic}" width="${PIC_WIDTH}" height="${PIC_HEIGHT}" /></a>
            </c:if>
            <strong><a href="${PATH}/action-Article/detail?id=${o.id}" target="_self">${o.title}</a></strong>
            <small>${o.description}</small>
          </li>
          </c:forEach>
          </ul>
          <div><nlft:page near="1" /></div>
        </c:otherwise>
      </c:choose>
    </article>
    <jsp:include page="/themes/basic/mobile/footer_home.jsp" />
  </section>
  <jsp:include page="/themes/basic/mobile/include_cat.jsp" />
</body>
</html>