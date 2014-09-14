<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="form">
<table class="list">
    <thead>
        <tr>
            <th class="sn"></th>
            <th class="sn">序号</th>
            <th>标题</th>
            <th>发布时间</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="4"><nlft:page near="1" /></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty nlfPagingData.data}">
        <tr>
            <td colspan="4">暂无文章</td>
        </tr>
    </c:if>
    <c:forEach items="${nlfPagingData.data}" var="o" varStatus="index">
        <tr>
            <td><input type="checkbox" value="${o.id}" /></td>
            <td>${index.count}</td>
            <td>${o.title}</td>
            <td>${o.time}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<script type="text/javascript">
trOnSelected(I.$('class','list')[0],function(id){
  showPanel('admin-Article/pageModify?id='+id);
});
</script>