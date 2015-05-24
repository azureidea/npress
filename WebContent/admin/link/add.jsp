<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="form">
  <ul>
    <li data-width="10">网站名称：</li>
    <li><input id="name" type="text" /></li>
    <li></li>
  </ul>
  <ul>
    <li data-width="10">序号：</li>
    <li><input id="index" type="text" value="0" /></li>
    <li><div class="remark">输入数字</div></li>
  </ul>
  <ul>
    <li data-width="10">URL：</li>
    <li><input id="url" type="text" /></li>
    <li></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="10"><a id="btn">添加</a></li>
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
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.set('index',I.$('index').value);
      I.net.Rmi.set('url',I.$('url').value);
      I.net.Rmi.call('admin-Link', 'add', function(r) {
      });
    }
  });
});
</script>