<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="form">
  <c:forEach items="${configs}" var="o" varStatus="index">
  <ul>
    <li data-width="15">${o.name}：</li>
    <li><input id="${o.key}" type="text" value="${o.value}" /></li>
    <li><div class="remark">${o.description}</div></li>
  </ul>
  </c:forEach>
  <ul>
    <li></li>
    <li data-width="15"><a id="btn">保存</a></li>
    <li></li>
  </ul>
</div>
<script>
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    background:'#5bc0de',
    border:'1px solid #46b8da',
    color:'#FFF',
    background_hover:'#31b0d5',
    border_hover:'1px solid #269abc',
    color_hover:'#FFF',
    callback:function(){
      var inputs = I.$('form','tag','input');
      for(var i=0;i<inputs.length;i++){
        var ipt = inputs[i];
        I.net.Rmi.set(ipt.id,ipt.value);
      }
      I.net.Rmi.call('admin-Config', 'modify', function(r) {
      });
    }
  });
});
</script>