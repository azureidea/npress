<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<aside id="right" class="right">
  <header>分类<nav><a class="fa fa-arrow-circle-right" data-aside="right"></a></nav></header>
    <article>
      <ul>
      <c:forEach items="${cats}" var="o">
        <c:if test="${1 eq o.type}">
        <li><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
        </c:if>
      </c:forEach>
      </ul>
    </article>
</aside>
<script type="text/javascript">
   I.want(function(){
     I.ui.Mobile.render();
   });
</script>