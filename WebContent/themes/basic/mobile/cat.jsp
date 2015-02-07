<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/themes/basic/mobile/include.jsp" />
    <script type="text/javascript" src="${PATH}/js/share.js"></script>
    <title>${cat.name} - ${WEB_NAME}</title>
  </head>
  <body>
    <header>
      <nav><a class="fa fa-chevron-left" href="${PATH}/"></a></nav>
      <i>${cat.name}</i>
      <nav>
        <a class="fa fa-bars"></a>
      </nav>
    </header>
    <article>
      <c:choose>
        <c:when test="${empty nlfPagingData.data}"><ul><li>没有数据！</li></ul></c:when>
        <c:otherwise>
          <ul>
          <c:forEach items="${nlfPagingData.data}" var="o">
          <li>
            <strong><a href="${PATH}/action-Article/detail?id=${o.id}" target="_self">${o.title}</a></strong>
            <small class="art_tag">
              <i class="fa fa-calendar">&nbsp;${o.day}</i>
              <i class="fa fa-tags">
              <c:forEach items="${o.tags}" var="tag" varStatus="index">
                <c:if test="${index.index>0}">|</c:if>
                <a class="tag" href="${PATH}/action-Cat/page?id=${tag.id}" target="_self">${tag.name}</a>
              </c:forEach>
              <c:if test="${empty o.tags}">无标签</c:if>
              </i>
            </small>
          </li>
          <li>
            <div>
              <c:if test="${not empty o.pic}">
                <a class="thumb" href="${PATH}/action-Article/detail?id=${o.id}"><img src="${o.pic}" width="${PIC_WIDTH}" height="${PIC_HEIGHT}" /></a>
              </c:if>
              <small>${o.description}</small>
            </div>
          </li>
          </c:forEach>
          </ul>
          <div><nlft:page near="1" /></div>
        </c:otherwise>
      </c:choose>
    </article>
    <jsp:include page="/themes/basic/mobile/footer_cat.jsp" />
    <jsp:include page="/themes/basic/mobile/include_cat.jsp" />
  </body>
</html>