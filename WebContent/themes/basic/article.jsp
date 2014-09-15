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
<title>${art.title} - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/header.jsp" />
    <div class="body">
        <div class="left">
        <ul>
            <li>
                <div class="art_header"><a href="${PATH}/action-Article/detail?id=${art.id}">${art.title}</a></div>
                <div class="art_desc">${art.content}</div>
                <div class="clear"></div>
            </li>
        </ul>
        </div>
        <div class="right">
          <jsp:include page="/themes/basic/comp/cat.jsp" />
        </div>
        <div class="clear"></div>
    </div>
    <jsp:include page="/themes/basic/footer.jsp" />
</body>
</html>