<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/themes/basic/pc/include.jsp" />
<script type="text/javascript" src="${PATH}/js/share.js"></script>
<title>首页 - ${WEB_NAME}</title>
</head>
<body>
  <jsp:include page="/themes/basic/pc/header.jsp" />
  <div class="body">
    <div class="left">
      <ul>
      <c:forEach items="${nlfPagingData.data}" var="o">
        <li>
          <div class="art_header"><a href="${PATH}/action-Article/detail?id=${o.id}">${o.title}</a></div>
          <div class="art_desc">${o.description}...<a class="detail" href="${PATH}/action-Article/detail?id=${o.id}">阅读全文</a></div>
          <div class="art_footer">
            <div class="pull-right">
              <a class="fa fa-share-alt">
                <ol>
                  <li class="fa fa-weibo" onclick="shareTo('weibo','${o.title}','${PATH}/action-Article/detail?id=${o.id}');">分享到新浪微博</li>
                  <li class="fa fa-qq" onclick="shareTo('qzone','${o.title}','${PATH}/action-Article/detail?id=${o.id}');">分享到QQ空间</li>
                </ol>
              </a>
              <a class="fa fa-comment-o"></a>
            </div>
            <i class="fa fa-clock-o">&nbsp;${o.day}</i>
          </div>
          <div class="clear"></div>
        </li>
      </c:forEach>
      </ul>
      <div><nlft:page near="1" /></div>
    </div>
    <div class="right">
      <jsp:include page="/themes/basic/pc/comp/cat.jsp" />
    </div>
    <div class="space"></div>
  </div>
  <jsp:include page="/themes/basic/pc/footer.jsp" />
</body>
</html>