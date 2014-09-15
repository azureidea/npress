<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="form">
<table class="list">
    <thead>
        <tr>
            <th class="sn"></th>
            <th class="sn">序号</th>
            <th>网站名称</th>
            <th>URL</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="4"></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty links}">
        <tr>
            <td colspan="4">暂无友情链接</td>
        </tr>
    </c:if>
    <c:forEach items="${links}" var="o" varStatus="index">
        <tr>
            <td><input type="checkbox" value="${o.id}" /></td>
            <td>${o.index}</td>
            <td>${o.name}</td>
            <td>
              <a href="${o.url}" target="_blank">${o.url}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<script type="text/javascript">
trOnSelected(I.$('class','list')[0],function(id){
  showPanel('admin-Link/pageModify?id='+id);
});
</script>