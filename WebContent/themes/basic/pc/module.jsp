<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<title>${module.name} - ${WEB_NAME}</title>
</head>
<body>
    <jsp:include page="/themes/basic/pc/header_module.jsp" />
    <div class="body">
      <div class="left">
        <jsp:include page="/themes/basic/pc/comp/plugin_4.jsp" />
        <ul>
            <li>
                <div class="art_header"><a href="${PATH}/action-Module/detail?id=${module.id}">${module.name}</a></div>
                <div class="art_desc">${module.content}</div>
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