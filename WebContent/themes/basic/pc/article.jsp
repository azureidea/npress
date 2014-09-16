<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>${art.title} - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header.jsp" />
    <div class="body">
        <div class="left">
        <ul>
            <li>
                <div class="art_header"><a href="${PATH}/action-Article/detail?id=${art.id}">${art.title}</a></div>
                <div class="art_desc">${art.content}</div>
                <div class="clear"></div>
            </li>
        </ul>
        <jsp:include page="/themes/basic/pc/comp/plugin_2.jsp" />
        </div>
        <div class="right">
          <jsp:include page="/themes/basic/pc/comp/cat.jsp" />
          <jsp:include page="/themes/basic/pc/comp/plugin_3.jsp" />
        </div>
        <div class="clear"></div>
    </div>
    <jsp:include page="/themes/basic/pc/footer.jsp" />
</body>
</html>