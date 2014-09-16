<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="bar">
  <label>修改模块</label>
  <a class="fa fa-minus-square" href="javascript:void(0);" onclick="deleteModule();">&nbsp;删除</a>
</div>
<div class="form">
<div id="form">
  <ul>
    <li data-width="15">名称：</li>
    <li><input id="name" type="text" value="${module.name}" /></li>
  </ul>
  <ul>
    <li data-width="15">序号：</li>
    <li><input id="index" type="text" value="${module.index}" /></li>
  </ul>
  <ul>
    <li data-width="15">位置：</li>
    <li><select id="pos">
          <option value="0">顶部导航</option>
          <option value="1">底部导航</option>
          <option value="2">文章详情底部</option>
          <option value="3">右侧边栏</option>
        </select></li>
  </ul>
  <ul>
    <li data-width="15">类型：</li>
    <li><select id="type">
          <option value="0">本窗口打开的本站URL</option>
          <option value="1">新窗口打开的本站URL</option>
          <option value="2">本窗口打开的外站URL</option>
          <option value="3">新窗口打开的外站URL</option>
          <option value="4">本窗口打开的页面</option>
          <option value="5">新窗口打开的页面</option>
          <option value="6">插件</option>
        </select></li>
  </ul>
  <ul>
    <li data-width="15">URL：</li>
    <li><input id="url" type="text" value="${module.url}" /></li>
  </ul>
  <ul>
    <li data-width="15">内容：</li>
    <li><textarea id="content" rows="10">${module.content}</textarea></li>
  </ul>
  <ul>
    <li data-width="15">是否首页：</li>
    <li><select id="home">
          <option value="0">否</option>
          <option value="1">是</option>
        </select></li>
  </ul>
  <ul>
    <li></li>
    <li data-width="20"><a id="btn">修改</a></li>
  </ul>
</div>
</div>
<script type="text/javascript">
I.want(function(){
  I.$('type').value = '${module.type}';
  I.$('pos').value = '${module.pos}';
  I.$('home').value = '${module.home}';
  I.ui.Form.render('form');
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('id','${id}');
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.set('index',I.$('index').value);
      I.net.Rmi.set('pos',I.$('pos').value);
      I.net.Rmi.set('type',I.$('type').value);
      I.net.Rmi.set('content',I.$('content').value);
      I.net.Rmi.set('url',I.$('url').value);
      I.net.Rmi.set('home',I.$('home').value);
      I.net.Rmi.call('admin-Module', 'modify', function(r) {
        I.net.Page.find('admin-Module/pageList');
        hidePanel();
      });
    }
  });
});

function deleteModule(){
  I.want(function(){
    I.z.Confirm.create({
      content:'您确定要删除该模块吗？',
      yes:function(){
        I.net.Rmi.set('id','${id}');
        I.net.Rmi.call('admin-Module','delete',function(r){
          I.net.Page.find('admin-Module/pageList');
          hidePanel();
        });
      }
    });
  });
}
</script>