<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>404 - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header.jsp" />
    <div class="body">
        <div class="container">
          <div class="art_desc">您访问的页面被猫吃了！</div>
          <div class="art_footer"><a href="${PATH}/">首页</a></div>
          <div class="clear"></div>
        </div>
    </div>
    <jsp:include page="/themes/basic/pc/footer.jsp" />
</body>
</html>