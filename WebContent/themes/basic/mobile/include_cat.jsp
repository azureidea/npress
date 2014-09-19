<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<aside id="right" data-transition="right">
  <header data-title="分类"></header>
  <article class="list active">
      <ul>
      <c:forEach items="${cats}" var="o">
        <li data-view-aside="right"><a href="${PATH}/action-Cat/page?id=${o.id}" target="_self">${o.name}(${o.count})</a></li>
      </c:forEach>
      </ul>
  </article>
</aside>
<script type="text/javascript" src="${PATH}/themes/${theme}/mobile/js/quo.js"></script>
<script type="text/javascript" src="${PATH}/themes/${theme}/mobile/js/lungo.js"></script>
<script type="text/javascript">
   Lungo.init({});
   I.want(function(){
     I.$('aside','tag','a')[0].href='${PATH}/';
   });
</script>