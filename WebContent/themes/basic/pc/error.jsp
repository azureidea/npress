<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>错误 - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header.jsp" />
    <div class="body">
        <div class="container">
          <div class="art_desc">${empty e?"未知错误":(empty e.message?"未知错误":e.message)}</div>
          <div class="clear"></div>
        </div>
    </div>
    <jsp:include page="/themes/basic/pc/footer.jsp" />
</body>
</html>