<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="right">
  <header>
    <nav><a class="fa fa-none"></a></nav>
    <i>分类</i>
    <nav><a class="fa fa-chevron-right"></a></nav>
  </header>
  <article>
    <ul>
    <c:forEach items="${cats}" var="o">
      <c:if test="${1 eq o.type}">
      <li><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
      </c:if>
    </c:forEach>
    </ul>
  </article>
</div>
<script type="text/javascript">
I.want(function(){
 I.ui.Mobile.render();
});
</script>