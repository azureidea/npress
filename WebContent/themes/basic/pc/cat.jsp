<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>${cat.name} - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header_cat.jsp" />
    <div class="body">
        <div class="left">
        <div class="title">${cat.name}</div>
        <c:choose>
        <c:when test="${empty nlfPagingData.data}"><ul><li><div class="art_desc">没有数据！</div></li></ul></c:when>
        <c:otherwise>
          <ul>
          <c:forEach items="${nlfPagingData.data}" var="o">
          <li>
            <div class="art_header"><a href="${PATH}/action-Article/detail?id=${o.id}">${o.title}</a></div>
            <div class="art_desc"><c:if test="${not empty o.pic}">
              <img class="pic" src="${o.pic}" width="${PIC_WIDTH}" height="${PIC_HEIGHT}" />
            </c:if>
            ${o.description}</div>
            <div class="clear"></div>
            <div class="art_footer">
              <i class="fa fa-calendar-o">&nbsp;${o.day}</i>
              <c:forEach items="${o.tags}" var="tag">
                <a class="tag" href="${PATH}/action-Cat/page?id=${tag.id}" target="_self">${tag.name}</a>
              </c:forEach>
            </div>
            <div class="clear"></div>
          </li>
          </c:forEach>
          </ul>
          <div><nlft:page near="1" /></div>
        </c:otherwise>
        </c:choose>
        </div>
        <div class="right">
          <jsp:include page="/themes/basic/pc/comp/plugin_5.jsp" />
          <jsp:include page="/themes/basic/pc/comp/cat.jsp" />
          <jsp:include page="/themes/basic/pc/comp/plugin_3.jsp" />
        </div>
        <div class="clear"></div>
    </div>
    <jsp:include page="/themes/basic/pc/footer.jsp" />
</body>
</html>