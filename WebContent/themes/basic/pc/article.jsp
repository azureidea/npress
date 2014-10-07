<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>${art.title} - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header_home.jsp" />
    <div class="body">
        <div class="left">
        <jsp:include page="/themes/basic/pc/comp/plugin_4.jsp" />
        <ul>
            <li>
                <div class="art_header"><a href="${PATH}/action-Article/detail?id=${art.id}">${art.title}</a></div>
                <div class="art_tag">
                  <i class="fa fa-calendar">&nbsp;${art.day}</i>
                  <i class="fa fa-tags">
                  <c:forEach items="${art.tags}" var="tag" varStatus="index">
                    <c:if test="${index.index>0}">|</c:if>
                    <a class="tag" href="${PATH}/action-Cat/page?id=${tag.id}" target="_self">${tag.name}</a>
                  </c:forEach>
                  <c:if test="${empty art.tags}">无标签</c:if>
                  </i>
                </div>
                <div class="art_desc">${art.content}</div>
                <div class="clear"></div>
            </li>
        </ul>
        <jsp:include page="/themes/basic/pc/comp/plugin_2.jsp" />
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