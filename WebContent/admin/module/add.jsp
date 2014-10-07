<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form">
<div id="form">
  <ul>
    <li data-width="10">名称：</li>
    <li><input id="name" type="text" /></li>
  </ul>
  <ul>
    <li data-width="10">序号：</li>
    <li><input id="index" type="text" /></li>
  </ul>
  <ul>
    <li data-width="10">位置：</li>
    <li><select id="pos">
          <option value="0">顶部导航</option>
          <option value="1">底部导航</option>
          <option value="2">文章详情底部插件</option>
          <option value="3">右侧边栏底部插件</option>
          <option value="4">文章详情顶部插件</option>
          <option value="5">右侧边栏顶部插件</option>
        </select></li>
  </ul>
  <ul>
    <li data-width="10">类型：</li>
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
    <li data-width="10">URL：</li>
    <li><input id="url" type="text" /></li>
  </ul>
  <ul>
    <li data-width="10">内容：</li>
    <li><textarea id="content" rows="10"></textarea></li>
  </ul>
  <ul>
    <li data-width="10">是否首页：</li>
    <li><select id="home">
          <option value="0">否</option>
          <option value="1">是</option>
        </select></li>
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
  var editor = I.ui.Editor.render('content',{
    checkKlass:'nc-liat6-frame-web-upload-UploadStatus',
    uploadUrl:'${PATH}/admin-File/uploadBigPic'
  });
  I.ui.Button.render('btn',{
    callback:function(){
      I.net.Rmi.set('name',I.$('name').value);
      I.net.Rmi.set('index',I.$('index').value);
      I.net.Rmi.set('pos',I.$('pos').value);
      I.net.Rmi.set('type',I.$('type').value);
      I.net.Rmi.set('content',editor.getContent());
      I.net.Rmi.set('url',I.$('url').value);
      I.net.Rmi.set('home',I.$('home').value);
      I.net.Rmi.call('admin-Module', 'add', function(r) {
        I.net.Page.find('admin-Module/pageList');
        hidePanel();
      });
    }
  });
});
</script>