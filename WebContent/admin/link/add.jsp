<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form">
<div id="form">
  <ul>
    <li data-width="10">网站名称：</li>
    <li><input id="name" type="text" /></li>
  </ul>
  <ul>
    <li data-width="10">序号：</li>
    <li><input id="index" type="text" /></li>
  </ul>
  <ul>
    <li data-width="10">URL：</li>
    <li><input id="url" type="text" /></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="10"><a id="btn">添加</a></li>
  </ul>
</div>
</div>
<script type="text/javascript">
I.want(function(){
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.set('index',I.$('index').value);
      I.net.Rmi.set('url',I.$('url').value);
      I.net.Rmi.call('admin-Link', 'add', function(r) {
        I.net.Page.find('admin-Link/pageList');
        hidePanel();
      });
    }
  });
});
</script>