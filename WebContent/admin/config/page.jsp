<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="form">
<div id="form">
  <c:forEach items="${configs}" var="o" varStatus="index">
  <ul>
    <li data-width="10">${o.name}：</li>
    <li><input id="${o.key}" type="text" value="${o.value}" /></li>
    <li data-width="40"><div>${o.description}</div></li>
  </ul>
  </c:forEach>
  <ul>
    <li></li>
    <li data-width="10"><a id="btn">保存</a></li>
  </ul>
</div>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      var inputs = I.$('form','tag','input');
      for(var i=0;i<inputs.length;i++){
        var ipt = inputs[i];
        I.net.Rmi.set(ipt.id,ipt.value);
      }
      I.net.Rmi.call('admin-Config', 'modify', function(r) {
        I.net.Page.find('admin-Config/page');
      });
    }
  });
});
</script>