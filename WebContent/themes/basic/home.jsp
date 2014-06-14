<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/include.jsp" />
<title>首页 - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/header.jsp" />
    <div class="body">
        <div class="left container">
        <ul>
        <c:forEach items="${nlfPagingData.data}" var="o">
            <li>
                <div class="art_header"><a href="${PATH}/action-Article/detail?id=${o.id}">${o.title}</a></div>
                <div class="art_desc">${o.description}</div>
                <div class="art_footer"><a href="${PATH}/action-Article/detail?id=${o.id}">阅读全文</a></div>
                <div class="clear"></div>
            </li>
        </c:forEach>
        </ul>
        <div><nlft:page normal="true" near="1" /></div>
        </div>
        <div class="right">
          <jsp:include page="/themes/basic/comp/user.jsp" />
          <jsp:include page="/themes/basic/comp/cat.jsp" />
        </div>
        <div class="clear"></div>
    </div>
    <jsp:include page="/themes/basic/footer.jsp" />
</body>
</html>