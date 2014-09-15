<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="bar">
  <label>修改友情链接</label>
  <a class="fa fa-minus-square" href="javascript:void(0);" onclick="deleteLink();">删除</a>
</div>
<div class="form">
<div id="form">
  <ul>
    <li data-width="15">网站名称：</li>
    <li><input id="name" type="text" value="${link.name}" /></li>
  </ul>
  <ul>
    <li data-width="15">序号：</li>
    <li><input id="index" type="text" value="${link.index}" /></li>
  </ul>
  <ul>
    <li data-width="15">URL：</li>
    <li><input id="url" type="text" value="${link.url}" /></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="20"><a id="btn">修改</a></li>
  </ul>
</div>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('id','${id}');
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.set('index',I.$('index').value);
      I.net.Rmi.set('url',I.$('url').value);
      I.net.Rmi.call('admin-Link', 'modify', function(r) {
        I.net.Page.find('admin-Link/pageList');
        hidePanel();
      });
    }
  });
});

function deleteLink(){
  I.want(function(){
    I.z.Confirm.create({
      content:'您确定要删除该模块吗？',
      yes:function(){
        I.net.Rmi.set('id','${id}');
        I.net.Rmi.call('admin-Link','delete',function(r){
          I.net.Page.find('admin-Link/pageList');
          hidePanel();
        });
      }
    });
  });
}
</script>