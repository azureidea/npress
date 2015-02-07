<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/themes/basic/mobile/include.jsp" />
    <script type="text/javascript" src="${PATH}/js/share.js"></script>
    <title>${module.name} - ${WEB_NAME}</title>
  </head>
  <body>
    <header>
      <nav><a class="fa fa-chevron-left" href="${PATH}/"></a></nav>
      <i>${module.name}</i>
      <nav>
        <a class="fa fa-bars"></a>
      </nav>
    </header>
    <article>
      <ul>
        <li>
          <strong><a href="${PATH}/action-Article/detail?id=${module.id}" target="_self">${module.name}</a></strong>
        </li>
        <li>
          <small>${module.content}</small>
        </li>
      </ul>
    </article>
    <jsp:include page="/themes/basic/mobile/footer_module.jsp" />
    <jsp:include page="/themes/basic/mobile/include_cat.jsp" />
  </body>
</html>