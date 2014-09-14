<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="form">
<table class="list">
    <thead>
        <tr>
            <th class="sn"></th>
            <th class="sn">序号</th>
            <th>名称</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="3"></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty modules}">
        <tr>
            <td colspan="3">暂无模块</td>
        </tr>
    </c:if>
    <c:forEach items="${modules}" var="o" varStatus="index">
        <tr>
            <td><input type="checkbox" value="${o.id}" /></td>
            <td>${o.index}</td>
            <td>${o.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<script type="text/javascript">
trOnSelected(I.$('class','list')[0],function(id){
  showPanel('admin-Module/pageModify?id='+id);
});
</script>