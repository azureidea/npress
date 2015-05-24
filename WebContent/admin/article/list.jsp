<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table class="list">
    <thead>
        <tr>
            <th class="sn">序号</th>
            <th>标题</th>
            <th>发布时间</th>
            <th class="sn">修改</th>
            <th class="sn">删除</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <td colspan="5"><nlft:page /></td>
        </tr>
    </tfoot>
    <tbody>
    <c:if test="${empty nlfPagingData.data}">
        <tr>
            <td colspan="5">暂无文章</td>
        </tr>
    </c:if>
    <c:forEach items="${nlfPagingData.data}" var="o" varStatus="index">
        <tr>
            <td>${index.count}</td>
            <td>${o.title}</td>
            <td>${o.time}</td>
            <td><a href="javascript:void(0);" onclick="mod('${o.id}');">修改</a></td>
            <td><a href="javascript:void(0);" onclick="del('${o.id}');">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
function del(id){
  I.want(function(){
    I.z.Confirm.create({
      content:'您确定要删除吗？',
      width:300,
      height:120,
      mask_opacity:30,
      mask_color:'#000',
      mask_close:false,
      yes:function(){
        var inst = this;
        I.net.Rmi.set('id',id);
        I.net.Rmi.call('admin-Article','delete',function(r){
          inst.close();
          dashboard.find('admin-Article/pageList');
        });
      }
    });
  });
}
function mod(id){
  dashboard.find('admin-Article/pageModify?id='+id);
}
</script>