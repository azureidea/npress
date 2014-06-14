<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="form">
<table class="list">
    <thead>
        <tr>
            <th class="sn"></th>
            <th class="sn">序号</th>
            <th>用户名</th>
            <th>姓名</th>
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
            <td colspan="4">暂无管理员</td>
        </tr>
    </c:if>
    <c:forEach items="${nlfPagingData.data}" var="o" varStatus="index">
        <tr>
            <td><input type="checkbox" value="${o.id}" /></td>
            <td>${index.count}</td>
            <td>${o.account}</td>
            <td>${o.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nlft:jsbegin>
trOnSelected(I.$('class','list')[0],function(id){
  showPanel('admin-User/pageModify?id='+id);
});
</nlft:jsbegin>